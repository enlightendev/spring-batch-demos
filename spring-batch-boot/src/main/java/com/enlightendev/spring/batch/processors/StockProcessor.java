package com.enlightendev.spring.batch.processors;

import com.enlightendev.spring.batch.domain.Stock;
import org.springframework.batch.item.ItemProcessor;

/**
 */
public class StockProcessor implements ItemProcessor<Stock, Stock> {

    @Override
    public Stock process(final Stock stock) throws Exception {

        String name = stock.getName().toUpperCase();
        double price = stock.getPrice() * 100.0f;

        final Stock transformedStock = new Stock(name, price);

        System.out.println("Converting (" + stock + ") into (" + transformedStock + ")");

        return transformedStock;
    }
}
