package com.bridgelaz.addressbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelaz.addressbook.dto.AddressDTO;
import com.bridgelaz.addressbook.mapper.AddressMapper;
import com.bridgelaz.addressbook.model.AddressModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressService {

    private final List<AddressModel> contactList = new ArrayList<>();
    private final AddressMapper mapper = new AddressMapper();
    private int idCounter = 1;

    public AddressModel createContact(AddressDTO dto) {
        log.info("Creating new contact for: {}", dto.getName());
        AddressModel contact = mapper.dtoToModel(idCounter++, dto);
        contactList.add(contact);
        log.debug("Contact added to memory: {}", contact);
        return contact;
    }

    public List<AddressModel> getAllContacts() {
        log.info("Fetching all contacts");
        return contactList;
    }

    public AddressModel getContactById(int id) {
        log.info("Fetching contact with ID: {}", id);
        return contactList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public AddressModel updateContact(int id, AddressDTO dto) {
        log.info("Updating contact with ID: {}", id);
        for (AddressModel contact : contactList) {
            if (contact.getId() == id) {
                log.debug("Before update: {}", contact);
                contact.setName(dto.getName());
                contact.setEmail(dto.getEmail());
                log.debug("After update: {}", contact);
                return contact;
            }
        }
        log.warn("Contact with ID {} not found for update", id);
        return null;
    }

    public boolean deleteContact(int id) {
        log.info("Deleting contact with ID: {}", id);
        boolean removed = contactList.removeIf(contact -> contact.getId() == id);
        if (removed) {
            log.info("Contact with ID {} deleted successfully", id);
        } else {
            log.warn("Contact with ID {} not found for deletion", id);
        }
        return removed;
    }
}