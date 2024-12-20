package com.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.api.model.Message;
import com.project.api.model.Person;
import com.project.api.repository.Repository;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Message message;

    @Autowired
    private Repository action;

    public ResponseEntity<?> register(Person obj) {
        if (obj.getName() == null || obj.getName().isEmpty()) { // Verificação para null e vazio
            message.setMessage("Name cannot be null or empty");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST); // Adicionado o retorno correto
        } else if (obj.getAge() < 0) { // Verifica se a idade é inválida
            message.setMessage("Invalid age");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            // Salva o objeto no repositório e retorna o status CREATED
            return new ResponseEntity<>(action.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> select(){
        return new ResponseEntity<>(action.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selectById(int id){
        if(action.countById(id) == 0){
            message.setMessage("Person not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(action.findById(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editPerson(Person obj){
        if(action.countById(obj.getId()) == 0){
            message.setMessage("Cannot find a person with this id");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }else if(obj.getName().equals("")){
            message.setMessage("Name cannot be empty or null");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(obj.getAge() < 0){
            message.setMessage("Invalid age");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(action.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deletePerson(int id){
        if(action.countById(id) != 0){
            Person obj = action.findById(id);
            action.delete(obj);
            message.setMessage("Person removed with sucess");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            message.setMessage("Person not found");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}