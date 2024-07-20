package com.nhnacademy.clientbatch.config;

import com.nhnacademy.clientbatch.service.ClientBatchService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BatchConfiguration.class)
@EnableBatchProcessing
@Import(BatchConfigurationTest.BatchTestConfig.class)
class BatchConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ClientBatchService clientBatchService;

    @MockBean
    private JobRepository jobRepository;

    @Test
    void testBeansAreLoaded() {
        assertNotNull(context.getBean("dormancyJob", Job.class), "DormancyJob bean should be loaded in the context");
        assertNotNull(context.getBean("dormancyStep", Step.class), "DormancyStep bean should be loaded in the context");
        assertNotNull(context.getBean("dormancyTasklet", Tasklet.class), "DormancyTasklet bean should be loaded in the context");
    }

    @TestConfiguration
    static class BatchTestConfig {

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}
