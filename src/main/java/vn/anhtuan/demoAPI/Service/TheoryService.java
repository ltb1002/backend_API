package vn.anhtuan.demoAPI.Service;

import org.springframework.stereotype.Service;
import vn.anhtuan.demoAPI.Entity.Subject;
import vn.anhtuan.demoAPI.Repository.SubjectRepository;

import java.util.List;

@Service
public class TheoryService {
    private final SubjectRepository subjectRepository;

    public TheoryService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getTheory(String subjectCode, int grade) {
        return subjectRepository.findByCodeAndGrade(subjectCode, grade);
    }
}
