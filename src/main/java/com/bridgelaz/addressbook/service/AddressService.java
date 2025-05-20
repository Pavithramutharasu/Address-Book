package com.bridgelaz.addressbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelaz.addressbook.dto.AddressDTO;
import com.bridgelaz.addressbook.mapper.AddressMapper;
import com.bridgelaz.addressbook.model.AddressModel;

@Service
public class AddressService {
	private final List<AddressModel> contactList = new ArrayList<>();
    private final AddressMapper mapper = new AddressMapper();
    private int idCounter = 1;

    public AddressModel createContact(AddressDTO dto) {
        AddressModel contact = mapper.dtoToModel(idCounter++, dto);
        contactList.add(contact);
        return contact;
    }

    public List<AddressModel> getAllContacts() {
        return contactList;
    }

    public AddressModel getContactById(int id) {
        return contactList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public AddressModel updateContact(int id, AddressDTO dto) {
        for (AddressModel contact : contactList) {
            if (contact.getId() == id) {
                contact.setName(dto.getName());
                contact.setEmail(dto.getEmail());
                return contact;
            }
        }
        return null;
    }

    public boolean deleteContact(int id) {
        return contactList.removeIf(contact -> contact.getId() == id);
    }
}
