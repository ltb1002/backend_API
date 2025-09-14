package vn.anhtuan.demoAPI.POJO;

import java.util.ArrayList;
import java.util.List;

public class ExercisePOJO {
    private String question;
    private List<ExerciseSolutionPOJO> solutions = new ArrayList<>();

    public ExercisePOJO() {
    }

    public ExercisePOJO(String question, List<ExerciseSolutionPOJO> solutions) {
        this.question = question;
        this.solutions = solutions;
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
