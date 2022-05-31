package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestRealEstate {
	
	@ApiModelProperty(notes = "username of user for whom real estate investment options have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom real estate investment options have to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "total budget of user for whom real estate investment options have to be displayed", name = "budget", required = true)
    private long budget;
    
    @ApiModelProperty(notes = "2 or 3 bhk house preferred by user for whom real estate investment options have to be displayed", name = "bhk", required = true)
    private int bhk;
    
    @ApiModelProperty(notes = "name of city in which user wishes to view the real estate investment options", name = "city", required = true)
    private String city;

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
    
    public long getBudget(){
        return this.budget;
    }

    public void setBudget(long budget){
        this.budget = budget;
    }
    
    public int getBhk(){
        return this.bhk;
    }

    public void setBhk(int bhk){
        this.bhk = bhk;
    }
    
    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city = city;
    }
}
