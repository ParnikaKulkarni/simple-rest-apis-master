package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestChangeName {
	
	@ApiModelProperty(notes = "current name of user for whom name has to be updated in database", name = "cname", required = true)
    private String cname;
	
	@ApiModelProperty(notes = "email of user for whom name has to be updated in database", name = "email", required = true)
    private String email;
    
	@ApiModelProperty(notes = "password of user for whom name has to be updated in database", name = "password", required = true)
    private String password;
	
    @ApiModelProperty(notes = "current name of user has to be changed to this new name", name = "nname", required = true)
    private String nname;

    public String getCName(){
        return this.cname;
    }

    public void setCName(String cname){
        this.cname = cname;
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
    
    public String getNName(){
        return this.nname;
    }

    public void setNName(String nname){
        this.nname = nname;
    }
}
