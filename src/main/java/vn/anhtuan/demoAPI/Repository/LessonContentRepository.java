package vn.anhtuan.demoAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.anhtuan.demoAPI.Entity.LessonContent;

import java.util.List;

public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {
    List<LessonContent> findByLessonId(Long lessonId);
}
