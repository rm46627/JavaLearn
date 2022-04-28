package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.User;
import web.javaLearn.service.AdminService;

import java.util.List;
import java.util.Optional;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{user}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return status(HttpStatus.OK).body(adminService.getUserByUsername(id));
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
