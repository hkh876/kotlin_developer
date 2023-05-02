package com.example.cardatabase.domain

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CarRepository : CrudRepository<Car, Long> {
    fun findByBrand(brand: String): List<Car> // 브랜드로 자동차를 검색
    fun findByColor(color: String): List<Car> // 색상으로 자동차를 검색
    fun findByYear(year: Int): List<Car> // 연도로 자동차를 검색
    fun findByBrandAndModel(brand: String, model: String): List<Car> // 브랜드와 모델로 자동차를 검색
    fun findByBrandOrColor(brand: String, color: String): List<Car> // 브랜드나 색상으로 자동차를 검색
    fun findByBrandOrderByYearAsc(brand: String): List<Car> // 브랜드로 자동차를 검색하고 연도로 정렬

    @Query("select c from Car c where c.brand = ?1")
    fun findByBrandUseSQL(brand: String): List<Car> // SQL 문을 이용해 브랜드로 자동차를 검색
}