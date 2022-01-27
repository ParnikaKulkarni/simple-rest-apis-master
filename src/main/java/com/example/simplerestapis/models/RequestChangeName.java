package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestChangeName {
	
	@ApiModelProperty(notes = "current name of user for whom name has to be updated in database", name = "cname", required = true)
    private String cname;
    
	@ApiModelProperty(notes = "password of user for whom name has to be updated in database", name = "cpassword", required = true)
    private String cpassword;
	
    @ApiModelProperty(notes = "current name of user has to be changed to this new name", name = "nname", required = true)
    private String nname;

    public String getCName(){
        return this.cname;
    }

    public void setCName(String cname){
        this.cname = cname;
    }
    
    public String getCPassword(){
        return this.cpassword;
    }

    public void setCPassword(String cpassword){
        this.cpassword = cpassword;
    }
    
    public String getNName(){
        return this.nname;
    }

    public void setNName(String nname){
        this.nname = nname;
    }
}
