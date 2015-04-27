package com.enlightendev.spring.batch.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobLauncherService {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    public void launchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("run-date",new Date());

        JobExecution execution = jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        System.out.println(execution.getExitStatus());
    }
}