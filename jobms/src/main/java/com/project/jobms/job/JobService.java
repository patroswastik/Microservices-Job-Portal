package com.project.jobms.job;

import com.project.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    JobWithCompanyDTO getJobById(Long id);
    void createJob(Job job);
    Boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job job);
}
