package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Employe;

@Repository
public interface EmployesRepository extends JpaRepository<Employe, Integer>{

}
