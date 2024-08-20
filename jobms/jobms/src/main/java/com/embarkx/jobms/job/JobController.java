package com.embarkx.jobms.job;

import com.embarkx.jobms.job.dto.JobWithCompanyDTO;
import com.embarkx.jobms.job.external.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/jobs") // Specifies the base URL.
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping("")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        // InterService communication.
        RestTemplate restTemplate=new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
        System.out.println("COMPANY : "+company.getName());
        System.out.println("COMPANY : "+company.getId());
        // DTO - Data transfer objects - Design pattern used to transfer data between software application subsystems.

        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable(name = "id") Long id){
        Job job = jobService.getJobById(id);
        if(job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable(name = "id") Long id){
        boolean deleted=jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @PutMapping("/jobs/{id}")
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable(name = "id") Long id,@RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
