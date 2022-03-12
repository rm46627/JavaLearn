package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
