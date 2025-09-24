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
        System.out.println("Requesting progress for userId=" + userId + ", grade=" + grade);
        List<ProgressDto> result = progressService.getProgressByUserAndGrade(userId, grade);
        if (result.isEmpty()) {
            System.out.println("No progress found for userId=" + userId + ", grade=" + grade);
        }
        return result;
    }

    @GetMapping("/user/{userId}")
    public List<ProgressDto> getProgressByUser(@PathVariable Long userId) {
        System.out.println("Requesting progress for userId=" + userId);
        List<ProgressDto> result = progressService.getProgressByUser(userId);
        if (result.isEmpty()) {
            System.out.println("No progress found for userId=" + userId);
        }
        return result;
    }


    @PostMapping("/update")
    public ProgressDto updateProgress(@RequestBody ProgressUpdateRequest req) {
        return progressService.updateProgress(req);
    }

    @GetMapping("/history")
    public List<ProgressHistoryDto> getHistory(
            @RequestParam Long userId,
            @RequestParam Long subjectId,
            @RequestParam String range) {
        return progressService.getHistory(userId, subjectId, range);
    }
}
