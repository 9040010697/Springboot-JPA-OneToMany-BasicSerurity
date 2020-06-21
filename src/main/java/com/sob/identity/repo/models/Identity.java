package com.sob.identity.repo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Identity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String identity_id;
    private String role;
    private String companyName;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToMany(targetEntity = Address.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "identity_id", referencedColumnName = "identity_id")
    private List<Address> address;

}
