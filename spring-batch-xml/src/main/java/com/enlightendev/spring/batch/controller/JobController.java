package com.enlightendev.spring.batch.controller;

import com.enlightendev.spring.batch.service.JobLauncherService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Juan on 4/25/15.
 */
@RestController
public class JobController {

    @Autowired
    private JobLauncherService jobLauncherService;

    @RequestMapping("/")
    String runJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncherService.launchJob();
        return "job luanched";
    }
}
