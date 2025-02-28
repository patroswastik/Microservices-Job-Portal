package com.project.jobms.job;

import com.project.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    JobDTO getJobById(Long id);
    void createJob(Job job);
    Boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job job);
}
