package com.practice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.entity.Car;
import com.practice.request.dto.CarRequestDTO;
import com.practice.response.dto.CarResponseDTO;
import com.practice.utility.ResponseStructure;

public interface CarService {

	public ResponseEntity<ResponseStructure<CarResponseDTO>> addCar(CarRequestDTO carRequestDTO);

	public ResponseEntity<ResponseStructure<CarResponseDTO>> findCarById(int id);

	public ResponseEntity<ResponseStructure<CarResponseDTO>> updateCarById(int id, CarRequestDTO carRequestDTO);

	public ResponseEntity<ResponseStructure<CarResponseDTO>> deleteCarById(int id);

	public ResponseEntity<ResponseStructure<List<CarResponseDTO>>> findAllCars();

}
