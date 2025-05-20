package com.bridgelaz.addressbook.mapper;

import com.bridgelaz.addressbook.dto.AddressDTO;
import com.bridgelaz.addressbook.model.AddressModel;

public class AddressMapper {
	public AddressModel dtoToModel(int id, AddressDTO dto) {
        return new AddressModel(id, dto.getName(), dto.getEmail());
    }

    public AddressDTO modelToDto(AddressModel model) {
        return new AddressDTO(model.getName(), model.getEmail());
    }

}
