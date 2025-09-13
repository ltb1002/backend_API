package vn.anhtuan.demoAPI.REST;

import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.Entity.Subject;
import vn.anhtuan.demoAPI.Entity.Chapter;
import vn.anhtuan.demoAPI.Entity.Lesson;
import vn.anhtuan.demoAPI.Entity.LessonContent;
import vn.anhtuan.demoAPI.POJO.SubjectPOJO;
import vn.anhtuan.demoAPI.POJO.ChapterPOJO;
import vn.anhtuan.demoAPI.POJO.LessonPOJO;
import vn.anhtuan.demoAPI.POJO.LessonContentPOJO;
import vn.anhtuan.demoAPI.Repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TheoryController {

    private final SubjectRepository subjectRepo;
    private final ChapterRepository chapterRepo;
    private final LessonRepository lessonRepo;
    private final LessonContentRepository lessonContentRepo;

    public TheoryController(SubjectRepository subjectRepo,
                            ChapterRepository chapterRepo,
                            LessonRepository lessonRepo,
                            LessonContentRepository lessonContentRepo) {
        this.subjectRepo = subjectRepo;
        this.chapterRepo = chapterRepo;
        this.lessonRepo = lessonRepo;
        this.lessonContentRepo = lessonContentRepo;
    }

    @GetMapping("/subjects")
    public List<SubjectPOJO> getSubjects(@RequestParam(required = false) Integer grade) {
        List<Subject> subjects = (grade != null)
                ? subjectRepo.findAll().stream().filter(s -> s.getGrade() == grade).collect(Collectors.toList())
                : subjectRepo.findAll();

        return subjects.stream()
                .map(this::convertSubject)
                .collect(Collectors.toList());
    }

    @GetMapping("/subjects/{subjectId}/chapters")
    public List<ChapterPOJO> getChapters(@PathVariable Long subjectId) {
        List<Chapter> chapters = chapterRepo.findBySubjectId(subjectId);
        return chapters.stream()
                .map(this::convertChapter)
                .collect(Collectors.toList());
    }

    @GetMapping("/chapters/{chapterId}/lessons")
    public List<LessonPOJO> getLessons(@PathVariable Long chapterId) {
        List<Lesson> lessons = lessonRepo.findByChapterId(chapterId);
        return lessons.stream()
                .map(this::convertLesson)
                .collect(Collectors.toList());
    }

    @GetMapping("/lessons/{lessonId}/contents")
    public List<LessonContentPOJO> getContents(@PathVariable Long lessonId) {
        List<LessonContent> contents = lessonContentRepo.findByLessonId(lessonId);
        return contents.stream()
                .map(this::convertLessonContent)
                .collect(Collectors.toList());
    }

    // =================== Chuyển đổi Entity → POJO ===================
    private SubjectPOJO convertSubject(Subject s) {
        List<ChapterPOJO> chapters = s.getChapters() != null
                ? s.getChapters().stream().map(this::convertChapter).collect(Collectors.toList())
                : List.of();
        return new SubjectPOJO(s.getId(), s.getCode(), s.getName(), s.getGrade(), chapters);
    }

    private ChapterPOJO convertChapter(Chapter c) {
        List<LessonPOJO> lessons = c.getLessons() != null
                ? c.getLessons().stream().map(this::convertLesson).collect(Collectors.toList())
                : List.of();
        return new ChapterPOJO(c.getId(), c.getTitle(), c.getOrderNo(), lessons);
    }

    private LessonPOJO convertLesson(Lesson l) {
        List<LessonContentPOJO> contents = l.getContents() != null
                ? l.getContents().stream().map(this::convertLessonContent).collect(Collectors.toList())
                : List.of();
        return new LessonPOJO(l.getId(), l.getTitle(), l.getVideoUrl(), contents);
    }

    private LessonContentPOJO convertLessonContent(LessonContent lc) {
        return new LessonContentPOJO(lc.getId(), lc.getContentType(), lc.getContentValue(), lc.getContentOrder());
    }
}
