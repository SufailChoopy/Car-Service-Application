package com.practice.mapper;

import org.springframework.stereotype.Component;

import com.practice.entity.CarService;
import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;

@Component
public class CarServiceMapper {

	public CarService mapToCarService(CarServiceRequestDTO carServiceRequestDTO) {
		CarService s = new CarService();
		s.setServiceType(carServiceRequestDTO.getServiceType());
		s.setCost(carServiceRequestDTO.getCost());
		s.setDescription(carServiceRequestDTO.getDescription());
		return s;
	}

	public CarServiceResponseDTO mapToCarServiceResponse(CarService s) {
		CarServiceResponseDTO srd = new CarServiceResponseDTO();
		srd.setServiceType(s.getServiceType());
		srd.setCost(s.getCost());
		srd.setDescription(s.getDescription());
		return srd;
	}

}
