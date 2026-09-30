package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 */
public class GuestRepository implements IGuestRepository {
    /** Penyimpanan data tamu di memori. */
    private final List<Guest> data = new ArrayList<>();

    /** Penghitung ID otomatis. */
    private int idCounter = 0;

    @Override
    public List<Guest> findAll() {
        // Salinan list yang tidak bisa dimodifikasi; Guest sendiri immutable
        return List.copyOf(data);
    }

    @Override
    public Optional<Guest> findById(int id) {
        return data.stream()
                .filter(guest -> guest.getId() == id)
                .findFirst();
    }

    @Override
    public Guest save(String name, String purpose) {
        Guest guest = new Guest(nextId(), name, purpose);
        data.add(guest);
        return guest;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(guest -> guest.getId() == id);
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
