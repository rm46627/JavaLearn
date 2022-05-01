package web.javaLearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.auth.LoginRequest;
import web.javaLearn.security.UserPrincipal;
import web.javaLearn.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return new ResponseEntity<>(userService.getInfo(userPrincipal.getUsername()), HttpStatus.OK);
    }

    @DeleteMapping("/removeuser")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest)
    {
        return new ResponseEntity<>(userService.removeUser(loginRequest), HttpStatus.OK);
    }
}
