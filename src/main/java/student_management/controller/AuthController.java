package student_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import student_management.entity.User;
import student_management.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){

        User foundUser =
                userService.login(
                        user.getUsername(),
                        user.getPassword());

        if(foundUser != null){
            return "Login Success";
        }

        return "Invalid Username or Password";
    }
}