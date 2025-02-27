package com.project.jobms.job;

import com.project.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id) {
        JobWithCompanyDTO jobWithCompany = jobService.getJobById(id);
        if(jobWithCompany == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobWithCompany, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        if(jobService.deleteJobById(id)) return ResponseEntity.ok("Job deleted successfully");
        else return new ResponseEntity<>("Job could not be deleted", HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/jobs/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
        boolean update = jobService.updateJob(id, job);
        if(update) {
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job could not be updated", HttpStatus.NOT_FOUND);
    }
}
