package com.aram.connect.init;

import com.aram.connect.persistence.UserDetailsService;
import com.aram.connect.persistence.dao.UserLogin;
import com.aram.connect.util.AramUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AramInit implements CommandLineRunner {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Aram started --------------------");
        UserLogin userLogin = userDetailsService.getUserByUsername("admin");
        if(userLogin == null) {
            userLogin = new UserLogin();
            userLogin.setRoleId(1);
            userLogin.setUsername("admin");
            userLogin.setUserIdentity("admin@123");
            userDetailsService.createUserLogin(userLogin);
            // userLogin = userDetailsService.getUserByUsername("admin");
        }
        userLogin.setUserIdentity(AramUtil.generateToken("admin@123", userLogin.getUserLoginId(), "admin"));
        userDetailsService.createUserLogin(userLogin);
    }

}