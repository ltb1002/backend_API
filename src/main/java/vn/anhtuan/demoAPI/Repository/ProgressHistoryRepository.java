    package vn.anhtuan.demoAPI.Repository;


    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import vn.anhtuan.demoAPI.Entity.ProgressHistory;
    import vn.anhtuan.demoAPI.Entity.User;

    import java.time.LocalDateTime;
    import java.util.List;

    @Repository
    public interface ProgressHistoryRepository extends JpaRepository<ProgressHistory, Long> {
        List<ProgressHistory> findByUserAndSubjectAndRecordedAtBetween(User user, String subject, LocalDateTime start, LocalDateTime end);
        List<ProgressHistory> findByUserAndSubject(User user, String subject);
    }

