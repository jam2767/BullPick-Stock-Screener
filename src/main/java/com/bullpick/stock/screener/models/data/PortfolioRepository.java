package com.bullpick.stock.screener.models.data;

import com.bullpick.stock.screener.models.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {
}
