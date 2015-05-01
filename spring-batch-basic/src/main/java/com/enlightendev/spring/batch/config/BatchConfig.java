package com.enlightendev.spring.batch.config;

import com.enlightendev.spring.batch.domain.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * C:\\dev\\java\\projects\\edu\\spring\\spring-batch-demos\\spring-batch-basic\\src\\main\\resources\\customer.csv
     * @param fileName
     * @return
     * @throws Exception
     */
    @Bean
    @StepScope
    protected FlatFileItemReader<Customer> reader(@Value("#{jobParameters['fileName']}") Resource fileName) throws Exception {
        DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<Customer>();
        defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        defaultLineMapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
            @Override
            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
                Customer cust = new Customer();

                cust.setCustomerName(fieldSet.readString(0));
                cust.setQty(fieldSet.readInt(1));

                return cust;
            }
        });

        defaultLineMapper.afterPropertiesSet();

        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setLineMapper(defaultLineMapper);
        //reader.setResource(fileName);
        reader.setResource(new ClassPathResource("customer.csv"));
        reader.afterPropertiesSet();

        return reader;
    }



    @Bean
    protected JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<Customer>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO CUSTOMER VALUES(:customerName, :qty)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        writer.afterPropertiesSet();

        return writer;
    }

    @Bean
    protected Step step(DataSource dataSource) throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(5)
                .reader(reader(null))
                .writer(writer(dataSource))
                .build();
    }

    @Bean
    protected Job fileToDatabase(Step step1) {
        return jobBuilderFactory.get("fileToDatabase").start(step1).build();
    }
}
