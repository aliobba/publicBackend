package com.ooredoo.report_builder.securityImpl;

import com.ooredoo.report_builder.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //when I load user Details I need to load the roles and token
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        return userRepository.findByEmail(userEmail)
                .orElseThrow( () -> new UsernameNotFoundException("User not found "));
    }
}
