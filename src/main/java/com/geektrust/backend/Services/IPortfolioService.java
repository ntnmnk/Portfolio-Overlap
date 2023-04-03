package com.geektrust.backend.Services;

import java.util.List;

public interface IPortfolioService {

    void currentPortfolioStocks(String[] stocks);

    List<String> calculatePortfolioOverlap(String funds);

    void addStocksToFund(String fundName, String stockName);
}
