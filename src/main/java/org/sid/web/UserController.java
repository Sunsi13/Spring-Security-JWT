package org.sid.web;

import lombok.Data;


import org.sid.entities.JwtResponse;
import org.sid.utility.JWTUtility;



import org.sid.entities.AppUser;
import org.sid.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
   
    
    @Autowired
	JWTUtility jwtUtility;
    
    @Autowired
    AccountServiceImpl userService;
    
	@Autowired
	AuthenticationManager authenticationManager;
	
    @PostMapping("/register")
    public AppUser register(@RequestBody  AppUser userForm){
        return  userService.saveUser(
                userForm.getUsername(),userForm.getPassword());
    }
    
    
    
    @PostMapping( "/authenticate")
    public JwtResponse authenticate(@RequestBody AppUser jwtRequest) {

      
        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        
        
        System.out.println(token);

        return  new JwtResponse(token);
    }
	

	  @GetMapping (path= "/t")
	    public String home() {
	        return "Welcome to allianceca";
	    }

	
	
	
}
