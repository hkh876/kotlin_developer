package com.example.examtestapp.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ExamDto(
    @JsonProperty(value = "save_status")
    val saveStatus: Boolean,
)
