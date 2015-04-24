package com.enlightendev.spring.batch;

import com.enlightendev.spring.batch.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringBatchBootApplication implements CommandLineRunner {

    private static Log logger = LogFactory.getLog(SpringBatchBootApplication.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        logger.info("RUNNING MAIN");

        SpringApplication.run(SpringBatchBootApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Person> results = jdbcTemplate.query("SELECT first_name, last_name FROM people", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int row) throws SQLException {
                return new Person(rs.getString(1), rs.getString(2));
            }
        });

        for (Person person : results) {
            System.out.println("Found <" + person + "> in the database.");
        }
    }
}
