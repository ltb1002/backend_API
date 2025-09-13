package vn.anhtuan.demoAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import vn.anhtuan.demoAPI.Entity.*;
import vn.anhtuan.demoAPI.Repository.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final SubjectRepository subjectRepo;
    private final ChapterRepository chapterRepo;
    private final LessonRepository lessonRepo;
    private final LessonContentRepository lessonContentRepo;

    public DataLoader(SubjectRepository subjectRepo,
                      ChapterRepository chapterRepo,
                      LessonRepository lessonRepo,
                      LessonContentRepository lessonContentRepo) {
        this.subjectRepo = subjectRepo;
        this.chapterRepo = chapterRepo;
        this.lessonRepo = lessonRepo;
        this.lessonContentRepo = lessonContentRepo;
    }

    @Override
    public void run(String... args) {
        String[] subjects = {"toan"}; // Thêm các môn khác nếu cần
        int[] grades = {6}; // Thêm các lớp khác nếu cần

        ObjectMapper mapper = new ObjectMapper();

        for (String subjectName : subjects) {
            for (int grade : grades) {
                try {
                    // Kiểm tra dữ liệu đã load chưa
                    if (!subjectRepo.findByCodeAndGrade(subjectName.toLowerCase(), grade).isEmpty()) continue;

                    // Lấy file JSON
                    String fileName = getFileName(subjectName, grade);
                    JsonNode root = mapper.readTree(new ClassPathResource(fileName).getInputStream());

                    for (JsonNode subjectNode : root.path("subjects")) {
                        Subject subject = new Subject();
                        subject.setCode(subjectNode.get("code").asText());
                        subject.setName(subjectNode.get("name").asText());
                        subject.setGrade(subjectNode.get("grade").asInt());
                        subjectRepo.save(subject);

                        for (JsonNode chapterNode : subjectNode.path("chapters")) {
                            Chapter chapter = new Chapter();
                            chapter.setTitle(chapterNode.get("title").asText());
                            chapter.setOrderNo(chapterNode.path("orderNo").asInt(1));
                            chapter.setSubject(subject);
                            chapterRepo.save(chapter);

                            for (JsonNode lessonNode : chapterNode.path("lessons")) {
                                Lesson lesson = new Lesson();
                                lesson.setTitle(lessonNode.get("title").asText());
                                lesson.setVideoUrl(lessonNode.path("videoUrl").asText(""));
                                lesson.setChapter(chapter);
                                lessonRepo.saveAndFlush(lesson); // Đảm bảo ID được tạo

                                int contentOrder = 1;
                                for (JsonNode contentNode : lessonNode.path("contents")) {
                                    LessonContent content = new LessonContent();
                                    content.setLesson(lesson); // Bắt buộc
                                    content.setContentType(ContentType.valueOf(
                                            contentNode.get("type").asText()));
                                    content.setContentValue(contentNode.get("value").asText());
                                    content.setContentOrder(contentOrder++);
                                    lessonContentRepo.save(content);
                                }
                            }
                        }
                    }

                    System.out.println("Loaded data from file: " + fileName);

                } catch (Exception e) {
                    System.err.println("Failed to load data for subject " + subjectName + " grade " + grade);
                    e.printStackTrace();
                }
            }
        }
    }

    private String getFileName(String subject, int grade) {
        switch (subject.toLowerCase()) {
            case "toán":
            case "toan":
                return "toan_" + grade + ".json";
            case "lý":
            case "vatly":
                return "vatly_" + grade + ".json";
            case "văn":
            case "van":
                return "van_" + grade + ".json";
            case "anh":
            case "tienganh":
                return "tienganh_" + grade + ".json";
            default:
                throw new IllegalArgumentException("Môn học không hợp lệ: " + subject);
        }
    }
}
