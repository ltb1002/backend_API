package vn.anhtuan.demoAPI.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_solutions")
public class ExerciseSolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonBackReference
    private Exercise exercise;

    @Enumerated(EnumType.STRING)
    private ContentType solutionType; // text hoặc image

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solutionValue;

    @Column(name = "solution_order", nullable = false)
    private int solutionOrder;

    // Constructors
    public ExerciseSolution() {
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public ContentType getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(ContentType solutionType) {
        this.solutionType = solutionType;
    }

    public String getSolutionValue() {
        return solutionValue;
    }

    public void setSolutionValue(String solutionValue) {
        this.solutionValue = solutionValue;
    }

    public int getSolutionOrder() {
        return solutionOrder;
    }

    public void setSolutionOrder(int solutionOrder) {
        this.solutionOrder = solutionOrder;
    }
    // trong ExerciseSolution.java
    public ContentType getType() {
        return this.solutionType;
    }

    public String getValue() {
        return this.solutionValue;
    }

    // và nếu cần setter alias:
    public void setType(ContentType type) {
        this.solutionType = type;
    }

    public void setValue(String value) {
        this.solutionValue = value;
    }
}
