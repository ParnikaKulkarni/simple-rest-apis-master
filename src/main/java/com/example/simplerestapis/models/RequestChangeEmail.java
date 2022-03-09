package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestChangeEmail {
	
	@ApiModelProperty(notes = "current email of user for whom email has to be updated in database", name = "cemail", required = true)
    private String cemail;
    
	@ApiModelProperty(notes = "password of user for whom email has to be updated in database", name = "cpassword", required = true)
    private String password;
	
    @ApiModelProperty(notes = "current email of user has to be changed to this new email", name = "nemail", required = true)
    private String nemail;

    public String getCEmail(){
        return this.cemail;
    }

    public void setCEmail(String cemail){
        this.cemail = cemail;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public String getNEmail(){
        return this.nemail;
    }

    public void setNEmail(String nemail){
        this.nemail = nemail;
    }
}
