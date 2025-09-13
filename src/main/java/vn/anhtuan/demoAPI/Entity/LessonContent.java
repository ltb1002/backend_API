package vn.anhtuan.demoAPI.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "lesson_contents")
public class LessonContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    private ContentType contentType; // TEXT hoặc IMAGE

    @Column(columnDefinition = "TEXT")
    private String contentValue;

    private int contentOrder; // Thứ tự hiển thị trong lesson

    // Constructors
    public LessonContent() {
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public int getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(int contentOrder) {
        this.contentOrder = contentOrder;
    }
}
