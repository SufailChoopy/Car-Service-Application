package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.CarService;
import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;
import com.practice.service.CarServiceService;
import com.practice.utility.ResponseStructure;

@RestController
public class ServiceController {

	@Autowired
	private CarServiceService serviceService;

	@PostMapping("/services")
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> addCarService(
			@RequestBody CarServiceRequestDTO carServiceRequestDTO) {
		return serviceService.addCarService(carServiceRequestDTO);
	}

	@GetMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> findCarService(@PathVariable int id) {
		return serviceService.findCarServiceById(id);
	}

	@PutMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> updateCarServiceById(@PathVariable int id,
			@RequestBody CarServiceRequestDTO carServiceRequestDTO) {
		return serviceService.updateCarServiceById(id, carServiceRequestDTO);
	}

	@DeleteMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> deleteCarServiceById(@PathVariable int id) {
		return serviceService.deleteCarServiceById(id);
	}

	@GetMapping("/services")
	public ResponseEntity<ResponseStructure<List<CarServiceResponseDTO>>> findAllCarServices() {
		return serviceService.findAllCarServices();
	}
}
