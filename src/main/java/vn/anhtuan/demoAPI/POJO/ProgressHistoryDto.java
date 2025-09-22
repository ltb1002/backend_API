
package vn.anhtuan.demoAPI.POJO;

import java.time.LocalDateTime;

public class ProgressHistoryDto {
    private Long userId;            // thêm định danh user
    private String subject;
    private Integer grade;
    private Double progressPercent;
    private LocalDateTime recordedAt;

    public ProgressHistoryDto() {}

    public ProgressHistoryDto(Long userId, String subject, Integer grade,
                              Double progressPercent, LocalDateTime recordedAt) {
        this.userId = userId;
        this.subject = subject;
        this.grade = grade;
        this.progressPercent = progressPercent;
        this.recordedAt = recordedAt;
    }

    // ===== Getters & Setters =====
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }

    public Double getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Double progressPercent) { this.progressPercent = progressPercent; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
