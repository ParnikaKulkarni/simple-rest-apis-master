package com.example.simplerestapis.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestExpenditure {
	
	@ApiModelProperty(notes = "username of user for whom average expenditures have to be displayed", name = "email", required = true)
    private String email;
    
    @ApiModelProperty(notes = "password of user for whom average expenditures have to be displayed", name = "password", required = true)
    private String password;
    
    @ApiModelProperty(notes = "annual salary of user for whom average expenditures have to be displayed", name = "salary", required = true)
    private long salary;
    
//    @ApiModelProperty(notes = "annual salary of user for whom average expenditures have to be displayed", name = "asalary", required = true)
//    private String asalary;

//    @ApiModelProperty(notes = "monthly rent/housing expenditure of user for whom average expenditures have to be displayed", name = "re", required = true)
//    private String re;
    
    @ApiModelProperty(notes = "monthly rent/housing expenditure of user for whom average expenditures have to be displayed", name = "rent", required = true)
    private long rent;
    
    
//    @ApiModelProperty(notes = "monthly transportation expenditure of user for whom average expenditures have to be displayed", name = "transporte", required = true)
//    private String transporte;
    
    @ApiModelProperty(notes = "monthly transportation expenditure of user for whom average expenditures have to be displayed", name = "transport", required = true)
    private long transport;
    
//    @ApiModelProperty(notes = "monthly insurance expenditure of user for whom average expenditures have to be displayed", name = "insurancee", required = true)
//    private String insurancee;
    
    @ApiModelProperty(notes = "monthly insurance expenditure of user for whom average expenditures have to be displayed", name = "insurance", required = true)
    private long insurance;
       
//    @ApiModelProperty(notes = "monthly food/groceries expenditure of user for whom average expenditures have to be displayed", name = "foode", required = true)
//    private String foode;
    
    @ApiModelProperty(notes = "monthly food/groceries expenditure of user for whom average expenditures have to be displayed", name = "food", required = true)
    private long food;
    
//    @ApiModelProperty(notes = "monthly cell phone bill of user for whom average expenditures have to be displayed", name = "celle", required = true)
//    private String celle;
//    
//    @ApiModelProperty(notes = "monthly cell phone bill of user for whom average expenditures have to be displayed", name = "cell", required = true)
//    private String cell;
    
    @ApiModelProperty(notes = "monthly cell phone bill of user for whom average expenditures have to be displayed", name = "ce", required = true)
    private long ce;
    
//    @ApiModelProperty(notes = "monthly utility expenditures of user for whom average expenditures have to be displayed", name = "utilitye", required = true)
//    private String utilitye;
    
    @ApiModelProperty(notes = "monthly utility expenditures of user for whom average expenditures have to be displayed", name = "utility", required = true)
    private long utility;
    
//    @ApiModelProperty(notes = "monthly travel expenditures of user for whom average expenditures have to be displayed", name = "travele", required = true)
//    private String travele;
    
    @ApiModelProperty(notes = "monthly travel expenditures of user for whom average expenditures have to be displayed", name = "travel", required = true)
    private long travel;
    
//    @ApiModelProperty(notes = "monthly personal upkeep expenditures of user for whom average expenditures have to be displayed", name = "personale", required = true)
//    private String personale;
    
    @ApiModelProperty(notes = "monthly personal upkeep expenditures of user for whom average expenditures have to be displayed", name = "personal", required = true)
    private long personal;
    
//    @ApiModelProperty(notes = "monthly loan repayment expenditures of user for whom average expenditures have to be displayed", name = "loane", required = true)
//    private String loane;
    
    @ApiModelProperty(notes = "monthly loan repayment expenditures of user for whom average expenditures have to be displayed", name = "loan", required = true)
    private long loan;
    
//    @ApiModelProperty(notes = "monthly child care expenditure of user for whom average expenditures have to be displayed", name = "childe", required = true)
//    private String childe;
//    
//    @ApiModelProperty(notes = "monthly child care expenditure of user for whom average expenditures have to be displayed", name = "child", required = true)
//    private String child;
    
    @ApiModelProperty(notes = "monthly child care expenditure of user for whom average expenditures have to be displayed", name = "ce", required = true)
    private long cce;
    
//    @ApiModelProperty(notes = "monthly other expenditures of user for whom average expenditures have to be displayed", name = "othere", required = true)
//    private String othere;
    
    @ApiModelProperty(notes = "monthly other expenditures of user for whom average expenditures have to be displayed", name = "other", required = true)
    private long other;

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
    
    public long getRent(){
        return this.rent;
    }

    public void setRent(long rent){
        this.rent = rent;
    }

    public long getTransport(){
        return this.transport;
    }

    public void setTransport(long transport){
        this.transport = transport;
    }

    public long getInsurance(){
        return this.insurance;
    }

    public void setInsurance(long insurance){
        this.insurance = insurance;
    }
    
    public long getFood(){
        return this.food;
    }

    public void setFood(long food){
        this.food = food;
    }
    
    public long getCE(){
        return this.ce;
    }

    public void setCE(long ce){
        this.ce = ce;
    }
    
    public long getUtility(){
        return this.utility;
    }

    public void setUtility(long utility){
        this.utility = utility;
    }
    
    public long getTravel(){
        return this.travel;
    }

    public void setTravel(long travel){
        this.travel = travel;
    }
    
    public long getPersonal(){
        return this.personal;
    }

    public void setPersonal(long personal){
        this.personal = personal;
    }
    
    public long getLoan(){
        return this.loan;
    }

    public void setLoan(long loan){
        this.loan = loan;
    }
    
    public long getCCE(){
        return this.cce;
    }

    public void setCCE(long cce){
        this.cce = cce;
    }
    
    public long getOther(){
        return this.other;
    }

    public void setOther(long other){
        this.other = other;
    }
}
