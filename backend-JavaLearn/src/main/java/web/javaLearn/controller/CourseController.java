package web.javaLearn.controller;

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
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveCourse(@RequestBody Course course){
        if(courseRepository.findByName(course.getName()).isPresent()){
            return new ResponseEntity<>("Course name taken", HttpStatus.CONFLICT);
        }
        courseService.saveCourse(course);
        return new ResponseEntity<>("Added course successfully", HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<String> updateCourse(@RequestBody Course course){
//        if(courseRepository.findByName(course.getCourseName()).isPresent()){
//            courseService.updateCourse(course);
//            return new ResponseEntity<>("Updated course successfully", HttpStatus.OK);
//        }
//        courseService.saveCourse(course);
//        return new ResponseEntity<>("Added course successfully", HttpStatus.OK);
//    }

    @DeleteMapping("/remove/{course}")
    public ResponseEntity<Long> removeCourse(@PathVariable Long id){
        var isRemoved = courseService.removeCourse(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }
}
