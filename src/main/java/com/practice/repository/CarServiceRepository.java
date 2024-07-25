package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.entity.CarService;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Integer> {

}
