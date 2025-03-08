package com.project.companyms.company;

import com.project.companyms.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    public List<Company> getAllCompanies();
    public Company getCompanyById(Long id);
    public void createCompany(Company company);
    public boolean updateCompany(Long id, Company company);
    public boolean deleteCompany(Long id);
    public void updateCompanyRating(ReviewMessage reviewMessage);
}
