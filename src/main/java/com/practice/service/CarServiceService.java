package com.practice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.entity.CarService;
import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;
import com.practice.utility.ResponseStructure;

public interface CarServiceService {

	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> addCarService(
			CarServiceRequestDTO carServiceRequestDTO);

	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> findCarServiceById(int id);

	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> updateCarServiceById(int id, CarServiceRequestDTO carServiceRequestDTO);

	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> deleteCarServiceById(int id);

	public ResponseEntity<ResponseStructure<List<CarServiceResponseDTO>>> findAllCarServices();

}
