package com.example.examtestapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ExamTestAppApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
	runApplication<ExamTestAppApplication>(*args)
}
