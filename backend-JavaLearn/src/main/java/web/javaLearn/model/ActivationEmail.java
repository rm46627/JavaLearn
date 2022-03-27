package web.javaLearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationEmail {
    private String subject;
    private String recipient;
    private String body;
}
