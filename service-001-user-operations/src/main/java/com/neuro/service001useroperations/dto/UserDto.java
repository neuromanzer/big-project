package com.neuro.service001useroperations.dto;

import com.neuro.service001useroperations.model.Address;
import com.neuro.service001useroperations.model.Authority;
import com.neuro.service001useroperations.security.EncryptionAlgorithm;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;

    private String uid;

    private String name;

    private String email;

    private String password;

    private String matchedPassword;

    private Address address;

    private EncryptionAlgorithm algorithm;

    private List<Authority> authorities;

    //@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    //private String ip;
}
