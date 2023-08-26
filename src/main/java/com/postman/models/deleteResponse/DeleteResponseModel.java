package com.postman.models.deleteResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postman.models.Address;
import com.postman.models.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponseModel {

    @JsonProperty("email")
    public String email;
    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
    @JsonProperty("name")
    public Name name;
    @JsonProperty("address")
    public Address address;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("__v")
    public Integer __v;
}
