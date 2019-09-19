package com.example.autenticacion.controladores;

import com.example.autenticacion.DAO.User;
import com.example.autenticacion.DTO.Respuesta;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private MongoOperations mongoOperations;

    @GetMapping()
    public ResponseEntity<Respuesta> autenticacion(
            @RequestHeader(value = "XUsuario") String usuario,
            @RequestHeader(value = "XPassword") String contraseña
    ){
        if (isValidUser(usuario,contraseña)){
            JwtBuilder builder = Jwts.builder()
                    .setId(usuario);
            return ResponseEntity
                    .ok()
                    .body(new Respuesta(0,builder.compact()));
        }
        return ResponseEntity
                .badRequest()
                .body(new Respuesta(1,""));
    }


    private Boolean isValidUser(String usuario,String contraseña){
        Query query = Query
                .query(
                        Criteria
                                .where("usuario")
                                .is(usuario)
                                .and("password")
                                .is(contraseña)
                );

        if(mongoOperations.exists(query, User.class)){
            return Boolean.TRUE;
        }

        return  Boolean.FALSE;
    }


}
