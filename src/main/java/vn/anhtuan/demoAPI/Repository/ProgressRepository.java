package vn.anhtuan.demoAPI.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anhtuan.demoAPI.Entity.Progress;
import vn.anhtuan.demoAPI.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserAndGradeAndSubject(User user, Integer grade, String subject);
    List<Progress> findByUserAndGrade(User user, Integer grade);
    List<Progress> findByUser(User user);
}

