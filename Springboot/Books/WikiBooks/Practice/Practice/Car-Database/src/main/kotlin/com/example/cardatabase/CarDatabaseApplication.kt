package com.example.cardatabase

import com.example.cardatabase.domain.*
import com.example.cardatabase.service.UserDetailsServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarDatabaseApplication(
	val carRepository: CarRepository,
	val ownerRepository: OwnerRepository,
	val userRepository: UserRepository,
	val userService: UserDetailsServiceImpl
) : CommandLineRunner {
	private val logger: Logger = LoggerFactory.getLogger(CarDatabaseApplication::class.java)

	override fun run(vararg args: String?) {
		// 소유자 객체를 추가하고 데이터베이스에 저장
		val owner1 = Owner(
			firstName = "John",
			lastName = "Johnson"
		)
		val owner2 = Owner(
			firstName = "Mary",
			lastName = "Robinson"
		)
		ownerRepository.saveAll(listOf(owner1, owner2))

		// 자동차 객체를 추가하고 소유자와 연결한 후 데이터베이스에 저장
		val car1 = Car(
			model = "Ford",
			brand = "Mustang",
			color = "Red",
			registerNumber = "ADF-1121",
			year = 2021,
			price = 59000,
			owner = owner1
		)
		val car2 = Car(
			model = "Nissan",
			brand = "Leaf",
			color = "White",
			registerNumber = "SSJ-3002",
			year = 2019,
			price = 29000,
			owner = owner2
		)
		val car3 = Car(
			model = "Toyota",
			brand = "Prius",
			color = "Silver",
			registerNumber = "KKO-0212",
			year = 2020,
			price = 39000,
			owner = owner2
		)
		carRepository.saveAll(listOf(car1, car2, car3))

		// 모든 자동차를 가져와 콘솔에 로깅
		val allCars = carRepository.findAll()
		allCars.forEach { car ->
			logger.info("${car.brand} ${car.model}")
		}

		// 사용자 저장
		val user = User(
			username = "user",
			password = userService.getEncodedPassword("test1234"),
			role = "USER"
		)

		val admin = User(
			username = "admin",
			password = userService.getEncodedPassword("test1234"),
			role = "ADMIN"
		)
		userRepository.saveAll(listOf(admin, user))
	}
}

fun main(args: Array<String>) {
	runApplication<CarDatabaseApplication>(*args)
}
