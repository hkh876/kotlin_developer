package com.example.memberex.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TimeTraceAop {
    @Around("execution(* com.example.memberex..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Object {
        val start = System.currentTimeMillis()
        println("START: $joinPoint")

        try {
            return joinPoint.proceed() as Object
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            println("END: $joinPoint ${timeMs}ms")
        }
    }
}