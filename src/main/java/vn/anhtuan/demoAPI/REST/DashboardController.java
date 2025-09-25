package vn.anhtuan.demoAPI.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.Entity.UserStreak;
import vn.anhtuan.demoAPI.POJO.ProgressDto;
import vn.anhtuan.demoAPI.Service.ProgressService;
import vn.anhtuan.demoAPI.Service.UserStreakService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final ProgressService progressService;
    private final UserStreakService streakService;

    public DashboardController(ProgressService progressService, UserStreakService streakService) {
        this.progressService = progressService;
        this.streakService = streakService;
    }

    /**
     * API lấy tổng quan dashboard: progress + streak
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getDashboard(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();

        // ✅ Lấy progress
        List<ProgressDto> progressList = progressService.getProgressByUser(userId);
        result.put("progress", progressList);

        // ✅ Lấy streak
        UserStreak streak = streakService.getStreak(userId);
        Map<String, Object> streakMap = new HashMap<>();
        streakMap.put("currentStreak", streak.getStreakCount());
        streakMap.put("bestStreak", streak.getBestStreak());
        streakMap.put("totalDays", streak.getTotalDays());
        streakMap.put("lastActiveDate", streak.getLastActiveDate());

        result.put("streak", streakMap);

        return ResponseEntity.ok(result);
    }
}
