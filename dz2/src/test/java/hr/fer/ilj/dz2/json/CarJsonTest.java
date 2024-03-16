package hr.fer.ilj.dz2.json;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class CarJsonTest {

	@Autowired
	private JacksonTester<Car> json;
	@Test
	void Car() throws Exception {
	Car car = new Car(2L, "Mercedes", "slc 500", 20L);
	String expectedContent = "{\"id\":2,\"manufacturer\":\"Mercedes\",\"model\":\"slc 500\",\"serialNumber\":20}";
	assertThat(json.write(car)).isStrictlyEqualToJson(expectedContent);
	}
	

}
