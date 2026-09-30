package domain.repository;

import domain.entity.Item;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data barang.
 */
public interface IItemRepository {
    /** Mengambil semua barang dari penyimpanan. */
    List<Item> findAll();

    /** Mencari satu barang berdasarkan ID. */
    Optional<Item> findById(int id);

    /**
     * Menyimpan barang baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @return barang yang tersimpan (lengkap dengan ID)
     */
    Item save(String name, int quantity, String category);

    /** Menghapus barang berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);

    /** Menyimpan perubahan pada barang yang sudah ada. */
    void update(Item item);
}
