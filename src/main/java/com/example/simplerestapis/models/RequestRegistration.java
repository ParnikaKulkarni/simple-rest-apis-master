package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestRegistration {
	
	@ApiModelProperty(notes = "name of user for whom registration has to be done", name = "name", required = true)
    private String name;
	
	@ApiModelProperty(notes = "email of user for whom registration has to be done", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom registration has to be done", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "confirm password of user for whom registration has to be done", name = "cpassword", required = true)
    private String cpassword;
    
//    @ApiModelProperty(notes = "confirm password of user for whom registration has to be done", name = "confirmpassword", required = true)
//    private String confirmpassword;
    
    @ApiModelProperty(notes = "account number of user for whom registration has to be done", name = "accountno", required = true)
    private String accountno;
    
//    @ApiModelProperty(notes = "ifsc code associated with account of user for whom registration has to be done", name = "ifsccode", required = true)
//    private String ifsccode;
    
    @ApiModelProperty(notes = "ifsc code associated with account of user for whom registration has to be done", name = "ifsc", required = true)
    private String ifsc;
    
    @ApiModelProperty(notes = "current balance in account of user for whom registration has to be done", name = "cbalance", required = true)
    private String cbalance;
    
    @ApiModelProperty(notes = "month in which registration is being done", name = "month", required = true)
    private int month;
    
    @ApiModelProperty(notes = "year in which registration is being done", name = "year", required = true)
    private int year;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
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
    
    public String getCPassword(){
        return this.cpassword;
    }

    public void setCPassword(String cpassword){
        this.cpassword = cpassword;
    }
    
//    public String getConfirmPassword(){
//    	return this.confirmpassword;
//    }
//
//    public void setConfirmPassword(String confirmpassword){
//        this.confirmpassword = confirmpassword;
//    }
    
    public String getAccountno(){
        return this.accountno;
    }

    public void setAccountno(String accountno){
        this.accountno = accountno;
    }
    
//    public String getIFSC(){
//        return this.ifsccode;
//    }
//
//    public void setIFSC(String ifsccode){
//        this.ifsccode = ifsccode;
//    }
    
    public String getIFSCCode(){
        return this.ifsc;
    }

    public void setIFSCCode(String ifsc){
        this.ifsc = ifsc;
    }
    
    public String getCBalance(){
        return this.cbalance;
    }

    public void setCBalance(String cbalance){
        this.cbalance = cbalance;
    }
    
    public int getMonth(){
        return this.month;
    }

    public void setMonth(int month){
        this.month = month;
    }
    
    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        this.year = year;
    }
}
