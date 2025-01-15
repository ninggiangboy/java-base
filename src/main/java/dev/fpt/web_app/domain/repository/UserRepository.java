package dev.fpt.web_app.domain.repository;

import dev.ngb.web_app.application.dto.user.UserMentionResponse;
import dev.fpt.web_app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>, ExtendUserRepository {
    Optional<User> findByEmail(String username);

    @Query("SELECT u.id FROM User u JOIN u.projects p WHERE p.id = :projectId")
    List<UUID> findAllIdsIsProjectMember(Integer projectId);

    @Query("""
            SELECT
                new dev.ngb.web_app.application.dto.user.UserMentionResponse(u.id, u.username, u.fullName)
            FROM User u JOIN u.projects p
            WHERE :projectId IS NULL OR p.id = :projectId
            """)
    List<UserMentionResponse> findAllUserMentionByProjectId(Integer projectId);
}


