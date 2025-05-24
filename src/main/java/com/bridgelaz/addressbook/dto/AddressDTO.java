package com.bridgelaz.addressbook.dto;

import com.bridgelaz.addressbook.model.AddressModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String name;
    private String email;

   
}
