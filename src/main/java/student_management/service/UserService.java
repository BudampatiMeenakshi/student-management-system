package student_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_management.entity.User;
import student_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username,String password){

        return userRepository
                .findByUsernameAndPassword(username,password)
                .orElse(null);
    }
}