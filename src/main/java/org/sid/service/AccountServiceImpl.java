package org.sid.service;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements  UserDetailsService {
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public AppUser saveUser(String username, String password) {
        //AppUser  user=appUserRepository.findByUsername(username);
        //if(user!=null) throw new RuntimeException("User already exists");
        AppUser appUser=new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        return appUser;
    }

    
    
    
    public UserDetails loadUserByUsername(String username) {
        return  appUserRepository.findByUsername(username);
    }

   
}
