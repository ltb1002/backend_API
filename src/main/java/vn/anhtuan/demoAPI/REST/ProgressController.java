package vn.anhtuan.demoAPI.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.POJO.ProgressDto;
import vn.anhtuan.demoAPI.POJO.ProgressHistoryDto;
import vn.anhtuan.demoAPI.POJO.ProgressUpdateRequest;
import vn.anhtuan.demoAPI.Service.ProgressService;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/user/{userId}/grade/{grade}")
    public List<ProgressDto> getProgressByUserAndGrade(
            @PathVariable Long userId,
            @PathVariable Integer grade) {
        return progressService.getProgressByUserAndGrade(userId, grade);
    }

    @GetMapping("/user/{userId}")
    public List<ProgressDto> getProgressByUser(@PathVariable Long userId) {
        return progressService.getProgressByUser(userId);
    }

    @PostMapping("/update")
    public ProgressDto updateProgress(@RequestBody ProgressUpdateRequest req) {
        // Giờ đây req có cả lessonId, chapterId -> service sẽ map sang Lesson, Chapter
        return progressService.updateProgress(req);
    }

    @GetMapping("/history")
    public List<ProgressHistoryDto> getHistory(
            @RequestParam Long userId,
            @RequestParam Long subject,
            @RequestParam String range) {
        return progressService.getHistory(userId, subject, range);
    }
}
