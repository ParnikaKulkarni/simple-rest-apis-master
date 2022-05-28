package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestStocks {
	
	@ApiModelProperty(notes = "username of user for whom stock investment options have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom stock investment options have to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "monthly salary of user for whom stock investment options have to be displayed", name = "mi", required = true)
    private double mi;
    
    @ApiModelProperty(notes = "investment amount of user for whom stock investment options have to be displayed", name = "ia", required = true)
    private double ia;
    
    @ApiModelProperty(notes = "name of stock in which user wishes to view the stock investment options", name = "sn", required = true)
    private String sn;

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
    
    public double getMI(){
        return this.mi;
    }

    public void setMI(double mi){
        this.mi = mi;
    }
    
    public double getIA(){
        return this.ia;
    }

    public void setIA(double ia){
        this.ia = ia;
    }
    
    public String getSN(){
        return this.sn;
    }

    public void setSN(String sn){
        this.sn = sn;
    }
}
