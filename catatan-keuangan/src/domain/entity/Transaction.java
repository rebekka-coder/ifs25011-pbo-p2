package domain.entity;

/**
 * Entity inti yang merepresentasikan satu transaksi keuangan.
 * Bersifat immutable: seluruh field final, sehingga instance aman dibagikan
 * antar-layer tanpa risiko dimutasi dari luar.
 */
public class Transaction {
    /** ID unik transaksi. */
    private final int id;

    /** Keterangan transaksi. */
    private final String description;

    /** Jumlah uang (selalu lebih dari 0). */
    private final long amount;

    /** Jenis transaksi: pemasukan atau pengeluaran. */
    private final TransactionType type;

    /**
     * @throws IllegalArgumentException jika jumlah tidak lebih dari 0
     */
    public Transaction(int id, String description, long amount, TransactionType type) {
        requireValidAmount(amount);
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    /** Aturan domain: jumlah transaksi harus lebih dari 0. */
    public static boolean isValidAmount(long amount) {
        return amount > 0;
    }

    /**
     * Memastikan jumlah valid.
     *
     * @throws IllegalArgumentException jika jumlah tidak lebih dari 0
     */
    public static void requireValidAmount(long amount) {
        if (!isValidAmount(amount)) {
            throw new IllegalArgumentException("Jumlah harus lebih dari 0");
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
