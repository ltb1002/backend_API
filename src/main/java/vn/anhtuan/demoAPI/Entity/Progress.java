package vn.anhtuan.demoAPI.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "progress",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "grade", "subject_id"})
)
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ với User
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int grade;

    // Quan hệ với Subject
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private int completedLessons = 0;

    @Column(nullable = false)
    private int totalLessons = 0;

    @Column(nullable = false)
    private double progressPercent = 0.0;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ===== Constructor =====
    public Progress() {}

    public Progress(User user, int grade, Subject subject, int completedLessons, int totalLessons) {
        this.user = user;
        this.grade = grade;
        this.subject = subject;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        updateProgressPercent();
    }

    // ===== Logic tính % tiến độ =====
    public void updateProgressPercent() {
        if (totalLessons > 0) {
            this.progressPercent = Math.round((completedLessons * 100.0 / totalLessons) * 100.0) / 100.0;
        } else {
            this.progressPercent = 0.0;
        }
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public int getCompletedLessons() { return completedLessons; }
    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
        updateProgressPercent();
    }

    public int getTotalLessons() { return totalLessons; }
    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
        updateProgressPercent();
    }

    // progressPercent chỉ đọc, không set thủ công
    public double getProgressPercent() { return progressPercent; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // ===== equals & hashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress)) return false;
        Progress progress = (Progress) o;
        return grade == progress.grade &&
                Objects.equals(user, progress.user) &&
                Objects.equals(subject, progress.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, grade, subject);
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", grade=" + grade +
                ", completedLessons=" + completedLessons +
                ", totalLessons=" + totalLessons +
                ", progressPercent=" + progressPercent +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
