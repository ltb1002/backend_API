package vn.anhtuan.demoAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anhtuan.demoAPI.Entity.User;
import vn.anhtuan.demoAPI.Entity.UserStreak;
import vn.anhtuan.demoAPI.Repository.UserRepository;
import vn.anhtuan.demoAPI.Repository.UserStreakRepository;

import java.time.LocalDate;

@Service
public class UserStreakService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserStreakRepository streakRepo;

    public UserStreak updateStreak(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserStreak streak = streakRepo.findByUser(user).orElse(new UserStreak(user));
        LocalDate today = LocalDate.now();
        LocalDate last = streak.getLastActiveDate();

        if (last.equals(today)) {
            // đã học hôm nay
        } else if (last.plusDays(1).equals(today)) {
            streak.setStreakCount(streak.getStreakCount() + 1);
        } else {
            streak.setStreakCount(1);
        }

        streak.setTotalDays(streak.getTotalDays() + 1);
        if (streak.getStreakCount() > streak.getBestStreak()) {
            streak.setBestStreak(streak.getStreakCount());
        }

        streak.setLastActiveDate(today);
        return streakRepo.save(streak);
    }

    public UserStreak getStreak(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return streakRepo.findByUser(user).orElse(new UserStreak(user));
    }
}
