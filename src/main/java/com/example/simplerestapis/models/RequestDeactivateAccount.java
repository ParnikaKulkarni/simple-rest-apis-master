package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestDeactivateAccount {
	
	@ApiModelProperty(notes = "name of user for whom account has to be deactivated", name = "name", required = true)
    private String name;
	
	@ApiModelProperty(notes = "email of user for whom account has to be deactivated", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom account has to be deactivated", name = "password", required = true)
    private String password;


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
