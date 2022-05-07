package web.javaLearn.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.CourseRequest;
import web.javaLearn.model.course.Page;
import web.javaLearn.model.course.PageRequest;
import web.javaLearn.repository.CourseRepository;
import web.javaLearn.repository.PageRepository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PageService {

    PageRepository pageRepository;
    CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Page> getAllPagesByCourse(Long courseId) {
        return new ArrayList<>(pageRepository.findAllPagesByCourse(courseId));
    }

    @Transactional
    public Page savePage(PageRequest pageRequest, Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow();
        Page p = Page.builder()
                .name(pageRequest.getName())
                .pageOrder(pageRepository.findFirstByCourseOrderByPageOrderDesc(courseId).getPageOrder()+1)
                .type(pageRequest.getType())
                .data(pageRequest.getData())
                .course(c)
                .build();
        pageRepository.save(p);
        return p;
    }

//    public Course updatePage(Long id, PageRequest pageRequest) {
//        Page p = page.findById(id).orElseThrow();
//        c.setTitle(courseRequest.getTitle());
//        c.setDescription(courseRequest.getDescription());
//        courseRepository.save(c);
//        return c;
//    }

    @Transactional
    public boolean removePage(Long id) {
        if(pageRepository.findById(id).isPresent()){
            pageRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
