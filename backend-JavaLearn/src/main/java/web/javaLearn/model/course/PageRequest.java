package web.javaLearn.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    private String name;
    private Float pageOrder;
    private PageType type;
    private String data;
    private Long courseId;
}
