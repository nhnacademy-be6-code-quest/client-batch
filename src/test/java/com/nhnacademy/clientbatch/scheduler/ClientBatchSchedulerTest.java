package com.nhnacademy.clientbatch.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientBatchSchedulerTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job dormancyJob;

    @InjectMocks
    private ClientBatchScheduler clientBatchScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunJob_Success() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenReturn(new JobExecution(1L));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_JobExecutionAlreadyRunningException() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class)))
                .thenThrow(new JobExecutionAlreadyRunningException("Job is already running"));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_JobInstanceAlreadyCompleteException() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class)))
                .thenThrow(new JobInstanceAlreadyCompleteException("Job instance already completed"));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_JobParametersInvalidException() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class)))
                .thenThrow(new JobParametersInvalidException("Invalid job parameters"));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_JobRestartException() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class)))
                .thenThrow(new JobRestartException("Error restarting job"));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_UnexpectedException() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        clientBatchScheduler.runJob();

        verify(jobLauncher, times(1)).run(eq(dormancyJob), any(JobParameters.class));
    }
}