package com.project.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.api.model.Client;
import com.project.api.model.Person;
import com.project.api.repository.Repository;
import com.project.api.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;

@RestController
public class Controller {
    
    @Autowired
    private Repository acao;

    @Autowired
    private Service service;

    @PostMapping("/api")
    public ResponseEntity<?> register(@RequestBody Person obj){
        return service.register(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> select(){
        return service.select();
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return service.selectById(id);
    }
    
    @PutMapping("/api")
    public ResponseEntity<?> editPerson(@RequestBody Person obj){
        return service.editPerson(obj);
    }

    @DeleteMapping("/api/{id}")
    public void remove(@PathVariable int id){
        service.deletePerson(id);
    }

    @GetMapping("/api/count")
    public long count(){
        return acao.count();
    }

    @GetMapping("/api/orderName")
    public List<Person> orderByName(){
        return acao.findByOrderByName();
    }

    @GetMapping("/api/orderAge")
    public List<Person> orderByAge(){
        return acao.findByOrderByAge();
    }

    @GetMapping("/api/orderNameAge")
    public List<Person> orderByNameAndAge(){
        return acao.findByNameOrderByAgeDesc("Lucas");
    }

    @GetMapping("/search/containing/{term}")
    public List<Person> containingName(@PathVariable String term){

        return acao.findByNameContaining(term);
    }

    @GetMapping("/api/sumAges")
    public int sumAge(){
        return acao.sumAges();
    }

    @GetMapping("/search/ages/{age}")
    public List<Person> searchAgeOrHigher(@PathVariable int age){
        return acao.searchAgeOrHigher(age);
    } 

    public String mensagem(){
        return "Hello World.";
    }

    @GetMapping("/welcome")    
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/person")
    public Person person(@RequestBody Person p){
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public void client(@Valid @RequestBody Client obj){

    }
}
