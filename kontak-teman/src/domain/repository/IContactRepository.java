package domain.repository;

import domain.entity.Contact;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data kontak.
 */
public interface IContactRepository {
    /** Mengambil semua kontak dari penyimpanan. */
    List<Contact> findAll();

    /** Mencari satu kontak berdasarkan ID. */
    Optional<Contact> findById(int id);

    /**
     * Menyimpan kontak baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @return kontak yang tersimpan (lengkap dengan ID)
     */
    Contact save(String name, String phone, String email);

    /** Menghapus kontak berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);

    /**
     * Mengganti data yang tersimpan dengan versi baru yang ber-ID sama.
     * Entity bersifat immutable, sehingga perubahan dilakukan dengan menimpa instance lama.
     *
     * @return true jika data dengan ID tersebut ditemukan dan diganti
     */
    boolean update(Contact contact);
}
