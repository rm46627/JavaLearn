package web.javaLearn.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import web.javaLearn.model.course.Course;
import web.javaLearn.model.course.Page;
import web.javaLearn.model.course.PageType;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void saveAndDelete_Success(){
        Page p1 = Page.builder()
                .data("<H3>LEKCJA 1<h3>")
                .type(PageType.TEXT)
                .pageOrder(1F)
                .name("Lekcja 1")
                .build();
        Page p2 = Page.builder()
                .data("<H3>LEKCJA 2<h3>")
                .type(PageType.TEXT)
                .pageOrder(2F)
                .name("Lekcja 2")
                .build();

        Course c = Course.builder()
                .name("Kurs Kotlina")
                .description("Opis mojego nowego kursu.")
                .pageList(Arrays.asList(p1, p2))
                .build();

        courseRepository.save(c);
        Course c2 = courseRepository.findByName(c.getName()).orElseThrow();

        Assert.assertEquals(c2.getDescription(), c.getDescription());

        courseRepository.delete(c2);
        Assert.assertFalse(courseRepository.findByName(c.getName()).isPresent());

    }

}
