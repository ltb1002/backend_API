package vn.anhtuan.demoAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vn.anhtuan.demoAPI.Entity.Progress;
import vn.anhtuan.demoAPI.Entity.ProgressHistory;
import vn.anhtuan.demoAPI.Entity.User;
import vn.anhtuan.demoAPI.POJO.ProgressDto;
import vn.anhtuan.demoAPI.POJO.ProgressHistoryDto;
import vn.anhtuan.demoAPI.POJO.ProgressUpdateRequest;
import vn.anhtuan.demoAPI.Repository.ProgressHistoryRepository;
import vn.anhtuan.demoAPI.Repository.ProgressRepository;
import vn.anhtuan.demoAPI.Repository.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private ProgressHistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Lấy tiến độ theo user + grade
     */
    public List<ProgressDto> getProgressByUserAndGrade(Long userId, Integer grade) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        List<Progress> list = progressRepository.findByUserAndGrade(user, grade);
        return list.stream().map(this::toDto).toList();
    }

    /**
     * Cập nhật tiến độ (hoặc tạo mới nếu chưa có)
     */
    @Transactional
    public ProgressDto updateProgress(ProgressUpdateRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Progress p = progressRepository
                .findByUserAndGradeAndSubject(user, req.getGrade(), req.getSubject())
                .orElseGet(() -> new Progress(
                        user,
                        req.getGrade(),
                        req.getSubject(),
                        req.getCompletedLessons() == null ? 0 : req.getCompletedLessons(),
                        req.getTotalLessons() == null ? 0 : req.getTotalLessons()
                ));

        // update completed/total an toàn
        if (req.getTotalLessons() != null && req.getTotalLessons() >= 0) {
            p.setTotalLessons(req.getTotalLessons());
        }
        if (req.getCompletedLessons() != null && req.getCompletedLessons() >= 0) {
            int completed = req.getCompletedLessons();
            int total = p.getTotalLessons() != null ? p.getTotalLessons() : 0;
            if (total > 0 && completed > total) {
                completed = total; // tránh completed > total
            }
            p.setCompletedLessons(completed);
        }

        p.updateProgressPercent();
        p = progressRepository.save(p);

        // Lưu lịch sử
        ProgressHistory history = new ProgressHistory(
                user,
                req.getSubject(),
                req.getGrade(),
                p.getProgressPercent()
        );
        historyRepository.save(history);

        return toDto(p);
    }

    /**
     * Lấy lịch sử tiến độ theo khoảng thời gian
     */
    public List<ProgressHistoryDto> getHistory(Long userId, String subject, String range) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;

        switch (range.toLowerCase()) {
            case "day" -> start = now.toLocalDate().atStartOfDay();
            case "week" -> start = now.toLocalDate().with(DayOfWeek.MONDAY).atStartOfDay();
            case "month" -> start = LocalDate.of(now.getYear(), now.getMonth(), 1).atStartOfDay();
            default -> start = now.minusMonths(1);
        }

        List<ProgressHistory> rows = historyRepository
                .findByUserAndSubjectAndRecordedAtBetween(user, subject, start, now);

        return rows.stream()
                .map(h -> new ProgressHistoryDto(
                        user.getId(),
                        h.getSubject(),
                        h.getGrade(),
                        h.getProgressPercent(),
                        h.getRecordedAt()
                ))
                .toList();
    }

    /**
     * Lấy tất cả tiến độ của user
     */
    public List<ProgressDto> getProgressByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return progressRepository.findByUser(user).stream().map(this::toDto).toList();
    }

    /**
     * Convert Progress entity -> DTO
     */
    private ProgressDto toDto(Progress p) {
        return new ProgressDto(
                p.getSubject(),
                p.getGrade(),
                p.getCompletedLessons(),
                p.getTotalLessons(),
                p.getProgressPercent(),
                p.getUpdatedAt()
        );
    }
}
