package vn.anhtuan.demoAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.anhtuan.demoAPI.Entity.PdfFile;

import java.util.List;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
    PdfFile findByFileName(String fileName);

    List<PdfFile> findBySubjectAndGrade(String subject, String grade);

    List<PdfFile> findBySubjectAndGradeAndExamType(String subject, String grade, String examType);

    @Query("SELECT DISTINCT p.subject FROM PdfFile p")
    List<String> findDistinctSubjects();

    @Query("SELECT DISTINCT p.grade FROM PdfFile p")
    List<String> findDistinctGrades();

    @Query("SELECT DISTINCT p.examType FROM PdfFile p WHERE p.subject = :subject AND p.grade = :grade")
    List<String> findDistinctExamTypesBySubjectAndGrade(@Param("subject") String subject, @Param("grade") String grade);
}