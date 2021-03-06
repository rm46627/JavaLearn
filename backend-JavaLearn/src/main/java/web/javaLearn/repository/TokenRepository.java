package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.auth.Token;
import web.javaLearn.model.auth.User;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    void deleteByUser(User user);
}
