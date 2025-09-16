package vn.anhtuan.demoAPI.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pdf_files")
public class PdfFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "exam_type", nullable = false)
    private String examType;

    // Constructors
    public PdfFile() {
    }

    public PdfFile(String fileName, String description, String subject, String grade, String examType) {
        this.fileName = fileName;
        this.description = description;
        this.uploadDate = LocalDateTime.now();
        this.subject = subject;
        this.grade = grade;
        this.examType = examType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }
}