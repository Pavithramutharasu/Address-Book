package com.bridgelaz.addressbook.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.bridgelaz.addressbook.mapper.AddressMapper;
import com.bridgelaz.addressbook.model.AddressModel;

@RestController
@RequestMapping("/addressbook")
public class AddressController {
	private final List<AddressModel> contactList = new ArrayList<>();
    private final AddressMapper mapper = new AddressMapper();
    private int idCounter = 1;

    @PostMapping("/create")
    public ResponseEntity<AddressModel> createContact(@RequestBody AddressDTO dto) {
        AddressModel model = mapper.dtoToModel(idCounter++, dto);
        contactList.add(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressModel>> getAllContacts() {
        return ResponseEntity.ok(contactList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getContactById(@PathVariable int id) {
        return contactList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressModel> updateContact(@PathVariable int id, @RequestBody AddressDTO dto) {
        for (AddressModel contact : contactList) {
            if (contact.getId() == id) {
                contact.setName(dto.getName());
                contact.setEmail(dto.getEmail());
                return ResponseEntity.ok(contact);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        boolean removed = contactList.removeIf(c -> c.getId() == id);
        return removed
                ? ResponseEntity.ok("Deleted contact with ID: " + id)
                : ResponseEntity.notFound().build();
    }

}
