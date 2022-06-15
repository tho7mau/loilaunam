package com.example.User.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Model.UserModel;

public interface UserServiceRepo extends JpaRepository<UserModel, Integer> {

}
