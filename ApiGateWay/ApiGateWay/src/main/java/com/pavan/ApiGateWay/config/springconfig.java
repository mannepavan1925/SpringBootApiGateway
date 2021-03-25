package com.pavan.ApiGateWay.config;

import com.pavan.ApiGateWay.Repo.CustomerAuthRepository;
import com.pavan.ApiGateWay.beans.User;
import com.pavan.ApiGateWay.service.CustomerAuthService;
import com.pavan.ApiGateWay.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class springconfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private CustomerAuthService customerAuthService;
//    @Bean
//    public CustomerAuthService customerAuthService(CustomerAuthRepository customerAuthRepository) {
//        return new CustomerAuthService(customerAuthRepository);
//    }
    @Override
    public UserDetailsService userDetailsServiceBean() {
        return name -> {
            User user= (User) customerAuthService.findByName(name);
            return (UserDetails) new User(name);
//            QuerySideCustomer customer = customerAuthService.findByName(email);
//            return new User(email, customer.getPassword(), true, true, true, true,
//                    AuthorityUtils.createAuthorityList("USER"));
        };
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean());
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/hi");
//    }
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll();
    }
    @Bean
    public TokenService tokenService() {
        KeyBasedPersistenceTokenService res = new KeyBasedPersistenceTokenService();
        res.setSecureRandom(new SecureRandom());
        res.setServerSecret("12345");
       // res.setServerInteger(securityProperties.getServerInteger());

        return res;
    }
}
