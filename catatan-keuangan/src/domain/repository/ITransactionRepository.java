package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;
import java.util.Optional;

/**
 * Port (kontrak) penyimpanan data transaksi.
 * Berada di domain agar use case tidak bergantung pada implementasi penyimpanan.
 */
public interface ITransactionRepository {
    /** Mengambil semua transaksi dari penyimpanan. */
    List<Transaction> findAll();

    /** Mencari satu transaksi berdasarkan ID. */
    Optional<Transaction> findById(int id);

    /**
     * Menyimpan transaksi baru. Implementasi bertanggung jawab memberi ID unik.
     *
     * @return transaksi yang tersimpan (lengkap dengan ID)
     */
    Transaction save(String description, long amount, TransactionType type);

    /** Menghapus transaksi berdasarkan ID. Mengembalikan true jika berhasil. */
    boolean deleteById(int id);
}
