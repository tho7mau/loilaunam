package com.example.User.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.User.Model.UserModel;
import com.example.User.Repo.UserServiceRepo;
import com.example.User.Service.UserService;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceRepo userServiceRepo;

    @Override
    public UserModel createUser(UserModel model) {
        return userServiceRepo.save(model);
    }

    @Override
    public UserModel getById(Integer id) {
        Optional<UserModel> model = userServiceRepo.findById(id);
        return model.isPresent() ? model.get() : null;
    }

    @Override
    public void deleteUser(Integer id) {
        userServiceRepo.deleteById(id);
    }

    @Override
    public List<UserModel> getAll() {

        return userServiceRepo.findAll();
    }

}
