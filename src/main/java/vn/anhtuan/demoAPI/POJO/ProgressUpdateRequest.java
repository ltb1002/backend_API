package vn.anhtuan.demoAPI.POJO;

public class ProgressUpdateRequest {
    private Long userId;
    private Integer grade;
    private Long subjectId;       // thay vì String subject
    private Integer completedLessons;
    private Integer totalLessons;
    private Long lessonId;        // liên kết tới Lesson
    private Long chapterId;       // liên kết tới Chapter

    public ProgressUpdateRequest() {}

    public ProgressUpdateRequest(Long userId, Integer grade, Long subjectId,
                                 Integer completedLessons, Integer totalLessons,
                                 Long lessonId, Long chapterId) {
        this.userId = userId;
        this.grade = grade;
        this.subjectId = subjectId;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.lessonId = lessonId;
        this.chapterId = chapterId;
    }

    // ===== Getters & Setters =====
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public Integer getCompletedLessons() { return completedLessons; }
    public void setCompletedLessons(Integer completedLessons) { this.completedLessons = completedLessons; }

    public Integer getTotalLessons() { return totalLessons; }
    public void setTotalLessons(Integer totalLessons) { this.totalLessons = totalLessons; }

    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public Long getChapterId() { return chapterId; }
    public void setChapterId(Long chapterId) { this.chapterId = chapterId; }
}
