package com.example.restaurantsearch.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Restaurant(
    @Id
    var id: Long = 0,
    @Column(name = "permission_date")
    val permissionDate: String = "",
    @Column(name = "business_status")
    val businessStatus: Int = 0,
    @Column(name = "closure_date")
    val closureDate: String = "",
    @Column(name = "street_address")
    val streetAddress: String = "",
    @Column(name = "store_name")
    val storeName: String = "",
    @Column(name = "updated_date")
    val updatedDate: String = "",
    @Column(name = "store_type")
    val storeType: String = "",
)