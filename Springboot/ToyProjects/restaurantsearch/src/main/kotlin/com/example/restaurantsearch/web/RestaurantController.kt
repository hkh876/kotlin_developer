package com.example.restaurantsearch.web

import com.example.restaurantsearch.domain.RestaurantRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController(val restaurantRepository: RestaurantRepository) {
    @RequestMapping(value = [RESTAURANT_GET_ALL_URL], method = [RequestMethod.GET])
    fun getRestaurants(
        @RequestParam(name = "search", required = false) search: String?
    ) : ResponseEntity<Any?> {
        if (search?.isNotEmpty() == true) {
            return ResponseEntity.ok(restaurantRepository.findByStoreNameContaining(search))
        }

        return ResponseEntity.ok(restaurantRepository.findAll())
    }

    companion object {
        private const val RESTAURANT_BASE_URL = "/api/v1"
        const val RESTAURANT_GET_ALL_URL = "$RESTAURANT_BASE_URL/restaurants"
    }
}