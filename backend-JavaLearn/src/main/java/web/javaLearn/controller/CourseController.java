package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.CourseRequest;
import web.javaLearn.repository.CourseRepository;
import web.javaLearn.service.CourseService;

import java.util.List;


@RestController
@AllArgsConstructor
public class CourseController {

    CourseService courseService;
    CourseRepository courseRepository;

    ////
    // Admin
    ////

    @PostMapping("/admin/courses/save")
    public ResponseEntity<String> saveCourse(@RequestBody CourseRequest courseRequest){
        if(courseRepository.findByTitle(courseRequest.getTitle()).isPresent()){
            return new ResponseEntity<>("Course name taken", HttpStatus.CONFLICT);
        }
        courseService.saveCourse(courseRequest);
        return new ResponseEntity<>("Added course successfully", HttpStatus.OK);
    }

    @DeleteMapping("/admin/courses/remove/{id}")
    public ResponseEntity<Long> removeCourse(@PathVariable Long id){
        var isRemoved = courseService.removeCourse(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/admin/courses/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
        courseService.updateCourse(id, courseRequest);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    ////
    // All Users
    ////

    @GetMapping("/courses/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }


}
