package com.neuro.service001useroperations.model;

import com.neuro.service001useroperations.security.EncryptionAlgorithm;
import com.neuro.service001useroperations.validation.ValidateEmail;
import com.neuro.service001useroperations.validation.ValidatePassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ValidatePassword
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    @NotEmpty(message = "name must not be null or empty")
    @Size(max = 10, message = "name must be max 10 characters")
    private String name;

    @NotEmpty(message = "email must not be null or empty")
    @ValidateEmail
    private String email;

    @NotEmpty(message = "password must not be null or empty")
    private String password;

    @NotEmpty(message = "matchedPassword must not be null or empty")
    private String matchedPassword;

    @Valid
    @OneToOne(cascade = CascadeType.REMOVE)
    private Address address;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(/*mappedBy = "user", */fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
