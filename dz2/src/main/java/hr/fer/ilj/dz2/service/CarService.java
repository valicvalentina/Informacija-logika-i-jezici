package hr.fer.ilj.dz2.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.ilj.dz2.json.Car;
import hr.fer.ilj.dz2.repository.CarRepository;

@Service
public class CarService implements CarServiceInt{
   @Autowired
	private CarRepository repo;

//	  public CarService(CarRepository repo) {
//	    this.repo = repo;
//	  }
     
	  @Override
	  public Collection<Car> getAll() {
	    return repo.findAll();
	  }
      @Override
	  public long addCar(Car car) {
	    car = repo.save(car);
	    return car.getId();
	  }
      @Override
	  public Optional<Car> getCar(long carId) {
	    return repo.findById(carId);
	  }
      @Override
      public Car fetch(long carId) {
    	  return getCar(carId).orElseThrow(
    		      () -> new EntityMissingException(Car.class, carId)
    		    );

        
      }

     
	@Override
	  public boolean delete(long carId) {
    	  //Car car = fetch(carId);
		 if(repo.existsById(carId)) {
		      repo.deleteById(carId);
		      return true;
		    }else {

		    return false;
		    }
		  }
    	    

	  
      @Override
	  public boolean updateCar(Car car) {
    	  if(repo.existsById(car.getId())) {
    	      repo.save(car);
    	      return true;
    	    }

    	    return false;
    	  }
	  }

