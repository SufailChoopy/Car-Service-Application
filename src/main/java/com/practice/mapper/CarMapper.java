package com.practice.mapper;

import org.springframework.stereotype.Component;

import com.practice.entity.Car;
import com.practice.request.dto.CarRequestDTO;
import com.practice.response.dto.CarResponseDTO;

@Component
public class CarMapper {

	public Car mapToCar(CarRequestDTO carRequestDTO) {
		Car c = new Car();
		c.setBrand(carRequestDTO.getBrand());
		c.setModel(carRequestDTO.getModel());
		return c;
	}

	public CarResponseDTO mapToCarResponse(Car c) {
		CarResponseDTO crd = new CarResponseDTO();
		crd.setId(c.getId());
		crd.setBrand(c.getBrand());
		crd.setModel(c.getModel());
		return crd;
	}

}
