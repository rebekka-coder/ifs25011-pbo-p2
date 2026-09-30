package domain.repository;

import domain.entity.Guest;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data tamu.
 */
public interface IGuestRepository {
    /** Mengambil semua tamu dari penyimpanan. */
    List<Guest> findAll();

    /** Mencari satu tamu berdasarkan ID. */
    Optional<Guest> findById(int id);

    /**
     * Menyimpan tamu baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @return tamu yang tersimpan (lengkap dengan ID)
     */
    Guest save(String name, String purpose);

    /** Menghapus tamu berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);
}
