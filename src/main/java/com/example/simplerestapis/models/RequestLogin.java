package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;


public class RequestLogin {
	@ApiModelProperty(notes = "username of user for whom login has to be validated", name = "name", required = true)
    private String name;
    
    @ApiModelProperty(notes = "password of user for whom login has to be validated", name = "password", required = true)
    private String password;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
