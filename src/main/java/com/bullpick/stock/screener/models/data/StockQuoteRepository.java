package com.bullpick.stock.screener.models.data;

import com.bullpick.stock.screener.models.StockQuote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StockQuoteRepository extends CrudRepository<StockQuote, Integer> {
}
