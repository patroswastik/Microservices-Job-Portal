package com.project.companyms.company.impl;

import com.project.companyms.company.Company;
import com.project.companyms.company.CompanyRepository;
import com.project.companyms.company.CompanyService;
import com.project.companyms.company.clients.ReviewClient;
import com.project.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println("Output:" + reviewMessage.getCompanyId());
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElse(null);
        if(company == null) return ;
        Double averageRating = reviewClient.getAverageRatingForCompany(company.getId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
