package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.course.Page;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    @Query(value = "SELECT * FROM page WHERE course_id = ?1 ORDER BY page_order", nativeQuery = true)
    List<Page> findAllPagesByCourse(Long courseId);

    Page findFirstByCourseOrderByPageOrderDesc(Long courseId);
}
