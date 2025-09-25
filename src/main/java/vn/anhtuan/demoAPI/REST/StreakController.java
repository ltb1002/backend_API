package vn.anhtuan.demoAPI.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anhtuan.demoAPI.Entity.UserStreak;
import vn.anhtuan.demoAPI.Service.UserStreakService;


    @RestController
    @RequestMapping("/api/streak")
    public class StreakController {

        private final UserStreakService streakService;

        public StreakController(UserStreakService streakService) {
            this.streakService = streakService;
        }

        @GetMapping("/{userId}")
        public ResponseEntity<UserStreak> getStreak(@PathVariable Long userId) {
            return ResponseEntity.ok(streakService.getStreak(userId));
        }

        @PostMapping("/update")
        public ResponseEntity<UserStreak> updateStreak(@RequestParam Long userId) {
            return ResponseEntity.ok(streakService.updateStreak(userId));
        }
    }


