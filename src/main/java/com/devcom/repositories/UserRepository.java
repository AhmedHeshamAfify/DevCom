package com.devcom.repositories;


import org.springframework.data.repository.CrudRepository;
import com.devcom.models.User;
 
public interface UserRepository extends CrudRepository<User, Long> {
     
}

