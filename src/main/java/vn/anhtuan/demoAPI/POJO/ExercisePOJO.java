package vn.anhtuan.demoAPI.POJO;

import java.util.ArrayList;
import java.util.List;

public class ExercisePOJO {
    private Long id; // thêm id
    private String question;
    private List<ExerciseSolutionPOJO> solutions = new ArrayList<>();

    public ExercisePOJO() {
    }

    public ExercisePOJO(Long id, String question, List<ExerciseSolutionPOJO> solutions) {
        this.id = id;
        this.question = question;
        this.solutions = solutions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ExerciseSolutionPOJO> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<ExerciseSolutionPOJO> solutions) {
        this.solutions = solutions;
    }
}
