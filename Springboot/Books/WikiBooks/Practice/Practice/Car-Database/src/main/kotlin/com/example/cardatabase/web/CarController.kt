package com.example.cardatabase.web

import com.example.cardatabase.domain.Car
import com.example.cardatabase.domain.CarRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CarController(
    val carRepository: CarRepository
) {
    @RequestMapping("/cars")
    fun getCars(): Iterable<Car> {
        return carRepository.findAll()
    }
}