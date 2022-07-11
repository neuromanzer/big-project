package com.neuro.service001useroperations.security;

import com.neuro.service001useroperations.model.SecurityUser;
import com.neuro.service001useroperations.model.User;
import com.neuro.service001useroperations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during Authentication");

        User u = userRepository
                .findUsersByName(username)
                .orElseThrow(s);

        return new SecurityUser(u);
    }
}