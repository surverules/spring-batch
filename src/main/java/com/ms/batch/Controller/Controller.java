package com.ms.batch.Controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class Controller {


    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @GetMapping("/start")
    public void startBatch() throws Exception{

        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("Time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(processJob, jobParameter);

        System.out.println("Job Started");

    }
}
