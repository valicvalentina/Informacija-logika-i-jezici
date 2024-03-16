package hr.fer.ilj.dz2.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hr.fer.ilj.dz2.json.Car;
import hr.fer.ilj.dz2.service.CarService;





@RestController
public class CarController {
	@Autowired
	private CarService carService;
	
//	public CarController(CarService carService) {
//	this.carService = carService;
//	}
	
	@GetMapping("/cars")
	public Collection<Car> allCars() {
	return carService.getAll();
	}
	
	@PostMapping("/add")
	  public ResponseEntity<Void> addCar(@RequestBody Car car) throws URISyntaxException {
	    long carId = carService.addCar(car);
	    return ResponseEntity.created(new URI("/added/" + carId)).build();
	  }
	@GetMapping("/car/{id}")
	  public ResponseEntity<Car> getCar(@PathVariable("id") Long carId) {
	    return carService.getCar(carId)
	        .map(ur -> ResponseEntity.ok(ur))
	        .orElseGet(() -> ResponseEntity.notFound().build());
	  }
	@DeleteMapping("/remove/{id}")
//	  public boolean delete(@PathVariable("id") Long carId) {
//		return carService.delete(carId);
//	  }
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long carId) {
	    if(carService.delete(carId)) {
	      return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.notFound().build();
	  }

	@PutMapping("/modify")
	  public ResponseEntity<Void> updateCar(@RequestBody Car car)throws URISyntaxException {
	    //return carService.updateCar(car);
		if(carService.updateCar(car)==true) {
		      return ResponseEntity.noContent().build();
		    } else {

		    return ResponseEntity.notFound().build();
		    }
		  }
	}


