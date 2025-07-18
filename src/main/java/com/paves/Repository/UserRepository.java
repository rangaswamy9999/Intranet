package  com.paves.Repository;

import com.paves.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    Optional<User> findByUserId(String id);
    void deleteByUserId(String id);
    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r.roleId = :roleId")
    long countUsersByRoleId(@Param("roleId") Long roleId);

}