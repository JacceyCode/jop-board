package com.jaccey.jopapp.job.impl;

import com.jaccey.jopapp.job.Job;
import com.jaccey.jopapp.job.JobRepository;
import com.jaccey.jopapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job jobData) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()) {
                Job job = jobOptional.get();

                job.setTitle(jobData.getTitle());
                job.setDescription(jobData.getDescription());
                job.setMinSalary(jobData.getMinSalary());
                job.setMaxSalary(jobData.getMaxSalary());
                job.setLocation(jobData.getLocation());

                jobRepository.save(job);

                return true;
            }

        return false;
    }
}
