package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestChangePassword {
	
	@ApiModelProperty(notes = "email of user for whom password has to be updated in database", name = "email", required = true)
    private String email;
    
	@ApiModelProperty(notes = "current password which has to be updated in database", name = "cpassword", required = true)
    private String cpassword;
	
    @ApiModelProperty(notes = "current password of user has to be changed to this new password", name = "npassword", required = true)
    private String npassword;

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public String getCPassword(){
        return this.cpassword;
    }

    public void setCPassword(String cpassword){
        this.cpassword = cpassword;
    }
    
    public String getNPassword(){
        return this.npassword;
    }

    public void setNPassword(String npassword){
        this.npassword = npassword;
    }
}
