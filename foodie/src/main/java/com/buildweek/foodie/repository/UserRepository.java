package com.buildweek.foodie.repository;

import com.buildweek.foodie.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
