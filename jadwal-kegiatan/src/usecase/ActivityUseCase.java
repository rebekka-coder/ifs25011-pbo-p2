package usecase;

import domain.entity.Activity;
import domain.entity.SortOption;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis jadwal kegiatan.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil.
 */
public class ActivityUseCase {
    private final IActivityRepository activityRepository;

    public ActivityUseCase(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /** Mengambil semua kegiatan. */
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /** Menambahkan kegiatan baru dan mengembalikan entity yang tersimpan. */
    public Activity addActivity(String title, String day, String time) {
        return activityRepository.save(title, day, time);
    }

    /** Menghapus kegiatan berdasarkan ID. */
    public boolean removeActivity(int id) {
        return activityRepository.deleteById(id);
    }

    /**
     * Mengubah judul, hari, dan/atau waktu kegiatan dengan mengganti entity lama
     * dengan salinan baru. Parameter {@code null} berarti field tersebut tidak diubah.
     *
     * @return true jika kegiatan ditemukan dan diperbarui
     */
    public boolean updateActivity(int id, String title, String day, String time) {
        Optional<Activity> found = activityRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Activity updated = found.get();

        if (title != null) {
            updated = updated.withTitle(title);
        }
        if (day != null) {
            updated = updated.withDay(day);
        }
        if (time != null) {
            updated = updated.withTime(time);
        }

        return activityRepository.update(updated);
    }

    /** Mencari kegiatan yang judulnya mengandung kata kunci (case-insensitive). */
    public List<Activity> searchActivities(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return activityRepository.findAll().stream()
                .filter(activity -> activity.getTitle().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan kegiatan sesuai kriteria {@link SortOption}. */
    public List<Activity> sortActivities(SortOption option) {
        return activityRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
