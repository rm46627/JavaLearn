package web.javaLearn.model.course;

import lombok.Data;

import java.util.List;

@Data
public class CourseRequest {

    private String courseName;
    private String courseDescription;
    List<Page> pageList;
}
