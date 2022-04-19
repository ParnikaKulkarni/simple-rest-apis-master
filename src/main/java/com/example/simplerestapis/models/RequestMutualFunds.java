package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestMutualFunds {
	
	@ApiModelProperty(notes = "email of user for whom mutual funds investment gain has to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom mutual funds investment gain has to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "investment amount by user for whom mutual funds investment gain has to be displayed", name = "iamount", required = true)
    private long iamount;
    
    @ApiModelProperty(notes = "monthly income of user for whom mutual funds investment gain has to be displayed", name = "mincome", required = true)
    private long mincome;
    
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
    
    public long getIAmount(){
        return this.iamount;
    }

    public void setIAmount(long iamount){
        this.iamount = iamount;
    }
    
    public long getMIncome(){
        return this.mincome;
    }

    public void setMIncome(long mincome){
        this.mincome = mincome;
    }
}
