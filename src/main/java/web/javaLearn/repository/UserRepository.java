package web.javaLearn.repository;

import org.springframework.stereotype.Repository;
import web.javaLearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
