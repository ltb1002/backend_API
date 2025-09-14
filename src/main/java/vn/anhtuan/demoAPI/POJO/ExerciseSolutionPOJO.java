package vn.anhtuan.demoAPI.POJO;

public class ExerciseSolutionPOJO {
    private String type;   // text / image
    private String value;  // nội dung (chuỗi text hoặc url ảnh)

    public ExerciseSolutionPOJO() {
    }

    public ExerciseSolutionPOJO(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
