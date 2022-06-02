package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestGoals {
	
	@ApiModelProperty(notes = "username of user for whom goal setting has to be done", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom goal setting has to be done", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "savings for down payment/initial investment of user for whom goal setting has to be done", name = "savings", required = true)
    private long savings;
    
    @ApiModelProperty(notes = "goal name for which goal setting has to be done for user", name = "goal", required = true)
    private String goal;
    
    @ApiModelProperty(notes = "monthly income of user for whom goal setting has to be done", name = "mincome", required = true)
    private long mincome;
    
    @ApiModelProperty(notes = "price for goal of user for whom goal setting has to be done", name = "price", required = true)
    private long price;

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
    
    public String getGoal(){
        return this.goal;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }
    
    public long getMIncome(){
        return this.mincome;
    }

    public void setMIncome(long mincome){
        this.mincome = mincome;
    }
    
    public long getPrice(){
        return this.price;
    }

    public void setPrice(long price){
        this.price = price;
    }
}
