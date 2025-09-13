package vn.anhtuan.demoAPI.POJO;

import vn.anhtuan.demoAPI.Entity.ContentType;

public class LessonContentPOJO {
    private Long id;
    private ContentType contentType;
    private String contentValue;
    private int contentOrder;

    public LessonContentPOJO() {
    }

    public LessonContentPOJO(Long id, ContentType contentType, String contentValue, int contentOrder) {
        this.id = id;
        this.contentType = contentType;
        this.contentValue = contentValue;
        this.contentOrder = contentOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
