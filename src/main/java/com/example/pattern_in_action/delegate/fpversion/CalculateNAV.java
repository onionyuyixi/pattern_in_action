package com.example.pattern_in_action.delegate.fpversion;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CalculateNAV {

    private final Supplier<BigDecimal> priceFinder;

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.get().multiply(BigDecimal.valueOf(shares));
    }


    public static void main(String[] args) {
        CalculateNAV calculateNAV = new CalculateNAV(() -> new BigDecimal("6.01"));
        System.err.println(calculateNAV.computeStockWorth("A", 1000));
    }

}
