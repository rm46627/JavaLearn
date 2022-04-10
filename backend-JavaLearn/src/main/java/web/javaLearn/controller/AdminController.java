package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.User;
import web.javaLearn.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Secured({"ROLE_ADMIN"})
@AllArgsConstructor
@Slf4j
public class AdminController {

    AdminService adminService;

    @PostMapping("/users")
    public List<User> getUsersList(){
        return adminService.getAllUserRecords();
    }

    @PostMapping("/users/{user}")
    public Optional<User> getUser(@PathVariable String user){
        return adminService.getUserRecord(user);
    }

    @DeleteMapping("/removeuser/{id}")
    public ResponseEntity<Long> removeUser(@PathVariable Long id){
        var isRemoved = adminService.removeUser(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
