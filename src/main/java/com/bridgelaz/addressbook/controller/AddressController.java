package com.bridgelaz.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelaz.addressbook.dto.AddressDTO;
import com.bridgelaz.addressbook.model.AddressModel;
import com.bridgelaz.addressbook.service.AddressService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/create")
    public ResponseEntity<AddressModel> createContact(@RequestBody AddressDTO dto) {
        log.info("Creating contact: {}", dto.getName());
        return ResponseEntity.ok(service.createContact(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressModel>> getAllContacts() {
        log.info("Fetching all contacts");
        return ResponseEntity.ok(service.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getContactById(@PathVariable int id) {
        log.info("Fetching contact with ID: {}", id);
        AddressModel contact = service.getContactById(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressModel> updateContact(@PathVariable int id, @RequestBody AddressDTO dto) {
        log.info("Updating contact with ID: {}", id);
        AddressModel updated = service.updateContact(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        log.info("Deleting contact with ID: {}", id);
        boolean deleted = service.deleteContact(id);
        return deleted ? ResponseEntity.ok("Deleted contact with ID: " + id) : ResponseEntity.notFound().build();
    }
}