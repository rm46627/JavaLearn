package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.auth.User;
import web.javaLearn.model.course.Course;
import web.javaLearn.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Transactional
    public Course saveCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

//    public void updateCourse(Course course) {
//        courseRepository.save(course);
//        return course;
//    }

    @Transactional
    public boolean removeCourse(Long id) {
        if(courseRepository.findById(id).isPresent()){
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }
}
