    package com.anushka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.model.User;
import com.anushka.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserById'");
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsersProjectSize'");
    }

}
