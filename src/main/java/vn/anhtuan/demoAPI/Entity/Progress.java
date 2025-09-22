package vn.anhtuan.demoAPI.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "progress",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","grade","subject"})
)
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ với User
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer grade;

    @Column(nullable = false)
    private String subject;

    private Integer completedLessons = 0;
    private Integer totalLessons = 0;

    private Double progressPercent = 0.0;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Progress() {}

    public Progress(User user, Integer grade, String subject, Integer completedLessons,
                    Integer totalLessons) {
        this.user = user;
        this.grade = grade;
        this.subject = subject;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        updateProgressPercent();
    }

    // ===== Logic để tự tính progressPercent =====
    public void updateProgressPercent() {
        if (totalLessons != null && totalLessons > 0 && completedLessons != null) {
            this.progressPercent = (completedLessons * 100.0) / totalLessons;
        } else {
            this.progressPercent = 0.0;
        }
    }

    // ===== Getters và Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Integer getCompletedLessons() { return completedLessons; }
    public void setCompletedLessons(Integer completedLessons) {
        this.completedLessons = completedLessons;
        updateProgressPercent();
    }

    public Integer getTotalLessons() { return totalLessons; }
    public void setTotalLessons(Integer totalLessons) {
        this.totalLessons = totalLessons;
        updateProgressPercent();
    }

    public Double getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Double progressPercent) { this.progressPercent = progressPercent; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
