package com.enlightendev.spring.batch.codegeeks.batch;

import com.enlightendev.spring.batch.codegeeks.domain.Trade;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 */
public class TradeBatchProcessor implements ItemProcessor<Trade,Trade> {

    @Override
    public Trade process(final Trade item) throws Exception {
        //nothing to do
        return item;
    }
}
