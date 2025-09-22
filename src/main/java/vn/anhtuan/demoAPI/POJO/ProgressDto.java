package vn.anhtuan.demoAPI.POJO;

import java.time.LocalDateTime;

public class ProgressDto {
    private String subject;
    private Integer grade;
    private Integer completedLessons;
    private Integer totalLessons;
    private Double progressPercent;
    private LocalDateTime updatedAt;

    public ProgressDto() {}

    public ProgressDto(String subject, Integer grade, Integer completedLessons,
                       Integer totalLessons, Double progressPercent, LocalDateTime updatedAt) {
        this.subject = subject;
        this.grade = grade;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.progressPercent = progressPercent;
        this.updatedAt = updatedAt;
    }

    // ===== Getters & Setters =====
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }

    public Integer getCompletedLessons() { return completedLessons; }
    public void setCompletedLessons(Integer completedLessons) { this.completedLessons = completedLessons; }

    public Integer getTotalLessons() { return totalLessons; }
    public void setTotalLessons(Integer totalLessons) { this.totalLessons = totalLessons; }

    public Double getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Double progressPercent) { this.progressPercent = progressPercent; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
