package com.project.jobms.job.impl;

import com.project.jobms.job.Job;
import com.project.jobms.job.JobRepository;
import com.project.jobms.job.JobService;
import com.project.jobms.job.dto.JobWithCompanyDTO;
import com.project.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO dto = new JobWithCompanyDTO();
        Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/"+job.getCompanyId(), Company.class);
        dto.setJob(job);
        dto.setCompany(company);
        return dto;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        JobWithCompanyDTO dto = convertToDto(job);
        return dto;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        try{
            if(jobRepository.existsById(id)) {
                jobRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job jobUpdate = jobOptional.get();
            jobUpdate.setTitle(job.getTitle());
            jobUpdate.setDescription(job.getDescription());
            jobUpdate.setMinSalary(job.getMinSalary());
            jobUpdate.setMaxSalary(job.getMaxSalary());
            jobUpdate.setLocation(job.getLocation());
            jobRepository.save(jobUpdate);
            return true;
        }
        return false;
    }
}
