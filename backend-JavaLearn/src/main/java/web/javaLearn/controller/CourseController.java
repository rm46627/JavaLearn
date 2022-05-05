package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.auth.User;
import web.javaLearn.model.course.Course;
import web.javaLearn.repository.CourseRepository;
import web.javaLearn.service.CourseService;

import java.util.List;


@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    CourseService courseService;
    CourseRepository courseRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }
}
