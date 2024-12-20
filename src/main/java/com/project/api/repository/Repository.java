package com.project.api.repository;

import com.project.api.model.Person;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface Repository extends CrudRepository<Person, Integer>{
    
    //List<Person> findAll();

    Person findById(int id);

    List<Person> findByOrderByName();
    List<Person> findByOrderByAge();

    List<Person> findByNameOrderByAgeDesc(String name);

    List<Person> findByNameContaining(String word);

    @Query(value = "SELECT SUM(age) FROM persons", nativeQuery = true)
    int sumAges();

    @Query(value = "SELECT * FROM persons WHERE age>= :age", nativeQuery = true)
    List<Person> searchAgeOrHigher(int age);

    @Query(value = "SELECT COUNT(*) FROM persons WHERE id = :id", nativeQuery = true)
    int countById(int id);

}
