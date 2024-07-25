package com.practice.servimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.entity.CarService;
import com.practice.exception.CarServiceIdNotFoundException;
import com.practice.mapper.CarServiceMapper;
import com.practice.repository.CarServiceRepository;
import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;
import com.practice.service.CarServiceService;
import com.practice.utility.ResponseStructure;

@Service
public class CarServiceServiceImpl implements CarServiceService {

	@Autowired
	private CarServiceRepository carServiceRepository;

	@Autowired
	private CarServiceMapper carServiceMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> addCarService(CarServiceRequestDTO carServiceRequestDTO) {
		CarService carService = carServiceMapper.mapToCarService(carServiceRequestDTO);
		CarService carService1 = carServiceRepository.save(carService);
		CarServiceResponseDTO mapToCarServiceResponseDTO = carServiceMapper.mapToCarServiceResponse(carService1);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<CarServiceResponseDTO>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setData(mapToCarServiceResponseDTO)
						.setMessage("Car Service Data Added Successfully !"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> findCarServiceById(int id) {
		return carServiceRepository.findById(id)   //[Optional<CarService>]
				.map(carService -> ResponseEntity       // return statement not needed because of single line 
						.status(HttpStatus.FOUND)
						.body(new ResponseStructure<CarServiceResponseDTO>()
								.setStatusCode(HttpStatus.FOUND.value())
								.setData(carServiceMapper.mapToCarServiceResponse(carService))	// [Optional<ResponseStructure<CarServiceResponse>>]
								.setMessage("Car Service Found!")))
				.orElseThrow(() -> new CarServiceIdNotFoundException("Car Service Not Found!"));  // here you should not throw

	}

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> updateCarServiceById(int id, CarServiceRequestDTO updatedCarServiceRequestDTO) {

		return carServiceRepository.findById(id)
				.map(existingCarService -> {
					CarService carService = carServiceMapper.mapToCarService(updatedCarServiceRequestDTO);
					carService.setId(existingCarService.getId());
					CarServiceResponseDTO  carServiceResponseDTO = carServiceMapper.mapToCarServiceResponse(carServiceRepository.save(carService));
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseStructure<CarServiceResponseDTO>()
									.setStatusCode(HttpStatus.OK.value())
									.setMessage("Car Service Data Updated !")
									.setData(carServiceResponseDTO));
				}).orElseThrow(()-> new CarServiceIdNotFoundException("Car Service ID Not Found !"));

	}


	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponseDTO>> deleteCarServiceById(int id) {
		return carServiceRepository.findById(id)
				.map(carService -> { carServiceRepository.delete(carService);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseStructure<CarServiceResponseDTO>()
								.setStatusCode(HttpStatus.OK.value())
								.setData(carServiceMapper.mapToCarServiceResponse(carService))
								.setMessage("Car Service Deleted !"));
				})
				.orElseThrow(() -> new CarServiceIdNotFoundException("Car Service ID Not Found !"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarServiceResponseDTO>>> findAllCarServices() {
		List<CarServiceResponseDTO> carServiceResponseDTOList = carServiceRepository.findAll()
				.stream()
				.map(carService -> carServiceMapper.mapToCarServiceResponse(carService))
				.toList();
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<CarServiceResponseDTO>>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("List of Cars Found !")
						.setData(carServiceResponseDTOList));
	}
}