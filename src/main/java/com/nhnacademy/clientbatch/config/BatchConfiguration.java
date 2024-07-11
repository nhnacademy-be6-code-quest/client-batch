package com.nhnacademy.clientbatch.config;

import com.nhnacademy.clientbatch.service.ClientBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ClientBatchService clientBatchService;

    @Bean
    public Job dormancyJob() {
        return new JobBuilder("dormancyJob", jobRepository)
                .start(dormancyStep())
                .build();
    }

    @Bean
    public Step dormancyStep() {
        return new StepBuilder("dormancyStep", jobRepository)
                .tasklet(dormancyTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet dormancyTasklet() {
        return ((contribution, chunkContext) -> {
            clientBatchService.updateInactiveClients();
            return RepeatStatus.FINISHED;
        });
    }
}