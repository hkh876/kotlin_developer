package com.example.cardatabase

import com.example.cardatabase.web.CarController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarDatabaseApplicationTest {
	@Autowired
	private lateinit var carController: CarController

	@Test
	@DisplayName("First example test case")
	fun contextLoads() {
		assertThat(carController).isNotNull
	}
}
