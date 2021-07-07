package com.ms.batch.config;

import com.ms.batch.model.Employee;
import com.ms.batch.process.Processor;
import com.ms.batch.process.Reader;
import com.ms.batch.process.Writer;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob(Reader reader, Processor processor, Writer writer){
        return jobBuilderFactory.get("ETL Job")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(orderStep(reader, processor, writer))
                .end()
                .build();
    }

    private JobExecutionListener listener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                System.out.println("Starting job");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    System.out.println("Ending Job");
                }
            }
        };
    }

    @Bean
    @Autowired
    public Step orderStep(Reader reader, Processor processor, Writer writer) {
        return stepBuilderFactory.get("Step 1")
                .<String, Employee> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

}
