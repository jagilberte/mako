package com.makobrothers.apps.rrhh.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.makobrothers.shared", "com.makobrothers.rrhh", "com.makobrothers.apps.rrhh.backend"}
)
class RrhhApplication

fun main(args: Array<String>) {
    SpringApplication.run(MakoApplication::class.java, *args)
}