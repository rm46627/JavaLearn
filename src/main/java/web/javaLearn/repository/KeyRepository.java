package web.javaLearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.javaLearn.model.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {
}
