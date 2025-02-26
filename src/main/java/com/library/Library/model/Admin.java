package com.library.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
public class Admin {

    private Long id;

    private String name;

    private String email;

    private String password;
}
