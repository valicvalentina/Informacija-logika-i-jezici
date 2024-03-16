package hr.fer.ilj.dz2.service;

import java.util.Collection;
import java.util.Optional;

import hr.fer.ilj.dz2.json.Car;

public interface CarServiceInt {
	 Collection<Car> getAll();
     long addCar(Car car);
	 Optional<Car> getCar(long carId);
	 boolean delete(long carId);
	 boolean updateCar(Car car);
	 Car fetch(long carId);
	
	
}
