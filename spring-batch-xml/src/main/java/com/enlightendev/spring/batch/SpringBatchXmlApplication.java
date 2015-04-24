package com.enlightendev.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.batch.runtime.JobExecution;

@SpringBootApplication
public class SpringBatchXmlApplication {

    @Autowired
    private static Job job;

    @Autowired
    private static JobLauncher jobLauncher;


    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        SpringApplication.run(SpringBatchXmlApplication.class, args);

        JobParameters jobParameters = new JobParameters();
        JobExecution execution = (JobExecution) jobLauncher.run(job, jobParameters);
        System.out.println(execution.getExitStatus());

    }
}
