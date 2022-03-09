package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestViewDetails {
	
	@ApiModelProperty(notes = "email of user for whom account details have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom account details have to be displayed", name = "password", required = true)
    private String password;

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
