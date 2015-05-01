package com.enlightendev.spring.batch.codegeeks.batch;

import com.enlightendev.spring.batch.codegeeks.domain.Trade;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 *
 */
public class TradeBatchReader implements ItemReader<Trade> {

    @Override
    public Trade read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
