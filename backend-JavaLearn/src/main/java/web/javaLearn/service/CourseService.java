package web.javaLearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.CourseRequest;
import web.javaLearn.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Transactional
    public Course saveCourse(CourseRequest courseRequest) {
        Course c = Course.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .build();
        courseRepository.save(c);
        return c;
    }

    @Transactional
    public Course updateCourse(Long id, CourseRequest courseRequest) {
        Course c = courseRepository.findById(id).orElseThrow();
        c.setTitle(courseRequest.getTitle());
        c.setDescription(courseRequest.getDescription());
        courseRepository.save(c);
        return c;
    }

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
