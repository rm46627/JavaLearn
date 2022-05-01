package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.auth.Token;
import web.javaLearn.model.course.Course;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Token, Long> {
    Optional<Course> findByName(String name);
}
