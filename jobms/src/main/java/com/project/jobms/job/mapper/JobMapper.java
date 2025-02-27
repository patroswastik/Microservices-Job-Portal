package com.project.jobms.job.mapper;

import com.project.jobms.job.Job;
import com.project.jobms.job.dto.JobWithCompanyDTO;
import com.project.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapToCompanyDTO(Job job, Company company) {
        JobWithCompanyDTO dto = new JobWithCompanyDTO();
        dto.setId(job.getId());
        dto.setDescription(job.getDescription());
        dto.setTitle(job.getTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setCompany(company);

        return dto;
    }
}
