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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplerestapis.models.RequestLogin;
import com.example.simplerestapis.service.DatabaseConnection;

import java.util.*;


import io.swagger.annotations.ApiOperation;

@ApiOperation(value = "/api1", tags = "Rest Apis controllers")
@RestController
public class WebController {

    // public RequestMap mp = new RequestMap();

    
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

//	@Autowired
//	@Qualifier("hiveJdbcTemplate")
//	private JdbcTemplate hiveJdbcTemplate;
    
    @ApiOperation(value = "Home page")
    @GetMapping("/api1")
    public String home(){
        return "up and running";
    }
    
    
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
				System.out.println("Invalid login attempt");
				loginattempt="Invalid login attempt";
			}
			else {
				rs.previous();
				while(rs.next())
				{
					System.out.println("Name: "+rs.getString(1)+", Password: "+rs.getString(2));
					loginattempt="Valid login attempt";
				}
			}
			DBMS.closeConnection(stmt, con);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return loginattempt;
	}
}
