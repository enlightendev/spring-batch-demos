package com.enlightendev.spring.batch.codegeeks.config;

import com.enlightendev.spring.batch.codegeeks.batch.TradeBatchProcessor;
import com.enlightendev.spring.batch.codegeeks.batch.TradeBatchWriter;
import com.enlightendev.spring.batch.codegeeks.domain.Trade;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public ItemReader<Trade> reader() {
        FlatFileItemReader<Trade> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("trades.csv"));
        reader.setLineMapper(new DefaultLineMapper<Trade>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"ticker","shares"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Trade>(){{
                setTargetType(Trade.class);
            }});
        }});

        //return new TradeBatchReader();
        return reader;
    }

    @Bean
    public ItemProcessor<Trade,Trade> processor() {
        return new TradeBatchProcessor();
    }

    @Bean
    public ItemWriter writer(DataSource dataSource) {
/*        JdbcBatchItemWriter<Trade> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into trades (ticker, shares) values (:ticker, :shares)");
        writer.setDataSource(dataSource);
        return writer;*/

        return new TradeBatchWriter(dataSource);
    }

    @Bean
    public Job job1(JobBuilderFactory jobs, Step step1) {
        return jobs.get("job1").incrementer(new RunIdIncrementer())
                .flow(step1).end().build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      ItemReader reader, ItemWriter writer,
                      ItemProcessor processor) {
	/* it handles bunches of 10 units */
        return stepBuilderFactory.get("step1")
                . chunk(10).reader(reader)
                .processor(processor).writer(writer).build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
