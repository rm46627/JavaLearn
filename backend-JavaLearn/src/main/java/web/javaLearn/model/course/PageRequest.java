package web.javaLearn.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    private Float order;
    private String name;
    private String data;
    private PageType type;
}
