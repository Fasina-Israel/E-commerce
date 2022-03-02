package com.phoenix.security;

import com.phoenix.data.model.AppUser;
import com.phoenix.data.model.Authority;
import com.phoenix.data.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RDBUsersetailsServiceImpl implements UserDetailsService {


    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //query for user details form db
        AppUser user = appUserRepository.findByEmail(username).orElse(null);
        //check that user exists
        if(user==null){
            throw new UsernameNotFoundException("User with email not found");
        }
        //return Userdetails
        return new User(user.getEmail(), user.getPassword(), getAuthority(user.getAuthorities()));

    }

    private List<SimpleGrantedAuthority> getAuthority(List<Authority> authorities){
        return authorities.stream().map(authority->{
            return new SimpleGrantedAuthority(authority.name());
        }).collect(Collectors.toList());
    }
}
