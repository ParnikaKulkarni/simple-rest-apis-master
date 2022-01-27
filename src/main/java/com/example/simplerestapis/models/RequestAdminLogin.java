package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestAdminLogin {
	@ApiModelProperty(notes = "username of admin for whom login has to be validated", name = "aname", required = true)
    private String aname;
    
    @ApiModelProperty(notes = "password of admin for whom login has to be validated", name = "apassword", required = true)
    private String apassword;

    public String getAName(){
        return this.aname;
    }

    public void setAName(String aname){
        this.aname = aname;
    }
    
    public String getAPassword(){
        return this.apassword;
    }

    public void setAPassword(String apassword){
        this.apassword = apassword;
    }
}
