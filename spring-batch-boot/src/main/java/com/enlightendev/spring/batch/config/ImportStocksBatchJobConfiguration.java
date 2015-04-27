package com.enlightendev.spring.batch.config;


import com.enlightendev.spring.batch.domain.Stock;
import com.enlightendev.spring.batch.processors.StockProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ImportStocksBatchJobConfiguration {
    
    @Bean
    public ItemReader<Stock> reader() {
        FlatFileItemReader<Stock> reader = new FlatFileItemReader<Stock>();
        reader.setResource(new ClassPathResource("data/stocks.csv"));
        reader.setLineMapper(new DefaultLineMapper<Stock>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "price"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Stock>() {{
                setTargetType(Stock.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<Stock, Stock> processor() {
        return new StockProcessor();
    }

    @Bean
    public ItemWriter<Stock> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Stock> writer = new JdbcBatchItemWriter<Stock>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Stock>());
        writer.setSql("INSERT INTO stock (name, price) VALUES (:name, :price)");
        writer.setDataSource(dataSource);
        return writer;
    }

    /*
     * The next chunk focuses on the actual job configuration.
     *
     *  The first method defines the job and the second one defines a single step. Jobs are built from steps,
     *  where each step can involve a reader, a processor, and a writer.
     */
    @Bean
    public Job importStocksJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importStockJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Stock> reader,
                      ItemWriter<Stock> writer, ItemProcessor<Stock, Stock> processor) {

        return stepBuilderFactory.get("step1")
                .<Stock, Stock>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
