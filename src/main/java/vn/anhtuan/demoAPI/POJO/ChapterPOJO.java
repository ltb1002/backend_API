package vn.anhtuan.demoAPI.POJO;

import java.util.List;

public class ChapterPOJO {
    private Long id;
    private String title;
    private int orderNo;
    private List<LessonPOJO> lessons;

    public ChapterPOJO() {
    }

    public ChapterPOJO(Long id, String title, int orderNo, List<LessonPOJO> lessons) {
        this.id = id;
        this.title = title;
        this.orderNo = orderNo;
        this.lessons = lessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public List<LessonPOJO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonPOJO> lessons) {
        this.lessons = lessons;
    }
}
