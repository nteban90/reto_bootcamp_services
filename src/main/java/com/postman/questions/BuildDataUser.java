package com.postman.questions;


import com.postman.models.Address;
import com.postman.models.Geolocation;
import com.postman.models.Name;
import com.postman.models.UserModel;
import com.postman.utils.Data;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildDataUser implements Question<UserModel> {


    @Override
    public UserModel answeredBy(Actor actor) {

        Map<String, String> data = Data.extractTo().get(0);

         Geolocation geolocation= Geolocation.builder()
                 .lat("2")
                 ._long("3")
                 .build();

        Address address= Address.builder()
                .city("Bogota")
                .street("Carrera 54")
                .zipcode("3414")
                .geolocation(geolocation)
                .build();

        Name name= Name.builder()
                .firstname(data.get("FIRSTNAME"))
                .lastname(data.get("LASTNAME"))
                .build();

        UserModel userModel= UserModel.builder()
                .email(data.get("EMAIL"))
                .username(data.get("USERNAME"))
                .password(data.get("PASSWORD"))
                .name(name)
                .phone(data.get("PHONE"))
                .build();

        return userModel;
    }

    public static BuildDataUser was(){
        return  new BuildDataUser();
    }
}
