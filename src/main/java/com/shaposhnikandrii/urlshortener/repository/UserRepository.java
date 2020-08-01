package com.shaposhnikandrii.urlshortener.repository;

import com.shaposhnikandrii.urlshortener.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}