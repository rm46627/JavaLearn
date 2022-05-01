package web.javaLearn.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.auth.Role;
import web.javaLearn.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    boolean existsById(Long id);

    @Modifying
    @Query("update User set role = :role where id = :id")
    void updateUserRole(@Param("id") Long id, @Param("role") Role role);
}
