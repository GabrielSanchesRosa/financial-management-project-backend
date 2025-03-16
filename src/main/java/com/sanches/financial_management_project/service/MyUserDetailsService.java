package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.mapper.UserMapper;
import com.sanches.financial_management_project.model.UserPrincipal;
import com.sanches.financial_management_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        return new UserPrincipal(entity);
    }
}
