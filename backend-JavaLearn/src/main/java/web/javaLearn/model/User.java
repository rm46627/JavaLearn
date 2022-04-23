package web.javaLearn.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    @NotNull
    private String email;
    private boolean enabled; // is email confirmed
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createTime;

    @Transient
    private String accessToken;
    @Transient
    private String refreshToken;
}
