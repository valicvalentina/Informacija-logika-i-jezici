package hr.fer.ilj.dz2.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import hr.fer.ilj.dz2.json.Car;
@DataJpaTest
public class CarRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private CarRepository repo;
	@Test
	void readingOne() {
	entityManager.persist(new Car(null, "Mercedes", "slc 500", 20L));
	List<Car> allCars = repo.findAll();
	assertThat(allCars)
	.hasSize(1)
	.extracting("manufacturer")
	.containsExactly("Mercedes");
	}
}
