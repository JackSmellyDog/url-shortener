package com.shaposhnikandrii.urlshortener.service;

import com.shaposhnikandrii.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) {
    return Optional.ofNullable(userRepository.findByUsername(s)).orElseThrow(() -> new UsernameNotFoundException("No such user: " + s));
  }
}
