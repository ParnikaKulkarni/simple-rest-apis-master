package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestRegistration {
	
	@ApiModelProperty(notes = "name of user for whom registration has to be done", name = "name", required = true)
    private String name;
	
	@ApiModelProperty(notes = "email of user for whom registration has to be done", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom registration has to be done", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "confirm password of user for whom registration has to be done", name = "cpassword", required = true)
    private String cpassword;
    
//    @ApiModelProperty(notes = "confirm password of user for whom registration has to be done", name = "confirmpassword", required = true)
//    private String confirmpassword;

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
    
    public String getCPassword(){
        return this.cpassword;
    }

    public void setCPassword(String cpassword){
        this.cpassword = cpassword;
    }
    
//    public String getConfirmPassword(){
//    	return this.confirmpassword;
//    }
//
//    public void setConfirmPassword(String confirmpassword){
//        this.confirmpassword = confirmpassword;
//    }

}
