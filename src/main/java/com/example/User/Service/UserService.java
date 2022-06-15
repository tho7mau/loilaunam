package com.example.User.Service;

import java.util.List;

import com.example.User.Model.UserModel;

public interface UserService {
    // post
    UserModel createUser(UserModel model);

    // get all
    List<UserModel> getAll();

    // get by id
    UserModel getById(Integer id);
    // delete

    void deleteUser(Integer id);
}
