package web.javaLearn.model.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float pageOrder;
    @Enumerated(EnumType.STRING)
    private PageType type;
    private String data;

    @ManyToOne()
    @JsonBackReference
    private Course course;
}
