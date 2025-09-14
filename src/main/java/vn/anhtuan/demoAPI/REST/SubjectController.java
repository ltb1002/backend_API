package vn.anhtuan.demoAPI.REST;

import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.Entity.*;
import vn.anhtuan.demoAPI.POJO.*;

import vn.anhtuan.demoAPI.Repository.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SubjectController {

    private final SubjectRepository subjectRepo;
    private final ChapterRepository chapterRepo;
    private final LessonRepository lessonRepo;
    private final LessonContentRepository lessonContentRepo;
    private final ExerciseRepository exerciseRepo;
    private final ExerciseSolutionRepository exerciseSolutionRepo;

    public SubjectController(SubjectRepository subjectRepo,
                             ChapterRepository chapterRepo,
                             LessonRepository lessonRepo,
                             LessonContentRepository lessonContentRepo,
                             ExerciseRepository exerciseRepo,
                             ExerciseSolutionRepository exerciseSolutionRepo) {
        this.subjectRepo = subjectRepo;
        this.chapterRepo = chapterRepo;
        this.lessonRepo = lessonRepo;
        this.lessonContentRepo = lessonContentRepo;
        this.exerciseRepo = exerciseRepo;
        this.exerciseSolutionRepo = exerciseSolutionRepo;
    }

    // =================== Lấy tất cả subjects (có thể filter theo grade) ===================
    @GetMapping("/subjects")
    public List<SubjectPOJO> getSubjects(@RequestParam(required = false) Integer grade) {
        List<Subject> subjects;

        if (grade != null) {
            subjects = subjectRepo.findAll().stream()
                    .filter(s -> Objects.equals(s.getGrade(), grade))
                    .collect(Collectors.toList());
        } else {
            subjects = subjectRepo.findAll();
        }

        return subjects.stream()
                .map(this::convertSubject)
                .collect(Collectors.toList());
    }

    // =================== Lấy 1 subject theo grade + code ===================
    @GetMapping("/grades/{grade}/subjects/{code}")
    public SubjectPOJO getSubjectByGradeAndCode(
            @PathVariable Integer grade,
            @PathVariable String code
    ) {
        Subject subject = subjectRepo.findAll().stream()
                .filter(s -> Objects.equals(s.getGrade(), grade)
                        && s.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy môn '" + code + "' cho khối " + grade
                ));

        return convertSubject(subject);
    }

    // =================== Lấy danh sách chapters theo subjectId ===================
    @GetMapping("/subjects/{subjectId}/chapters")
    public List<ChapterPOJO> getChapters(@PathVariable Long subjectId) {
        List<Chapter> chapters = chapterRepo.findBySubjectId(subjectId);
        return chapters.stream()
                .map(this::convertChapter)
                .collect(Collectors.toList());
    }

    // =================== Lấy danh sách lessons theo chapterId ===================
    @GetMapping("/chapters/{chapterId}/lessons")
    public List<LessonPOJO> getLessons(@PathVariable Long chapterId) {
        List<Lesson> lessons = lessonRepo.findByChapterId(chapterId);
        return lessons.stream()
                .map(this::convertLesson)
                .collect(Collectors.toList());
    }

    // =================== Lấy nội dung bài học theo lessonId ===================
    @GetMapping("/lessons/{lessonId}/contents")
    public List<LessonContentPOJO> getContents(@PathVariable Long lessonId) {
        List<LessonContent> contents = lessonContentRepo.findByLessonId(lessonId);
        return contents.stream()
                .map(this::convertLessonContent)
                .collect(Collectors.toList());
    }

    // =================== Lấy tất cả exercises theo lessonId ===================
    @GetMapping("/lessons/{lessonId}/exercises")
    public List<ExercisePOJO> getExercises(@PathVariable Long lessonId) {
        List<Exercise> exercises = exerciseRepo.findByLessonId(lessonId);
        return exercises.stream()
                .map(this::convertExercise)
                .collect(Collectors.toList());
    }

    // =================== Lấy danh sách solution theo exerciseId ===================
    @GetMapping("/exercises/{exerciseId}/solutions")
    public List<ExerciseSolutionPOJO> getExerciseSolutions(@PathVariable Long exerciseId) {
        List<ExerciseSolution> solutions = exerciseSolutionRepo.findByExerciseId(exerciseId);
        return solutions.stream()
                .map(this::convertExerciseSolution)
                .collect(Collectors.toList());
    }

    // =================== Convert Entity → POJO ===================
    private ExercisePOJO convertExercise(Exercise e) {
        List<ExerciseSolutionPOJO> solutions = e.getSolutions() != null
                ? e.getSolutions().stream().map(this::convertExerciseSolution).collect(Collectors.toList())
                : List.of();
        return new ExercisePOJO(e.getQuestion(), solutions);
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

    private ExerciseSolutionPOJO convertExerciseSolution(ExerciseSolution es) {
        String type = es.getType() != null ? es.getType().toString() : null;
        return new ExerciseSolutionPOJO(type, es.getValue());
    }
}
