package com.example.simplerestapis.controller;

import java.sql.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplerestapis.models.RequestAdminLogin;
import com.example.simplerestapis.models.RequestChangeName;
import com.example.simplerestapis.models.RequestChangePassword;
import com.example.simplerestapis.models.RequestLogin;
import com.example.simplerestapis.service.DatabaseConnection;

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
    @PostMapping("/api1/login")
    public String Login(@RequestBody RequestLogin req){
        final String name;
        final String password;
        name = req.getName();
        password = req.getPassword();
        
        String loginattempt="initial";
        
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="select * from login where name=? and password=?;";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,name);
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
					System.out.println("Name: "+rs.getString(1)+", Password: "+rs.getString(2));
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return loginattempt;
	}
    
    
    //Admin Login
    @ApiOperation(value = "Form submission")
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
		}
    
    
    
    //Change account details (name)
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/changename")
    public String ChangeN(@RequestBody RequestChangeName req){
        final String cname;
        final String nname;
        final String cpassword;
        cname = req.getCName();
        nname = req.getNName();
        cpassword = req.getCPassword();
        
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
			String sql="update login set name=? where name=? and password=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,nname);
			stmt.setString(2,cname);
			stmt.setString(3,cpassword);
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
			namechangeattempt="This name has been taken, please choose some other name";
			System.out.println(namechangeattempt);
			//System.out.println(s.getMessage());
		}}
		return namechangeattempt;
	}
    
    
    
    
  //Change account details (password)
    @ApiOperation(value = "Form submission")
    @PutMapping("/api1/changepassword")
    public String ChangeN(@RequestBody RequestChangePassword req){
        final String cname;
        final String npassword;
        final String cpassword;
        cname = req.getCName();
        npassword = req.getNPassword();
        cpassword = req.getCPassword();
        
        String passwordchangeattempt="initial";
        
        if(npassword.contentEquals(cpassword))
        {
        	passwordchangeattempt="New password is the same as current password. No change made.";
        }
        else if(npassword.contains(cname))
        {
        	passwordchangeattempt="New password cannot contain the username. Please enter some other password for change.";
        }
        else {
        DatabaseConnection DBMS=new DatabaseConnection("jdbc:mysql://localhost:3306/capstone", "root", "root");
		Connection con;
		try {
			con = DBMS.getConnection();
			String sql="update login set password=? where name=? and password=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,npassword);
			stmt.setString(2,cname);
			stmt.setString(3,cpassword);
			int rowchange=stmt.executeUpdate();
			if(rowchange==0)
			{
				passwordchangeattempt="No such user exists";
				System.out.println(passwordchangeattempt);
			}
			else {
					passwordchangeattempt="Password of user "+cname+" has been changed from "+cpassword+" to "+npassword;
					System.out.println(passwordchangeattempt);
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}}
		return passwordchangeattempt;
	}
    
}
