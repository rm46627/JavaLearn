package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.course.CourseRequest;
import web.javaLearn.model.course.Page;
import web.javaLearn.model.course.PageRequest;
import web.javaLearn.repository.PageRepository;
import web.javaLearn.service.PageService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PageController {

    PageService pageService;
    PageRepository pageRepository;

    ////
    //  Admin
    ////

    @PostMapping("/admin/pages/save")
    public ResponseEntity<String> savePage(@RequestBody PageRequest pageRequest){
        pageService.savePage(pageRequest);
        return new ResponseEntity<>("Added page successfully", HttpStatus.OK);
    }

    @DeleteMapping("/admin/pages/remove/{id}")
    public ResponseEntity<Long> removeCourse(@PathVariable Long id){
        var isRemoved = pageService.removePage(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

//    @PutMapping("/admin/pages/update/{id}")
//    public ResponseEntity<String> updatePage(@PathVariable Long id, @RequestBody PageRequest pageRequest){
//        pageService.upadatePage(id, pageRequest);
//        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
//    }

    ////
    // All Users
    ////

    @GetMapping("/pages/{id}")
    public ResponseEntity<List<Page>> getAllPagesByCourse(@PathVariable Long courseId) {
        return new ResponseEntity<>(pageService.getAllPagesByCourse(courseId), HttpStatus.OK);
    }

}