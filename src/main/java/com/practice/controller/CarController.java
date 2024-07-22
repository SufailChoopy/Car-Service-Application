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

import com.practice.entity.Car;
import com.practice.request.dto.CarRequestDTO;
import com.practice.response.dto.CarResponseDTO;
import com.practice.service.CarService;
import com.practice.utility.ResponseStructure;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/cars")
	public ResponseEntity<ResponseStructure<CarResponseDTO>> addCar(@RequestBody CarRequestDTO carRequestDTO) {
		return carService.addCar(carRequestDTO);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<ResponseStructure<CarResponseDTO>> findCar(@PathVariable int id) {
		return carService.findCarById(id);
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<ResponseStructure<CarResponseDTO>> updateCarById(@PathVariable int id, @RequestBody CarRequestDTO carRequestDTO) {
		return carService.updateCarById(id, carRequestDTO);
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<ResponseStructure<CarResponseDTO>> deleteCarById(@PathVariable int id) {
		return carService.deleteCarById(id);
	}

	@GetMapping("/cars")
	public ResponseEntity<ResponseStructure<List<CarResponseDTO>>> findAllCars() {
		return carService.findAllCars();
	}
}
