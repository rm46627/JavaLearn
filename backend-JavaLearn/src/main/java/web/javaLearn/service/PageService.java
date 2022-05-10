package web.javaLearn.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.Page;
import web.javaLearn.model.course.PageRequest;
import web.javaLearn.repository.CourseRepository;
import web.javaLearn.repository.PageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class PageService {

    CourseRepository courseRepository;
    PageRepository pageRepository;

    @Transactional(readOnly = true)
    public List<Page> getAllPagesByCourse(Long courseId) {
        return new ArrayList<>(pageRepository.findAllPagesByCourse(courseId));
    }

    @Transactional
    public void savePage(PageRequest pageRequest, Long courseId) {
        List<Page> lp = pageRepository.findAllPagesByCourse(courseId);
        Float ord;
        if(!lp.isEmpty()) {
            ord = lp.get(lp.size() - 1).getPageOrder();
        } else {
            ord = 1F;
        }
        courseRepository.findById(courseId)
                .map(course -> course.addPage(Page.builder()
                        .name(pageRequest.getName())
                        .pageOrder(ord + 1)
                        .type(pageRequest.getType())
                        .data(pageRequest.getData())
                        .course(course)
                        .build()))
                .orElseThrow();
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
