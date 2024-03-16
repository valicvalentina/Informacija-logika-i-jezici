package hr.fer.ilj.dz2.resource;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.fer.ilj.dz2.json.Car;
import hr.fer.ilj.dz2.service.CarService;



@WebMvcTest(CarController.class)
class CarControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private CarService carService;
	
	@Test
	void allCars_noCars() throws Exception {
	given(carService.getAll())
	.willReturn(List.of());
	mvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())
	.andExpect(content().string("[]"));
	}
	@Test
	  void addCar() throws Exception {
	    mvc.perform(post("/add").accept(MediaType.APPLICATION_JSON)
	      .content(asJsonString(new Car(2L, "Mercedes","slc 500",20L)))
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(header().exists("Location"));
	  }
	@Test
	  void getCar() throws Exception {
	    given(carService.getCar(1L))
	      .willReturn(Optional.of(new Car(1L, "Mercedes", "slc 500", 20L)));
	    mvc.perform(get("/car/{id}", 1L).accept(MediaType.APPLICATION_JSON))
//	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isOk())
	      .andExpect(content().string("{\"id\":1,\"manufacturer\":\"Mercedes\",\"model\":\"slc 500\",\"serialNumber\":20}"));
	  }
	 @Test
	  void getCar_404() throws Exception {
	    given(carService.getCar(1L))
	      .willReturn(Optional.empty());

	    mvc.perform(get("/car/{id}", 1L).accept(MediaType.APPLICATION_JSON))
//	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isNotFound());
	  }
	 @Test
	  void deleteCar() throws Exception {
		given(carService.delete(1L))
	      .willReturn(true);

	    mvc.perform(delete("/remove/{id}", 1L).accept(MediaType.APPLICATION_JSON))
//	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isNoContent());
	  }

	  @Test
	  void deleteCar_404() throws Exception {
	    given(carService.delete(33L))
	      .willReturn(false);

	    mvc.perform(delete("/remove/{id}", 33L).accept(MediaType.APPLICATION_JSON))
//	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isNotFound());
	  }
	  @Test
	  void updateCar() throws Exception {
	    Car car = new Car(1L, "mercedes", "slc 500", 20L);
	    given(carService.updateCar(car))
	      .willReturn(true);
	   // car.setId(1L);
    // Long  id=(car.getId());
     //boolean b=carService.updateCar(car);
	    mvc.perform(put("/modify")
	      .content("{\n"
		      		+ "\"id\":1,\n"
		      		+ "\"manufacturer\":\"mercedes\",\n"
		      		+ "\"model\":\"slc 500\",\n"
		      		+ "\"serialNumber\":20\n"
		      		+ "}")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isNoContent());
	  }
	  @Test
	  void updateCar_404() throws Exception {
	    Car car = new Car(1L,"Mercedes","slc 500", 20L);
	    given(carService.updateCar(car))
	      .willReturn(false);

	    mvc.perform(put("/modify")
	      .content("{\n"
	      		+ "\"id\":1,\n"
	      		+ "\"manufacturer\":\"Mercedes\",\n"
	      		+ "\"model\":\"slc 500\",\n"
	      		+ "\"serialNumber\":20\n"
	      		+ "}")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(MockMvcResultHandlers.print())
	      .andExpect(status().isNotFound());
	  }


	public static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
}
