package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.List;

public class FinanceUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final ITransactionRepository transactionRepository;

    public FinanceUseCase(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /** Mengambil semua transaksi. */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /** Memeriksa apakah jumlah memenuhi aturan domain (lebih dari 0). */
    public boolean isValidAmount(long amount) {
        return Transaction.isValidAmount(amount);
    }

    /**
     * Menambahkan transaksi baru dan mengembalikan entity yang tersimpan.
     *
     * @throws IllegalArgumentException jika jumlah tidak lebih dari 0
     */
    public Transaction addTransaction(String description, long amount, TransactionType type) {
        Transaction.requireValidAmount(amount);
        return transactionRepository.save(description, amount, type);
    }

    /** Menghapus transaksi berdasarkan ID. */
    public boolean removeTransaction(int id) {
        return transactionRepository.deleteById(id);
    }

    /** Mencari transaksi yang keterangannya mengandung kata kunci (case-insensitive). */
    public List<Transaction> searchTransactions(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return transactionRepository.findAll().stream()
                .filter(t -> t.getDescription().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan transaksi sesuai kriteria {@link SortOption}. */
    public List<Transaction> sortTransactions(SortOption option) {
        return transactionRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }

    /** Menghitung saldo: total pemasukan dikurangi total pengeluaran. */
    public long getBalance() {
        long balance = 0;
        for (Transaction t : transactionRepository.findAll()) {
            balance += (t.getType() == TransactionType.INCOME) ? t.getAmount() : -t.getAmount();
        }
        return balance;
    }
}
