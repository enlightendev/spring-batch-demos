package com.enlightendev.spring.batch.codegeeks.batch;

import org.springframework.batch.item.ItemWriter;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 */
public class TradeBatchWriter implements ItemWriter {

    public TradeBatchWriter(DataSource dataSource) {

    }

    @Override
    public void write(List items) throws Exception {
        System.out.println("Writing list");
    }
}
