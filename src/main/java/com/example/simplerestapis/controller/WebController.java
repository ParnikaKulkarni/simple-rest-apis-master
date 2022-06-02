package com.example.simplerestapis.controller;

import java.lang.module.Configuration;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.DecimalFormat;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplerestapis.models.RequestAdminLogin;
import com.example.simplerestapis.models.RequestBonds;
import com.example.simplerestapis.models.RequestChangeEmail;
import com.example.simplerestapis.models.RequestChangeName;
import com.example.simplerestapis.models.RequestChangePassword;
import com.example.simplerestapis.models.RequestCurrencies;
import com.example.simplerestapis.models.RequestDeactivateAccount;
import com.example.simplerestapis.models.RequestDeleteAccount;
import com.example.simplerestapis.models.RequestExpenditure;
import com.example.simplerestapis.models.RequestGoals;
import com.example.simplerestapis.models.RequestLogin;
import com.example.simplerestapis.models.RequestLogs;
import com.example.simplerestapis.models.RequestMutualFunds;
import com.example.simplerestapis.models.RequestNetSavings;
import com.example.simplerestapis.models.RequestProvidentFunds;
import com.example.simplerestapis.models.RequestReactivateAccount;
import com.example.simplerestapis.models.RequestRealEstate;
import com.example.simplerestapis.models.RequestRegistration;
import com.example.simplerestapis.models.RequestStocks;
import com.example.simplerestapis.models.RequestViewDetails;
import com.example.simplerestapis.service.DatabaseConnection;

import com.intrinio.api.*;
import com.intrinio.models.*;
import com.intrinio.invoker.*;
import com.intrinio.invoker.auth.*;
import org.threeten.bp.*;
import java.math.BigDecimal;

import java.util.*;


import io.swagger.annotations.ApiOperation;

@ApiOperation(value = "/api1", tags = "Rest Apis controllers")
@RestController
public class WebController {

    
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);


    
    @ApiOperation(value = "Home page")
    @GetMapping("/api1")
    public String home(){
        return "up and running";
    }
    
    //User Login
    @ApiOperation(value = "Form submission")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/login")
    public String Login(@RequestBody RequestLogin req){
        final String email;
        final String password;
        email = req.getEmail();
        password = req.getPassword();
        
        String loginattempt="initial";
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				loginattempt="Invalid login attempt";
				System.out.println(loginattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					loginattempt="Valid login attempt";
					System.out.println(loginattempt);
					System.out.println("Email: "+rs.getString(2)+", Password: "+rs.getString(3));
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return loginattempt;
	}
    
    
    
  //User Registration
    @ApiOperation(value = "Form submission")
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/api1/registration")
    public String Registration(@RequestBody RequestRegistration req){
        final String name;
        final String email;
        final String password;
        final String cpassword;
        final String accountno;
        final String ifsc;
        //final String ifsccode;
        final String cbalance;
        final int month;
        final int year;
        //final String confirmpassword;
        name = req.getName();
        email = req.getEmail();
        //final String t = req.getConfirmPassword();
        cpassword = req.getCPassword();
        password = req.getPassword();
        //confirmpassword = req.getConfirmPassword();
        accountno=req.getAccountno();
        //ifsccode=req.getIFSC();
        ifsc=req.getIFSCCode();
        //System.out.println(ifsccode+" "+ifsc);
        cbalance=req.getCBalance();
        month=req.getMonth();
        year=req.getYear();
        
        System.out.println(cpassword+" "+password);
        String register="initial";
        
        
        try {
        if(password.contentEquals(cpassword)==false)
        {
        	register="Password and confirm password do not match";
        	System.out.println(register);
        }
        else {
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				register="Valid registration attempt.";
				con2 = DBMS.getConnection();
				String sql2="insert into registration(name, email, password, accountno, ifsc, cbalance, month, year) values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmt2=con2.prepareStatement(sql2);
				stmt2.setString(1,name);
				stmt2.setString(2,email);
				stmt2.setString(3,password);
				//stmt2.setString(4,cpassword);
				stmt2.setString(4,accountno);
				stmt2.setString(5,ifsc);
				stmt2.setString(6,cbalance);
				stmt2.setInt(7,month);
				stmt2.setInt(8,year);
				int rowchange=stmt2.executeUpdate();
				if(rowchange==0) {
					register="Registration unsuccessful";
				}
				else {
					register=register+"\n"+"New user registered successfully";
				}
				DBMS.closeConnection(stmt2, con2);
				System.out.println(register);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					register="Invalid registration attempt. User with the same email already exists.";
					System.out.println(register);
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}}}catch(NullPointerException n)
        {
			System.out.println(n.getMessage());
        }
		return register;
	}
    
    
    //Admin Login
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/adminlogin")
    public ArrayList<String> AdminLogin(@RequestBody RequestAdminLogin req){
        final String aname;
        final String apassword;
        aname = req.getAName();
        apassword = req.getAPassword();
        
        String aloginattempt="initial";
        
        //Users List
        List<String> ulist = new ArrayList<String>();
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from adminlogin where aname=? and apassword=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,aname);
			stmt.setString(2,apassword);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				aloginattempt="Invalid login attempt";
				ulist.add(aloginattempt);
				System.out.println(aloginattempt);
			}
			else {
				rs.previous();
				String userinfo="";
				while(rs.next())
				{
					aloginattempt="Valid login attempt";
					System.out.println(aloginattempt);
					System.out.println("Name: "+rs.getString(1)+", Password: "+rs.getString(2));
				}
				con2 = DBMS.getConnection();
				String sql2="select * from login;";
				PreparedStatement stmt2=con2.prepareStatement(sql2);
				ResultSet rs2=stmt2.executeQuery();
				if(rs2.next()==false)
				{
					userinfo="No users found";
					ulist.add(userinfo);
					System.out.println(userinfo);
				}
				else {
					rs2.previous();
					while(rs2.next())
					{
						userinfo="Name of User: "+rs2.getString(1)+", Password: "+rs2.getString(2);
						ulist.add(userinfo);
						System.out.println(userinfo);
					}
				}
				DBMS.closeConnection(stmt2, con2);
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return (ArrayList<String>) ulist;
		//return Response.ok((ArrayList<String>) ulist).header("Access-Control-Allow-Origin", "*").build();
		}
    
    
    
    
  //View user details
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/viewdetails")
    public ArrayList<String> ViewDetails(@RequestBody RequestViewDetails req){
        final String email;
        final String password;
        email = req.getEmail();
        password = req.getPassword();
        
        String viewattempt="initial";
        
        //Details List
        List<String> dlist = new ArrayList<String>();
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				viewattempt="No such user exists.";
				dlist.add(viewattempt);
				System.out.println(viewattempt);
			}
			else {
				rs.previous();
				String userinfo="";
				while(rs.next())
				{
					viewattempt="This user exists in the system.";
					dlist.add(viewattempt);
					
					//name
					userinfo=rs.getString(1);
					dlist.add(userinfo);
					System.out.println(viewattempt);
					//System.out.println(userinfo);
					
					//email
					userinfo=rs.getString(2);
					dlist.add(userinfo);
					
					//account no
					userinfo=rs.getString(4);
					dlist.add(userinfo);
					
					//ifsc code
					userinfo=rs.getString(5);
					dlist.add(userinfo);
					
					//account balance at the time of registration
					userinfo=rs.getString(6);
					dlist.add(userinfo);
					
					//month of registration
					userinfo=rs.getString(7);
					dlist.add(userinfo);
					
					//year of registration
					userinfo=rs.getString(8);
					dlist.add(userinfo);
					
					//System.out.println(userinfo);
					//System.out.println("Name: "+rs.getString(1)+", Email: "+rs.getString(2));
				}
				
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		System.out.println("Printing user details list");
	    for(String detail:dlist)
	    {
	    	System.out.print(detail+"\n");
	    }
		return (ArrayList<String>) dlist;
		}
    
    
    
    
    
  //Delete account
    @ApiOperation(value = "Form submission")
    @DeleteMapping("/api1/deleteaccount")
    public String DeleteAccount(@RequestBody RequestDeleteAccount req){
        final String name;
        final String email;
        final String password;
        name = req.getName();
        email = req.getEmail();
        password = req.getPassword();
        
        
        String deleteattempt="initial";
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where name=? and email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.setString(2,email);
			stmt.setString(3,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				deleteattempt="No such account exists. Account deletion unsuccessful.";
				System.out.println(deleteattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					deleteattempt="This account exists in the system.";
					con2 = DBMS.getConnection();
					String sql2="delete from registration where name=? and email=? and password=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,name);
					stmt2.setString(2,email);
					stmt2.setString(3,password);
					int rowchange=stmt2.executeUpdate();
					if(rowchange==0) {
						deleteattempt="Account deletion unsuccessful.";
					}
					else {
						deleteattempt=deleteattempt+"\n"+"Account deletion successful.";
					}
					DBMS.closeConnection(stmt2, con2);
					System.out.println(deleteattempt);
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return deleteattempt;
	}
    
    
    
  //Deactivate account
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/deactivateaccount")
    public String DeactivateAccount(@RequestBody RequestDeactivateAccount req){
        final String name;
        final String email;
        final String password;
        String accountno;
        String ifsc;
        String cbalance;
        int month;
        int year;
        name = req.getName();
        email = req.getEmail();
        password = req.getPassword();
        
        
        String deactivateattempt="initial";
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2, con3;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where name=? and email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.setString(2,email);
			stmt.setString(3,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				deactivateattempt="No such account exists. Account deactivation unsuccessful.";
				System.out.println(deactivateattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					deactivateattempt="This account exists in the system.";
					accountno=rs.getString(4);
					ifsc=rs.getString(5);
					cbalance=rs.getString(6);
					month=rs.getInt(7);
					year=rs.getInt(8);
					
					con2 = DBMS.getConnection();
					String sql2="insert into deactivated_accounts(name, email, password, accountno, ifsc, cbalance, month, year) values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,name);
					stmt2.setString(2,email);
					stmt2.setString(3,password);
					stmt2.setString(4,accountno);
					stmt2.setString(5,ifsc);
					stmt2.setString(6,cbalance);
					stmt2.setInt(7,month);
					stmt2.setInt(8,year);
					int rowchange=stmt2.executeUpdate();
					if(rowchange==0) {
						deactivateattempt=deactivateattempt+"\n"+"Account deactivation unsuccessful";
					}
					else {
						con3 = DBMS.getConnection();
						String sql3="delete from registration where name=? and email=? and password=?;";
						PreparedStatement stmt3=con3.prepareStatement(sql3);
						stmt3.setString(1,name);
						stmt3.setString(2,email);
						stmt3.setString(3,password);
						int rowchange2=stmt3.executeUpdate();
						if(rowchange2==0) {
							deactivateattempt="Account deactivation unsuccessful.";
						}
						else {
							deactivateattempt=deactivateattempt+"\n"+"Account deactivation successful.";
						}
						DBMS.closeConnection(stmt2, con2);
						DBMS.closeConnection(stmt3, con3);
					}
					System.out.println(deactivateattempt);
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return deactivateattempt;
	}
    
    
    
    
  //Reactivate account
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/reactivateaccount")
    public String ReactivateAccount(@RequestBody RequestReactivateAccount req){
        final String name;
        final String email;
        final String password;
        String accountno;
        String ifsc;
        String cbalance;
        int month;
        int year;
        name = req.getName();
        email = req.getEmail();
        password = req.getPassword();
        
        
        String reactivateattempt="initial";
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2, con3;
		try {
			con = DBMS.getConnection();
			String sql="select * from deactivated_accounts where name=? and email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.setString(2,email);
			stmt.setString(3,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				reactivateattempt="No such account existed. Account reactivation unsuccessful.";
				System.out.println(reactivateattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					reactivateattempt="This account had been deactivated earlier.";
					accountno=rs.getString(4);
					ifsc=rs.getString(5);
					cbalance=rs.getString(6);
					month=rs.getInt(7);
					year=rs.getInt(8);
					
					con2 = DBMS.getConnection();
					String sql2="insert into registration(name, email, password, accountno, ifsc, cbalance, month, year) values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,name);
					stmt2.setString(2,email);
					stmt2.setString(3,password);
					stmt2.setString(4,accountno);
					stmt2.setString(5,ifsc);
					stmt2.setString(6,cbalance);
					stmt2.setInt(7,month);
					stmt2.setInt(8,year);
					int rowchange=stmt2.executeUpdate();
					if(rowchange==0) {
						reactivateattempt=reactivateattempt+"\n"+"Account reactivation unsuccessful";
					}
					else {
						con3 = DBMS.getConnection();
						String sql3="delete from deactivated_accounts where name=? and email=? and password=?;";
						PreparedStatement stmt3=con3.prepareStatement(sql3);
						stmt3.setString(1,name);
						stmt3.setString(2,email);
						stmt3.setString(3,password);
						int rowchange2=stmt3.executeUpdate();
						if(rowchange2==0) {
							reactivateattempt="Account reactivation unsuccessful.";
						}
						else {
							reactivateattempt=reactivateattempt+"\n"+"Account reactivation successful.";
						}
						DBMS.closeConnection(stmt2, con2);
						DBMS.closeConnection(stmt3, con3);
					}
					System.out.println(reactivateattempt);
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return reactivateattempt;
	}
    
    
    
    
    
    //Change account details (name)
    @CrossOrigin(origins = "http://localhost:3000")
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/changename")
    public String ChangeN(@RequestBody RequestChangeName req){
        final String cname;
        final String nname;
        final String password;
        final String email;
        cname = req.getCName();
        nname = req.getNName();
        password = req.getPassword();
        email = req.getEmail();
        
        String namechangeattempt="initial";
        
        if(nname.contentEquals(cname))
        {
        	namechangeattempt="New name is the same as current name. No change made.";
        }
        else {
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			//System.out.println(cname+" "+nname+" "+email+" "+password);
			String sql="update registration set name=? where email=? and password=? and name=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,nname);
			stmt.setString(2,email);
			stmt.setString(3,password);
			stmt.setString(4,cname);
			int rowchange=stmt.executeUpdate();
			if(rowchange==0)
			{
				namechangeattempt="No such user exists";
				System.out.println(namechangeattempt);
			}
			else {
					namechangeattempt="Name of user "+cname+" has been changed to "+nname;
					System.out.println(namechangeattempt);
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}}
		return namechangeattempt;
	}
    
    
    
    
  //Change account details (password)
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/changepassword")
    public String ChangeP(@RequestBody RequestChangePassword req){
        final String email;
        final String npassword;
        final String cpassword;
        email = req.getEmail();
        npassword = req.getNPassword();
        cpassword = req.getCPassword();
        
        String passwordchangeattempt="initial";
        
        if(npassword.contentEquals(cpassword))
        {
        	passwordchangeattempt="New password is the same as current password. No change made.";
        }
        else if(npassword.toLowerCase().contains(email.substring(0,email.indexOf('@')-1).toLowerCase()) || email.toLowerCase().contains(npassword.toLowerCase()))
        {
        	passwordchangeattempt="New password cannot be similar to email. Please enter some other password for change.";
        }
        else {
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="update registration set password=? where email=? and password=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,npassword);
			stmt.setString(2,email);
			stmt.setString(3,cpassword);
			int rowchange=stmt.executeUpdate();
			if(rowchange==0)
			{
				passwordchangeattempt="No such user exists";
				System.out.println(passwordchangeattempt);
			}
			else {
					passwordchangeattempt="Password of user "+email+" has been changed from "+cpassword+" to "+npassword;
					System.out.println(passwordchangeattempt);
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}}
		return passwordchangeattempt;
	}
    
    
  //Change account details (email)
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/changeemail")
    public String ChangeE(@RequestBody RequestChangeEmail req){
        final String cemail;
        final String nemail;
        final String password;
        cemail = req.getCEmail();
        nemail = req.getNEmail();
        password = req.getPassword();
        
        String emailchangeattempt="initial";
        
        if(nemail.contentEquals(cemail))
        {
        	emailchangeattempt="New email is the same as current email. No change made.";
        }
        else {
            DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
    		Connection con;
    		try {
    			con = DBMS.getConnection();
    			String sql="update registration set email=? where email=? and password=?";
    			PreparedStatement stmt=con.prepareStatement(sql);
    			stmt.setString(1,nemail);
    			stmt.setString(2,cemail);
    			stmt.setString(3,password);
    			int rowchange=stmt.executeUpdate();
    			if(rowchange==0)
    			{
    				emailchangeattempt="No such user exists";
    				System.out.println(emailchangeattempt);
    			}
    			else {
    					emailchangeattempt="Email of user "+cemail+" has been changed to "+nemail;
    					System.out.println(emailchangeattempt);
    			}
    			DBMS.closeConnection(stmt, con);
    		} catch (SQLException s) {
    			emailchangeattempt="This email has been taken, please choose some other email";
    			System.out.println(emailchangeattempt);
    			//System.out.println(s.getMessage());
    		}}
    		return emailchangeattempt;
	}
    
    
    
    
    
    
  //View net user savings
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/api1/netsavings")
    public String NetSavings(@RequestBody RequestNetSavings req){
        final String email;
        final String password;
        final long salary;
        final long expenditure;
        email = req.getEmail();
        password = req.getPassword();
        salary=req.getSalary();
        expenditure=req.getExpenditure();
        
        String savingsattempt="";
        long savings;
        
        //Calculating and viewing the net user savings since account opening
        
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2, con3;
		try {
			con = DBMS.getConnection();
			String sql="select cbalance, month, year from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				savingsattempt="No such user exists.";
				System.out.println(savingsattempt);
			}
			else {
				rs.previous();
				String userinfo="";
				while(rs.next())
				{
					//savingsattempt="This user exists in the system.";
					long ibalance=Long.parseLong(rs.getString(1));
					int month=rs.getInt(2);
					int year=rs.getInt(3);
					String date1=year+"-0"+month+"-01";
					String date2=java.time.LocalDate.now().toString();
					long monthsBetween = ChronoUnit.MONTHS.between(
					        LocalDate.parse(date1),
					        LocalDate.parse(date2).withDayOfMonth(1));
					if(salary<=expenditure)
					{
						savingsattempt=savingsattempt+"Your monthly expenditure is greater than or equal to your monthly salary. Please make use of our application to monitor your expenses and improve your savings.";
						System.out.println(savingsattempt);
					}
					else {
						System.out.println(monthsBetween);
						savings=(salary-expenditure)*monthsBetween;
						savingsattempt=savingsattempt+"Your net savings are calculated to be Rs. "+savings+" from the time you registered in our application. This amount is apart from the balance of Rs. "+ibalance+" in your account at the time of registration. The net total balance in your account is Rs. "+(ibalance+savings);
						System.out.println(savingsattempt);
					}
				}
				
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		try {
		
		con2 = DBMS.getConnection();
		String sql2="select * from logs where email=?;";
		PreparedStatement stmt2=con2.prepareStatement(sql2);
		stmt2.setString(1,email);
		ResultSet rs2=stmt2.executeQuery();
		if(rs2.next()==false)
		{
			con3 = DBMS.getConnection();
			String sql3="INSERT INTO logs (email, savings) VALUES (?, ?);";
			PreparedStatement stmt3=con3.prepareStatement(sql3);
			stmt3.setString(1,email);
			stmt3.setString(2,savingsattempt);
			int check=stmt3.executeUpdate();
			if(check<1)
			{
				System.out.println("Savings attempt not logged.");
			}
			else {
				System.out.println("Savings attempt successfully logged.");
			}
		    DBMS.closeConnection(stmt3, con3);
		}
		else {
			rs2.previous();
			while(rs2.next())
			{
				con3 = DBMS.getConnection();
				String sql3="UPDATE logs SET savings = ? WHERE email = ?;";
				PreparedStatement stmt3=con3.prepareStatement(sql3);
				stmt3.setString(1,savingsattempt);
				stmt3.setString(2,email);
				int check=stmt3.executeUpdate();
				if(check<1)
				{
					System.out.println("Savings attempt not logged.");
				}
				else {
					System.out.println("Savings attempt successfully logged.");
				}
			    DBMS.closeConnection(stmt3, con3);
			}
		    DBMS.closeConnection(stmt2, con2);

	}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return savingsattempt;
		}
    
    
    
    
  //View average monthly and annual user expenditures
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/api1/expenditures")
    public ArrayList<String> Expenditure(@RequestBody RequestExpenditure req){
        final String email;
        final String password;
        //final String asalary;
        final long salary;
        final long rent;
        final long transport;
        final long insurance;
        final long food;
        final long ce;
        final long utility;
        final long travel;
        final long personal;
        final long loan;
        final long cce;
        final long other;
        email = req.getEmail();
        password = req.getPassword();
        salary=req.getSalary();
        rent=req.getRent();
        transport=req.getTransport();
        insurance=req.getInsurance();
        food=req.getFood();
        ce=req.getCE();
        utility=req.getUtility();
        travel=req.getTravel();
        personal=req.getPersonal();
        loan=req.getLoan();
        cce=req.getCCE();
        other=req.getOther();
        
        System.out.println(salary);
        System.out.println(rent);
        System.out.println(transport);
        System.out.println(insurance);
        System.out.println(food);
        System.out.println(ce);
        System.out.println(utility);
        System.out.println(travel);
        System.out.println(personal);
        System.out.println(loan);
        System.out.println(cce);
        System.out.println(other);

        ArrayList<String> results=new ArrayList<String>();
        String results2="";
        String avgexpenditureattempt="";
        long monthlye;
        long annuale;
        double percentagesaved;
        double percentagespent;
        
        //Calculating and viewing the average user expenditures
        
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2, con3;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				avgexpenditureattempt="No such user exists.";
				results2=avgexpenditureattempt;
				results.add(avgexpenditureattempt);
				System.out.println(avgexpenditureattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					//avgexpenditureattempt="This user exists in the system.";
					monthlye=rent+transport+insurance+food+ce+utility+travel+personal+loan+cce+other;
					System.out.println(monthlye);
					annuale=monthlye*12;
					System.out.println(annuale);
					results.add(avgexpenditureattempt);
					results.add(monthlye+"");
					results.add(annuale+"");
					results2=avgexpenditureattempt;
					results2=results2+"Your total monthly expenditure is Rs. "+monthlye;
					results2=results2+"\n"+"Your total annual expenditure is Rs. "+annuale;
					if(annuale>salary)
					{
						results.add("You are spending more that you are earning per year. Please make use of our application to monitor your expenses and improve your savings.");
					    results2=results2+"\n"+"You are spending more that you are earning per year. Please make use of our application to monitor your expenses and improve your savings.";
					}
					else {
						percentagespent=((double)annuale/(double)salary)*100;
						String pspent=String.format("%.2f", percentagespent);
						percentagesaved=100-percentagespent;
						String psaved=String.format("%.2f", percentagesaved);
						if(pspent.contentEquals("100.00"))
						{
							results.add("You are spending all your earnings on expenses. Please make use of our application to monitor your expenses and improve your savings.");
							results2=results2+"\n"+"You are spending all your earnings on expenses. Please make use of our application to monitor your expenses and improve your savings.";
						}
						else {
							results.add(pspent+"");
							results.add(psaved+"");
							results2=results2+"\n"+"Percentage of earnings spent is "+pspent;
							results2=results2+"\n"+"Percentage of earnings saved is "+psaved;
						}
					}
				}
				
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
			results.add(s.getMessage());
		}
		
		
		
		try {
			
			con2 = DBMS.getConnection();
			String sql2="select * from logs where email=?;";
			PreparedStatement stmt2=con2.prepareStatement(sql2);
			stmt2.setString(1,email);
			ResultSet rs2=stmt2.executeQuery();
			if(rs2.next()==false)
			{
				con3 = DBMS.getConnection();
				String sql3="INSERT INTO logs (email, expenses) VALUES (?, ?);";
				PreparedStatement stmt3=con3.prepareStatement(sql3);
				stmt3.setString(1,email);
				stmt3.setString(2,results2);
				int check=stmt3.executeUpdate();
				if(check<1)
				{
					System.out.println("Expenditure results not logged.");
				}
				else {
					System.out.println("Expenditure results successfully logged.");
				}
			    DBMS.closeConnection(stmt3, con3);
			}
			else {
				rs2.previous();
				while(rs2.next())
				{
					con3 = DBMS.getConnection();
					String sql3="UPDATE logs SET expenses = ? WHERE email = ?;";
					PreparedStatement stmt3=con3.prepareStatement(sql3);
					stmt3.setString(1,results2);
					stmt3.setString(2,email);
					int check=stmt3.executeUpdate();
					if(check<1)
					{
						System.out.println("Expenditure results not logged.");
					}
					else {
						System.out.println("Expenditure results successfully logged.");
					}
				    DBMS.closeConnection(stmt3, con3);
				}
			    DBMS.closeConnection(stmt2, con2);

		}
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		
		
		return results;
		} 
    
    
    
    
    
    
  //View net gains from mutual funds investment
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/mutualfunds")
    public String MutualFunds(@RequestBody RequestMutualFunds req){
        final String email;
        final String password;
        final long iamount;
        final long mincome;
        email = req.getEmail();
        password = req.getPassword();
        iamount=req.getIAmount();
        mincome=req.getMIncome();
        String minvestmentattempt="initial";

        //Calculating and viewing the net gains from mutual funds investment
        
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				minvestmentattempt="No such user exists.";
				System.out.println(minvestmentattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					minvestmentattempt="This user exists in the system.";
					final DecimalFormat decimals = new DecimalFormat("0.00");

					if(iamount>((double)20/(double)100)*mincome)
					{
						minvestmentattempt=minvestmentattempt+"\n"+"It is advisable to start with investing 20% of your monthly salary in mutual funds after saving atleast 6x your monthly salary for emergency purposes. If you consider going forward with an investment of Rs. "+iamount+", then you may invest in debt funds if you have a short investment horizon. Otherwise, you may invest in equity or hybrid funds if you have a long investment horizon. Investing in debt funds has moderately high risk and based on average returns, 3.4% to 14.3% gain can be made from an year's worth of investments. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(3.4/100))+" to Rs. +"+decimals.format(iamount*12*(14.3/100))+". Investing in equity funds has moderate to high risk and based on average returns, 7.37% to 21.45% gain can be made from an year's worth of investments. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(7.37/100))+" to Rs. "+decimals.format(iamount*12*(21.45/100))+". Investing in hybrid funds has low to moderate risk and based on average returns, 17.15% to 33.37% gain can be made from an year's worth of investments in the best hybrid funds. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(17.15/100))+" to Rs. "+decimals.format(iamount*12*(33.37/100))+".";
						System.out.println(minvestmentattempt);
					}
					else if(iamount>0){
						minvestmentattempt=minvestmentattempt+"\n"+"This is an appropriate amount for starting investment in mutual funds. If you consider going forward with an investment of Rs. "+iamount+", then you may invest in debt funds if you have a short investment horizon. Otherwise, you may invest in equity or hybrid funds if you have a long investment horizon. Investing in debt funds has moderately high risk and based on average returns, 3.4% to 14.3% gain can be made from an year's worth of investments. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(3.4/100))+" to Rs. "+decimals.format(iamount*12*(14.3/100))+". Investing in equity funds has moderate to high risk and based on average returns, 7.37% to 21.45% gain can be made from an year's worth of investments. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(7.37/100))+" to Rs. "+decimals.format(iamount*12*(21.45/100))+". Investing in hybrid funds has low to moderate risk and based on average returns, 17.15% to 33.37% gain can be made from an year's worth of investments in the best hybrid funds. In an year, you will be able to gain from Rs. "+decimals.format(iamount*12*(17.15/100))+" to Rs. "+decimals.format(iamount*12*(33.37/100))+".";
						System.out.println(minvestmentattempt);
					}
					else {
						minvestmentattempt=minvestmentattempt+"\n"+"This is an invalid amount for investment.";
						System.out.println(minvestmentattempt);
					}
				}
				
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return minvestmentattempt;
		}
    
    
    
    
  //View net gains from provident funds investment
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/providentfunds")
    public String ProvidentFunds(@RequestBody RequestProvidentFunds req){
        final String email;
        final String password;
        final long iamount;
        final long aincome;
        email = req.getEmail();
        password = req.getPassword();
        iamount=req.getIAmount();
        aincome=req.getAIncome();
        String pinvestmentattempt="initial";

        //Calculating and viewing the net gains from provident funds investment
        
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				pinvestmentattempt="No such user exists.";
				System.out.println(pinvestmentattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					pinvestmentattempt="This user exists in the system.";
					final DecimalFormat decimals = new DecimalFormat("0.00");
					
					if(iamount>150000)
					{
						pinvestmentattempt=pinvestmentattempt+"\n"+"Rs. 150000 is the maximum allowed investment amount in a year if you are investing in Public Provident Fund. There is no limit to the amount that can be invested through Voluntary Provident Fund in Employees' Provident Fund apart from 12% of your basic salary. The lock-in period for Public Provident Fund is 15 years, with 7.10% returns per annum. On the other hand, the lock-in period for Employees' Provident Fund is till retirement, with 8.50% returns per annum. In a year, you will be able to gain Rs. "+decimals.format((8.50/100)*iamount)+" through Employees' Provident Fund. You won't be able to invest through Public Provident Fund with this investment amount (greater than Rs. 150000 a year).";
						System.out.println(pinvestmentattempt);
					}
					else if(iamount>=500 && iamount>=(0.12*aincome)){
						pinvestmentattempt=pinvestmentattempt+"\n"+"Rs. 500 is the minimum allowed investment amount in a year if you are investing in Public Provident Fund. This investment amount is appropriate (greater than or equal to 12% of your basic salary) when investing through Employees' Provident Fund. The lock-in period for Public Provident Fund is 15 years, with 7.10% returns per annum. On the other hand, the lock-in period for Employees' Provident Fund is till retirement, with 8.50% returns per annum. In a year, you will be able to gain Rs. "+decimals.format((7.10/100)*iamount)+" through Public Provident Fund or Rs. "+decimals.format((8.50/100)*iamount)+" through Employees' Provident Fund.";
						System.out.println(pinvestmentattempt);
					}
					else if(iamount>=500){
						pinvestmentattempt=pinvestmentattempt+"\n"+"Rs. 500 is the minimum allowed investment amount in a year if you are investing in Public Provident Fund. You should invest a minimum of 12% of your basic salary, Rs. "+decimals.format(0.12*aincome)+" when investing through Employees' Provident Fund. The lock-in period for Public Provident Fund is 15 years, with 7.10% returns per annum. On the other hand, the lock-in period for Employees' Provident Fund is till retirement, with 8.50% returns per annum. In a year, you will be able to gain Rs. "+decimals.format((7.10/100)*iamount)+" through Public Provident Fund. You won't be able to invest through Employees' Provident Fund with this investment amount (less than 12% of your basic salary).";
						System.out.println(pinvestmentattempt);
					}
					else if(iamount<500 && iamount>=(0.12*aincome)){
						pinvestmentattempt=pinvestmentattempt+"\n"+"The investment amount is below the allowed minimum investment amount in Public Provident Fund. You should invest through Employees' Provident Fund, as this investment amount is greater than or equal to 12% of your basic salary. The lock-in period for Employees' Provident Fund is till retirement, with 8.50% returns per annum. In a year, you will be able to gain Rs. "+decimals.format((8.50/100)*iamount)+" through Employees' Provident Fund.";
						System.out.println(pinvestmentattempt);
					}
					else {
						pinvestmentattempt=pinvestmentattempt+"\n"+"This is an invalid amount for investment.";
						System.out.println(pinvestmentattempt);
					}
				}
				
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return pinvestmentattempt;
		}
    
  //View net gains from stock investment
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/stocks")
    public String Stocks(@RequestBody RequestStocks req){
        final String email;
        final String password;
        final double ia;
        final double mi;
        final String sn;
       
        email = req.getEmail();
        password = req.getPassword();
        ia=req.getIA();
        mi=req.getMI();
        sn=req.getSN();
        
        System.out.println(email+" "+password+" "+mi+" "+ia+" "+sn);
        
        String stockattempt="initial";
        
      //Calculating and viewing the net gains from stocks investment
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				stockattempt="No such user exists.";
				System.out.println(stockattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					stockattempt="This user exists in the system.";
					if(ia>(0.2*mi))
					{
						stockattempt=stockattempt+"\n"+"The investment amount is greater than 20% of your monthly salary. It is advised to invest less than or equal to 20% of your monthly salary.";
					    System.out.println(stockattempt);
					}
					else {
					con2 = DBMS.getConnection();
					String sql2="select * from stocks where stock_name=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,sn);
					ResultSet rs2=stmt2.executeQuery();
					if(rs2.next()==false)
					{
						stockattempt="No such stock exists.";
						System.out.println(stockattempt);
					}
					else {
						rs2.previous();
						while(rs2.next())
						{
							final double stock_price;
					        final double change_percent;
					        final double avg_change_percent;
							stock_price=rs2.getDouble(2);
							change_percent=rs2.getDouble(3);
							avg_change_percent=rs2.getDouble(4);
							
							if(stock_price>ia)
							{
								stockattempt=stockattempt+"\n"+"You cannot invest in this stock as the price of a share is Rs. "+stock_price+" and is greater than your investment amount.";
							}
							else {
								stockattempt=stockattempt+"\n"+"You can invest in this stock. The price of a share is Rs. "+stock_price+". The value of this stock has increased by "+change_percent+"% in the past 5 years. According to previous years' increase percentage, average increase in the investment made by you would be Rs. "+(ia*(avg_change_percent/100))+" in a year, percentage of increase being "+avg_change_percent+"%";
							}
						}
					DBMS.closeConnection(stmt2, con2);
					System.out.println(stockattempt);
				}
			}
		    }
			DBMS.closeConnection(stmt, con);
		}} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return stockattempt;
    }
    
    
    
    
    
    
    
    
    
    
    
  //View net gains from bond investment
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/bonds")
    public String Bonds(@RequestBody RequestBonds req){
        final String email;
        final String password;
        final long ia;
        final long savings;
        final String bn;
       
        email = req.getEmail();
        password = req.getPassword();
        ia=req.getIA();
        savings=req.getSavings();
        bn=req.getBN();
        
        System.out.println(email+" "+password+" "+savings+" "+ia+" "+bn);
        
        String bondattempt="initial";
        
      //Calculating and viewing the net gains from bonds investment
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				bondattempt="No such user exists.";
				System.out.println(bondattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					bondattempt="This user exists in the system.";
					if(ia>(0.3*savings))
					{
						bondattempt=bondattempt+"\n"+"The investment amount is greater than 30% of your total savings. It is advised to invest less than or equal to 30% of your savings.";
					    System.out.println(bondattempt);
					}
					else {
					con2 = DBMS.getConnection();
					String sql2="select * from bonds where bond_name=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,bn);
					ResultSet rs2=stmt2.executeQuery();
					if(rs2.next()==false)
					{
						bondattempt="No such bond exists.";
						System.out.println(bondattempt);
					}
					else {
						rs2.previous();
						while(rs2.next())
						{
							final long bond_price;
					        final double interest_rate;
					        final int months;
							bond_price=rs2.getLong(2);
							interest_rate=rs2.getDouble(3);
							months=rs2.getInt(4);
							
							final DecimalFormat decimals = new DecimalFormat("0.00");
							
							if(bond_price>ia)
							{
								bondattempt=bondattempt+"\n"+"You cannot invest in this bond as the face value is Rs. "+bond_price+" and is greater than your investment amount.";
							}
							else {
								int years=months/12;
								int months2=months%12;
								if(years<1 && months==1)
								{
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+months2+" month. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
								else if(years<1 && months>1)
								{
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+months2+" months. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
								else if(years==1 && months2==0)
								{
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+years+" year. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
								else if(years>1 && months2==0)
								{
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+years+" years. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
								else if(years>1 && months2==1){
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+years+" years and "+months2+" month. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
								else {
									bondattempt=bondattempt+"\n"+"You can invest in this bond. The face value is Rs. "+bond_price+". The time till maturity of this bond is "+years+" years and "+months2+" months. You will gain Rs. "+decimals.format(bond_price*(interest_rate/100)*(double)((double)months/(double)12))+" after this time period, the interest rate on face value being "+interest_rate+"%.";
								}
							}
						}
					DBMS.closeConnection(stmt2, con2);
					System.out.println(bondattempt);
				}
			}
		    }
			DBMS.closeConnection(stmt, con);
		}} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return bondattempt;
    }
    
    
    
    
  //View net gains from real estate investment
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/realestate")
    public String Real_Estate(@RequestBody RequestRealEstate req){
        final String email;
        final String password;
        final long budget;
        final int bhk;
        final String city;
       
        email = req.getEmail();
        password = req.getPassword();
        budget=req.getBudget();
        bhk=req.getBhk();
        city=req.getCity();
        
        System.out.println(email+" "+password+" "+budget+" "+bhk+" "+city);
        
        String realestateattempt="initial";
        
      //Viewing real estate investment options according to user's budget
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				realestateattempt="No such user exists.";
				System.out.println(realestateattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					realestateattempt="This user exists in the system.";
					
					con2 = DBMS.getConnection();
					String sql2="select * from real_estate where city=? and bhk=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,city);
					stmt2.setInt(2,bhk);
					ResultSet rs2=stmt2.executeQuery();
					if(rs2.next()==false)
					{
						realestateattempt="No such option exists.";
						System.out.println(realestateattempt);
					}
					else {
						rs2.previous();
						while(rs2.next())
						{
							final long lower_limit;
					        final long upper_limit;
							lower_limit=rs2.getLong(3);
							upper_limit=rs2.getLong(4);
							
							if(lower_limit>budget)
							{
								realestateattempt=realestateattempt+"\n"+"You cannot invest in a "+bhk+" bhk house in "+city+" as the price ranges from Rs. "+lower_limit+" to Rs. "+upper_limit+" and has a greater cost than your budget.";
							}
							else if(budget<=((lower_limit+upper_limit)/2)){
								realestateattempt=realestateattempt+"\n"+"You can invest in a "+bhk+" bhk house in "+city+" as the price ranges from Rs. "+lower_limit+" to Rs. "+upper_limit+", which is in your budget. You will be able to afford a good enough house.";
							}
							else {
								realestateattempt=realestateattempt+"\n"+"You can invest in a "+bhk+" bhk house in "+city+" as the price ranges from Rs. "+lower_limit+" to Rs. "+upper_limit+", which is in your budget. You will be able to afford a good luxury house.";
						    }
					DBMS.closeConnection(stmt2, con2);
					System.out.println(realestateattempt);
				}
			}
		    }
			DBMS.closeConnection(stmt, con);
		}} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return realestateattempt;
    }
    
    
    
    
  //View user's current savings in different currencies
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/currencies")
    public String Currencies(@RequestBody RequestCurrencies req){
        final String email;
        final String password;
        final long savings;
        final String currency;
        
        email = req.getEmail();
        password = req.getPassword();
        savings=req.getSavings();
        currency=req.getCurrency();

        System.out.println(email+" "+password+" "+savings+" "+currency);
        
        String currencyattempt="initial";
        
      //Viewing user's savings in different currencies
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				currencyattempt="No such user exists.";
				System.out.println(currencyattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					currencyattempt="This user exists in the system.";
					
					con2 = DBMS.getConnection();
					String sql2="select * from currencies where currency_name=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,currency);
					ResultSet rs2=stmt2.executeQuery();
					if(rs2.next()==false)
					{
						currencyattempt="No such option exists.";
						System.out.println(currencyattempt);
					}
					else {
						rs2.previous();
						while(rs2.next())
						{
							final double inr_value;
							inr_value=rs2.getDouble(2);
							final DecimalFormat decimals = new DecimalFormat("0.00");
							if(savings>0 && (double)savings/(double)inr_value>1)
							{
								currencyattempt=currencyattempt+"\n"+"You have total savings of Rs. "+savings+". This amount when converted from Indian Rupee to "+currency+" is "+decimals.format((double)savings/(double)inr_value)+" "+currency+"s.";
							}
							else if(savings>0)
							{
								currencyattempt=currencyattempt+"\n"+"You have total savings of Rs. "+savings+". This amount when converted from Indian Rupee to "+currency+" is "+decimals.format((double)savings/(double)inr_value)+" "+currency+".";
							}
							else
							{
								currencyattempt=currencyattempt+"\n"+"The value entered by user in total savings is invalid.";
							}
						}
					DBMS.closeConnection(stmt2, con2);
					System.out.println(currencyattempt);
				}
			}
		    }
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return currencyattempt;
    }
    
    
    
    
  //Display user's logs
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/logs")
    public String Logs(@RequestBody RequestLogs req){
        final String email;
        final String password;
        
        email = req.getEmail();
        password = req.getPassword();

        System.out.println(email+" "+password);
        
        String logsattempt="";
        
      //Displaying user's logs
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				logsattempt="No such user exists.";
				System.out.println(logsattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					//logsattempt="This user exists in the system.";
					
					con2 = DBMS.getConnection();
					String sql2="select * from logs where email=?;";
					PreparedStatement stmt2=con2.prepareStatement(sql2);
					stmt2.setString(1,email);
					ResultSet rs2=stmt2.executeQuery();
					if(rs2.next()==false)
					{
						logsattempt=logsattempt+"No logs exist for this user.";
						System.out.println(logsattempt);
					}
					else {
						rs2.previous();
						while(rs2.next())
						{
							final String savings;
							final String expenses;
							savings=rs2.getString(2);
							expenses=rs2.getString(3);
							System.out.println(savings+"\n"+expenses);
							if(savings==null && expenses!=null)
							{
								logsattempt=logsattempt+"No savings logged for this user."+"\n"+expenses;
							}
							else if(savings!=null && expenses==null)
							{
								logsattempt=logsattempt+savings+"\n"+"No expenses logged for this user.";
							}
							else {
								logsattempt=logsattempt+savings;
								logsattempt=logsattempt+"\n"+expenses;
							}
						}
					DBMS.closeConnection(stmt2, con2);
					System.out.println(logsattempt);
				}
			}
		    }
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return logsattempt;
    }
    
    
    
    
  //Setting goals for user
    @ApiOperation(value = "Form submission")
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api1/goals")
    public String Goals(@RequestBody RequestGoals req){
        final String email;
        final String password;
        final long savings;
        final String goal;
        final long mincome;
        final long price;
        
        email = req.getEmail();
        password = req.getPassword();
        savings=req.getSavings();
        goal=req.getGoal();
        mincome=req.getMIncome();
        price=req.getPrice();

        System.out.println(email+" "+password+" "+savings+" "+goal+" "+mincome+" "+price);
        
        String goalattempt="";
        
      //Displaying goal setting to user for different goals
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con, con2;
		try {
			con = DBMS.getConnection();
			String sql="select * from registration where email=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()==false)
			{
				goalattempt="No such user exists.";
				System.out.println(goalattempt);
			}
			else {
				rs.previous();
				while(rs.next())
				{
					//goalattempt="This user exists in the system.";
					
					final DecimalFormat decimals = new DecimalFormat("0");
					final DecimalFormat decimals2 = new DecimalFormat("0.00");
					
					//Goal setting for car
					if(goal.contentEquals("Car"))
					{
						double p=0.8*price;
						double r=0.08/12;
						int n=48;
						double emi=(p*r*Math.pow((1+r),(n)))/(Math.pow((1+r),(n))-1);
						System.out.println("EMI = "+emi);
						if(6*mincome<price)
						{
							goalattempt="It is advised to buy a car with price less than or equal to half of your annual salary.";
						}
						else if(savings<(0.2*price))
						{
							goalattempt="Following the 20/4/10 rule, you should be able to pay 20% of the price of car as down payment. As your savings for down payment are less than Rs. "+(0.2*price)+" (20% of car price), you will have to save up more before buying the car.";
						}
						else if(emi>0.1*mincome)
						{
							goalattempt="Following the 20/4/10 rule, you should be able to pay the remaining 80% of the price of the car as EMIs in 4 years. The EMI should be less than or equal to 10% of your monthly income. As 10% of your monthly income is less than Rs. "+decimals2.format(emi)+" (EMI for payment of car price, after down payment), you won't be able to buy this car. You are advised to buy a car with lower price.";
						}
						else {
							goalattempt="Since this car is within budget according to your annual income, and the conditions for the 20/4/10 rule are getting fulfilled (you are able to pay 20% of the price of car as down payment, you are able to pay the remaining 80% of the price of the car as EMIs in 4 years, the EMI being less than or equal to 10% of your monthly income); You can buy this car with an EMI of Rs. "+decimals2.format(emi)+".";
						}
					}
					
					
					//Goal setting for house
					else if(goal.contentEquals("House"))
					{
						double p=0.6*price;
						double r=0.08/12;
						int n=240;
						double emi=(p*r*Math.pow((1+r),(n)))/(Math.pow((1+r),(n))-1);
						System.out.println("EMI = "+emi);
						if(3*12*mincome<price)
						{
							goalattempt="Following the 3/20/30/40 rule, you should buy a house with price less than or equal to 3 times your annual salary.";
						}
						else if(savings<(0.4*price))
						{
							goalattempt="Following the 3/20/30/40 rule, you should be able to pay 40% of the price of house as down payment. As your savings for down payment are less than Rs. "+(0.4*price)+" (40% of house price), you will have to save up more before buying the house.";
						}
						else if(emi>0.3*mincome)
						{
							goalattempt="Following the 3/20/30/40 rule, you should be able to pay the remaining 60% of the price of the house as EMIs in 20 years. The EMI should be less than or equal to 30% of your monthly income. As 30% of your monthly income is less than Rs. "+decimals2.format(emi)+" (EMI for payment of house price, after down payment), you won't be able to buy this house. You are advised to buy a house with lower price.";
						}
						else {
							goalattempt="Since this house is within budget as the conditions for the 3/20/30/40 rule are getting fulfilled (you are buying a house with price less than or equal to 3 times your annual salary, you are able to pay 40% of the price of house as down payment, you are able to pay the remaining 60% of the price of the house as EMIs in 20 years, the EMI being less than or equal to 30% of your monthly income); You can buy this house with an EMI of Rs. "+decimals2.format(emi)+".";
						}
					}
					else if(goal.contentEquals("Other"))
					{
						if(savings>=price)
						{
							goalattempt="You can buy the thing you want, as you have enough savings for this purchase.";
						}
						else {
							long remaining=price-savings;
							double months=Double.parseDouble(decimals.format(remaining/(0.1*mincome)));
							if(months>1)
							{
								goalattempt="You can buy the thing you want in the next "+(int)months+" months by saving 10% of your monthly income every month, as you will have enough savings for this purchase.";
							}
							else if(months==1){
								goalattempt="You can buy the thing you want in the next "+(int)months+" month by saving 10% of your monthly income, as you will have enough savings for this purchase.";
							}
							else if(months<1)
							{
								goalattempt="You can buy the thing you want within this month, as you will have enough savings for this purchase.";
							}
						}
					}
					else {
						goalattempt="No such option exists.";
					}
					System.out.println(goalattempt);
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return goalattempt;
    }
}
