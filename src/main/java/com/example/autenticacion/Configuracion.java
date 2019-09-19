package com.example.autenticacion;

import com.example.autenticacion.DAO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class Configuracion {

    @Autowired
    private MongoOperations mongoOperations;

    @Bean
    public void inicializarDatos(){
        User user = new User("user","12345");
        mongoOperations.save(user,"user");
    }


}
