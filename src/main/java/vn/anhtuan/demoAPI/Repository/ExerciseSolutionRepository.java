package vn.anhtuan.demoAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.anhtuan.demoAPI.Entity.ExerciseSolution;

import java.util.List;

public interface ExerciseSolutionRepository extends JpaRepository<ExerciseSolution, Long> {
    List<ExerciseSolution> findByExerciseId(Long exerciseId);
}
