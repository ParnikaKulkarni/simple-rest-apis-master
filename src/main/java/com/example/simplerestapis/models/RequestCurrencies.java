package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestCurrencies {
	
	@ApiModelProperty(notes = "username of user for whom savings have to be displayed in different currency", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom savings have to be displayed in different currency", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "total savings of user for whom savings have to be displayed in different currency", name = "savings", required = true)
    private long savings;
    
    @ApiModelProperty(notes = "currency name in which user's total savings have to be displayed", name = "currency", required = true)
    private String currency;

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
    
    public String getCurrency(){
        return this.currency;
    }

    public void setCuurency(String currency){
        this.currency = currency;
    }
}
