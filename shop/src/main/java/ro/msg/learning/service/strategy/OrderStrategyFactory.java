package ro.msg.learning.service.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.repository.StockRepository;

@Configuration
@RequiredArgsConstructor
public class OrderStrategyFactory {

	enum Strategy {
		SINGLE_LOCATION, MOST_ABUNDANT
	}

	@Value("${order-strategy}")
	private Strategy orderStrategy;

	private final StockRepository stockRepository;

	@Bean
	public OrderStrategy orderStrategy() {
		switch (orderStrategy) {
		case SINGLE_LOCATION:
			return new SingleLocationStrategy(stockRepository);
		case MOST_ABUNDANT:
			return new MostAbundantStrategy(stockRepository);
		default:
			return new SingleLocationStrategy(stockRepository);
		}
	}
}
