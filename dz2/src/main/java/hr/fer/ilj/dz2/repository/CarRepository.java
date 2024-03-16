package hr.fer.ilj.dz2.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.ilj.dz2.json.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{

//	Car save(Car car);
//
//	  Collection<Car> loadAll();
//
//	  Optional<Car> getCar(long carId);
//
//	  boolean delete(long carId);
//
//	  boolean updateCar(Car car);
//
//	  long size();
	
}
