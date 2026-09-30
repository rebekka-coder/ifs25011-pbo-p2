package domain.repository;

import domain.entity.Activity;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data kegiatan.
 */
public interface IActivityRepository {
    /** Mengambil semua kegiatan dari penyimpanan. */
    List<Activity> findAll();

    /** Mencari satu kegiatan berdasarkan ID. */
    Optional<Activity> findById(int id);

    /**
     * Menyimpan kegiatan baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @return kegiatan yang tersimpan (lengkap dengan ID)
     */
    Activity save(String title, String day, String time);

    /** Menghapus kegiatan berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);

    /**
     * Mengganti data yang tersimpan dengan versi baru yang ber-ID sama.
     * Entity bersifat immutable, sehingga perubahan dilakukan dengan menimpa instance lama.
     *
     * @return true jika data dengan ID tersebut ditemukan dan diganti
     */
    boolean update(Activity activity);
}
