package web.javaLearn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Token {
    @Id
    private String tokenId;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime createDate;
    private LocalDateTime expirationDate;
}
