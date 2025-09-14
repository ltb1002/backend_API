package vn.anhtuan.demoAPI.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.Entity.PdfFile;
import vn.anhtuan.demoAPI.Repository.PdfFileRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "*")
public class PdfController {

    @Autowired
    private PdfFileRepository pdfFileRepository;

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/files/" + fileName);
        if (inputStream == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(new InputStreamResource(inputStream));
    }
    @GetMapping("/list")
    public List<PdfFile> getPdfFiles(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String examType) {

        if (subject != null && grade != null && examType != null) {
            return pdfFileRepository.findBySubjectAndGradeAndExamType(subject, grade, examType);
        } else if (subject != null && grade != null) {
            return pdfFileRepository.findBySubjectAndGrade(subject, grade);
        } else {
            return pdfFileRepository.findAll();
        }
    }

    @GetMapping("/subjects")
    public List<String> getDistinctSubjects() {
        return pdfFileRepository.findDistinctSubjects();
    }

    @GetMapping("/grades")
    public List<String> getDistinctGrades() {
        return pdfFileRepository.findDistinctGrades();
    }

    @GetMapping("/exam-types")
    public List<String> getDistinctExamTypes(@RequestParam String subject, @RequestParam String grade) {
        return pdfFileRepository.findDistinctExamTypesBySubjectAndGrade(subject, grade);
    }
}