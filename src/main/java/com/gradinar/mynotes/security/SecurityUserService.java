package com.gradinar.mynotes.security;

import com.gradinar.mynotes.entity.User;
import com.gradinar.mynotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return new SecurityUser(user.getEmail(), user.getPassword(), user.isValid(), user.getRoles());
        } else {
            throw new UsernameNotFoundException("User with such email not found");
        }
    }
}
