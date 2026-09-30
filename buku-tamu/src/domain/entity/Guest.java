package domain.entity;

/**
 * Entity inti yang merepresentasikan satu tamu.
 * Bersifat immutable: seluruh field final, sehingga instance aman dibagikan
 * antar-layer tanpa risiko dimutasi dari luar.
 */
public class Guest {
    /** ID unik tamu. */
    private final int id;

    /** Nama tamu. */
    private final String name;

    /** Tujuan kunjungan. */
    private final String purpose;

    public Guest(int id, String name, String purpose) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }
}
