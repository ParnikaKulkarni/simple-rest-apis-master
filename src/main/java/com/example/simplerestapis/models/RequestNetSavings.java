package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestNetSavings {
	
	@ApiModelProperty(notes = "username of user for whom net savings have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom net savings have to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "monthly salary of user for whom net savings have to be displayed", name = "salary", required = true)
    private long salary;
    
    @ApiModelProperty(notes = "average monthly expenditure of user for whom net savings have to be displayed", name = "expenditure", required = true)
    private long expenditure;

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
    
    public long getSalary(){
        return this.salary;
    }

    public void setSalary(long salary){
        this.salary = salary;
    }
    
    public long getExpenditure(){
        return this.expenditure;
    }

    public void setExpenditure(long expenditure){
        this.expenditure = expenditure;
    }
}
