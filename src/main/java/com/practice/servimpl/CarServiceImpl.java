package com.practice.servimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import com.practice.entity.Car;
import com.practice.exception.CarIdNotFoundException;
import com.practice.mapper.CarMapper;
import com.practice.repository.CarRepository;
import com.practice.request.dto.CarRequestDTO;
import com.practice.response.dto.CarResponseDTO;
import com.practice.service.CarService;
import com.practice.utility.ResponseStructure;

import ch.qos.logback.core.status.Status;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarMapper carMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDTO>> addCar(CarRequestDTO carRequestDTO) {
		Car car = carMapper.mapToCar(carRequestDTO);
		Car car1 = carRepository.save(car);
		CarResponseDTO mapToCarResponse = carMapper.mapToCarResponse(car1);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<CarResponseDTO>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setData(mapToCarResponse)
						.setMessage("Car Data Added Successfully !"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDTO>> findCarById(int id) {
		return carRepository.findById(id)   //[Optional<Car>]
				.map(car -> ResponseEntity       // return statement not needed because of single line 
						.status(HttpStatus.FOUND)
						.body(new ResponseStructure<CarResponseDTO>()
								.setStatusCode(HttpStatus.FOUND.value())
								.setData(carMapper.mapToCarResponse(car))	// [Optional<ResponseStructure<CarResponse>>]
								.setMessage("Car Found")))
				.orElseThrow(() -> new CarIdNotFoundException("Car Not Found"));  // here you should not throw
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDTO>> updateCarById(int id, CarRequestDTO updatedCar) {

		return carRepository.findById(id)
				.map(existingCar -> {
					Car car = carMapper.mapToCar(updatedCar);
					car.setId(existingCar.getId());
					CarResponseDTO  carResponseDTO = carMapper.mapToCarResponse(carRepository.save(car));
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseStructure<CarResponseDTO>()
									.setStatusCode(HttpStatus.OK.value())
									.setMessage("Car Data Updated !")
									.setData(carResponseDTO));
				}).orElseThrow(()-> new CarIdNotFoundException("Car ID Not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDTO>> deleteCarById(int id) {
		return carRepository.findById(id)
				.map(car -> { carRepository.delete(car);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseStructure<CarResponseDTO>()
								.setStatusCode(HttpStatus.OK.value())
								.setData(carMapper.mapToCarResponse(car))
								.setMessage("Car Deleted"));
				})
				.orElseThrow(() -> new CarIdNotFoundException("Car Not Found"));
	}


	@Override
	public ResponseEntity<ResponseStructure<List<CarResponseDTO>>> findAllCars() {
		List<CarResponseDTO> carResponseDTOList = carRepository.findAll()
				.stream()
				.map(car -> carMapper.mapToCarResponse(car))
				.toList();
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<CarResponseDTO>>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("List of Cars Found !")
						.setData(carResponseDTOList));
	}
}

