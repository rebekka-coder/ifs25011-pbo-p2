package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 */
public class ActivityRepository implements IActivityRepository {
    /** Penyimpanan data kegiatan di memori. */
    private final List<Activity> data = new ArrayList<>();

    /** Penghitung ID otomatis. */
    private int idCounter = 0;

    @Override
    public List<Activity> findAll() {
        // Salinan list yang tidak bisa dimodifikasi; Activity sendiri immutable
        return List.copyOf(data);
    }

    @Override
    public Optional<Activity> findById(int id) {
        return data.stream()
                .filter(activity -> activity.getId() == id)
                .findFirst();
    }

    @Override
    public Activity save(String title, String day, String time) {
        Activity activity = new Activity(nextId(), title, day, time);
        data.add(activity);
        return activity;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(activity -> activity.getId() == id);
    }

    @Override
    public boolean update(Activity activity) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == activity.getId()) {
                data.set(i, activity);
                return true;
            }
        }
        return false;
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
