package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 */
public class ItemRepository implements IItemRepository {
    /** Penyimpanan data barang di memori. */
    private final List<Item> data = new ArrayList<>();

    /** Penghitung ID otomatis. */
    private int idCounter = 0;

    @Override
    public List<Item> findAll() {
        // Salinan list yang tidak bisa dimodifikasi; Item sendiri immutable
        return List.copyOf(data);
    }

    @Override
    public Optional<Item> findById(int id) {
        return data.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    @Override
    public Item save(String name, int quantity, String category) {
        Item item = new Item(nextId(), name, quantity, category);
        data.add(item);
        return item;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(item -> item.getId() == id);
    }

    @Override
    public boolean update(Item item) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == item.getId()) {
                data.set(i, item);
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
