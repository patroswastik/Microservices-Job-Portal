package com.project.jobms.job.mapper;

import com.project.jobms.job.Job;
import com.project.jobms.job.dto.JobDTO;
import com.project.jobms.job.external.Company;
import com.project.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToCompanyDTO(Job job, Company company, List<Review> reviews) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setDescription(job.getDescription());
        dto.setTitle(job.getTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setCompany(company);
        dto.setReviews(reviews);

        return dto;
    }
}
