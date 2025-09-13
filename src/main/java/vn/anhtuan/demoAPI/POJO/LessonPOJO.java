package vn.anhtuan.demoAPI.POJO;

import java.util.List;

public class LessonPOJO {
    private Long id;
    private String title;
    private String videoUrl;
    private List<LessonContentPOJO> contents;

    public LessonPOJO() {
    }

    public LessonPOJO(Long id, String title, String videoUrl, List<LessonContentPOJO> contents) {
        this.id = id;
        this.title = title;
        this.videoUrl = videoUrl;
        this.contents = contents;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<LessonContentPOJO> getContents() {
        return contents;
    }

    public void setContents(List<LessonContentPOJO> contents) {
        this.contents = contents;
    }
}
