package com.example.autenticacion.DAO;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Document(collection = "user")
public class User {

    private String usuario;
    private String password;

}
