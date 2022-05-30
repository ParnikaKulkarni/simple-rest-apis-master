package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestBonds {
	
	@ApiModelProperty(notes = "username of user for whom bond investment options have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom bond investment options have to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "total savings of user for whom bond investment options have to be displayed", name = "savings", required = true)
    private long savings;
    
    @ApiModelProperty(notes = "investment amount of user for whom bond investment options have to be displayed", name = "ia", required = true)
    private long ia;
    
    @ApiModelProperty(notes = "name of bond in which user wishes to view the bond investment options", name = "bn", required = true)
    private String bn;

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
    
    public long getSavings(){
        return this.savings;
    }

    public void setSavings(long savings){
        this.savings = savings;
    }
    
    public long getIA(){
        return this.ia;
    }

    public void setIA(long ia){
        this.ia = ia;
    }
    
    public String getBN(){
        return this.bn;
    }

    public void setBN(String bn){
        this.bn = bn;
    }
}
