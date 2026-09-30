package usecase;

import domain.entity.Item;
import domain.entity.SortOption;
import domain.repository.IItemRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis inventaris barang.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil.
 */
public class ItemUseCase {
    private final IItemRepository itemRepository;

    public ItemUseCase(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /** Mengambil semua barang. */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /** Memeriksa apakah jumlah stok memenuhi aturan domain (lebih dari 0). */
    public boolean isValidQuantity(int quantity) {
        return Item.isValidQuantity(quantity);
    }

    /**
     * Menambahkan barang baru dan mengembalikan entity yang tersimpan.
     *
     * @throws IllegalArgumentException jika jumlah stok tidak lebih dari 0
     */
    public Item addItem(String name, int quantity, String category) {
        Item.requireValidQuantity(quantity);
        return itemRepository.save(name, quantity, category);
    }

    /** Menghapus barang berdasarkan ID. */
    public boolean removeItem(int id) {
        return itemRepository.deleteById(id);
    }

    /**
     * Mengubah stok barang dengan mengganti entity lama dengan salinan baru.
     * Parameter {@code null} berarti stok tidak diubah.
     *
     * @return true jika barang ditemukan dan diperbarui
     * @throws IllegalArgumentException jika jumlah stok diberikan tetapi tidak lebih dari 0
     */
    public boolean updateStock(int id, Integer quantity) {
        if (quantity != null) {
            Item.requireValidQuantity(quantity); // validasi dulu sebelum mengakses repository
        }

        Optional<Item> found = itemRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Item item = found.get();
        Item updated = (quantity != null) ? item.withQuantity(quantity) : item;

        return itemRepository.update(updated);
    }

    /** Mencari barang yang namanya mengandung kata kunci (case-insensitive). */
    public List<Item> searchItems(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return itemRepository.findAll().stream()
                .filter(item -> item.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan barang sesuai kriteria {@link SortOption}. */
    public List<Item> sortItems(SortOption option) {
        return itemRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
