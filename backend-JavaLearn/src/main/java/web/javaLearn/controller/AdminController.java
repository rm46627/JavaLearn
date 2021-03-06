package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.auth.User;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.CourseRequest;
import web.javaLearn.repository.CourseRepository;
import web.javaLearn.service.AdminService;
import web.javaLearn.service.CourseService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminService adminService;
    CourseRepository courseRepository;
    CourseService courseService;

    ////
    // Users
    ////

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
