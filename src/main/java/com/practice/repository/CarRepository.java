package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
