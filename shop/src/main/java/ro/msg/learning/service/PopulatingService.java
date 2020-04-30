package ro.msg.learning.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.LocationRepository;
import ro.msg.learning.repository.RevenueRepository;
import ro.msg.learning.repository.StockRepository;

@Service
@RequiredArgsConstructor
@Profile("test")
public class PopulatingService {

	private final LocationRepository locationRepository;
	private final StockRepository stockRepository;
	private final RevenueRepository revenueRepository;

	public void deleteAll() {
		revenueRepository.deleteAll();
		stockRepository.deleteAll();
		locationRepository.deleteAll();
	}

	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}

	public Stock saveStock(Stock stock) {
		return stockRepository.save(stock);
	}
}
