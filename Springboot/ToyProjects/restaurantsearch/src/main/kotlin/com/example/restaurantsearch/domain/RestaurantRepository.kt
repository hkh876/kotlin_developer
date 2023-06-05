package com.example.restaurantsearch.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface RestaurantRepository : CrudRepository<Restaurant, Long> {
    fun findByStoreNameContaining(@Param("store_name") storeName: String): MutableList<Restaurant>
}