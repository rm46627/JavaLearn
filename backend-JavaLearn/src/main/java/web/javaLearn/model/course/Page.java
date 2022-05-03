package web.javaLearn.model.course;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float pageOrder;
    @Enumerated(EnumType.STRING)
    private PageType type;
    private String data;

    @ManyToOne(cascade = CascadeType.ALL)
    Course course;
}
