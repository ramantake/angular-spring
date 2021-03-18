package com.WebProject.movieCruizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.WebProject.movieCruizer.model.AppUser;
import com.WebProject.movieCruizer.model.User;
import com.WebProject.movieCruizer.repository.UserRepository;



@Service
public class AppUserDetailsService implements UserDetailsService {

@Autowired
UserRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user = userRepository.findByUsername(username);
if(user==null) {
throw new UsernameNotFoundException("Username Not Found");
}
else {
AppUser appUser = new AppUser(user);
return appUser;
}
}
}
