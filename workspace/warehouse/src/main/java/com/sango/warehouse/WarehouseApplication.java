package com.sango.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = {"com.sango.warehouse.mapper", "com.sango.warehouse.utils"})
@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {

		SpringApplication.run(WarehouseApplication.class, args);
	}
}
