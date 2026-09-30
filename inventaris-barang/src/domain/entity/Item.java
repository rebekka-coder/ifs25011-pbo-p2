package domain.entity;

/**
 * Entity inti yang merepresentasikan satu barang di inventaris.
 * Bersifat immutable: perubahan data dilakukan dengan membuat salinan baru
 * lewat method {@code withXxx}, sehingga instance aman dibagikan antar-layer.
 */
public class Item {
    /** ID unik barang. */
    private final int id;

    /** Nama barang. */
    private final String name;

    /** Jumlah stok (selalu lebih dari 0). */
    private final int quantity;

    /** Kategori barang. */
    private final String category;

    /**
     * @throws IllegalArgumentException jika jumlah stok tidak lebih dari 0
     */
    public Item(int id, String name, int quantity, String category) {
        requireValidQuantity(quantity);
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    /** Aturan domain: jumlah stok harus lebih dari 0. */
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    /**
     * Memastikan jumlah stok valid.
     *
     * @throws IllegalArgumentException jika jumlah stok tidak lebih dari 0
     */
    public static void requireValidQuantity(int quantity) {
        if (!isValidQuantity(quantity)) {
            throw new IllegalArgumentException("Jumlah stok harus lebih dari 0");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    /** Mengembalikan salinan barang ini dengan jumlah stok yang baru. */
    public Item withQuantity(int quantity) {
        return new Item(id, name, quantity, category);
    }
}
