package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.auth.Token;
import web.javaLearn.model.course.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    @Query(value = "SELECT * FROM page WHERE course_id = ?1 ORDER BY page_order", nativeQuery = true)
    List<Page> findAllPagesByCourse(Long courseId);

    //repository.findLimited(..., new PageRequest(0, 10));
    @Query(value = "SELECT p.page_order FROM page WHERE course_id = ?1 ORDER BY page_order DESC", nativeQuery = true)
    Float getLastPageNumberLimited(Long courseId, Pageable pageable);
}
