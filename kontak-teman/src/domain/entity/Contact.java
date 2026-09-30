package domain.entity;

/**
 * Entity inti yang merepresentasikan satu kontak teman.
 * Bersifat immutable: perubahan data dilakukan dengan membuat salinan baru
 * lewat method {@code withXxx}, sehingga instance aman dibagikan antar-layer.
 */
public class Contact {
    /** ID unik kontak. */
    private final int id;

    /** Nama teman. */
    private final String name;

    /** Nomor telepon. */
    private final String phone;

    /** Alamat email. */
    private final String email;

    public Contact(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    /** Mengembalikan salinan kontak ini dengan nama yang baru. */
    public Contact withName(String name) {
        return new Contact(id, name, phone, email);
    }

    /** Mengembalikan salinan kontak ini dengan nomor telepon yang baru. */
    public Contact withPhone(String phone) {
        return new Contact(id, name, phone, email);
    }

    /** Mengembalikan salinan kontak ini dengan email yang baru. */
    public Contact withEmail(String email) {
        return new Contact(id, name, phone, email);
    }
}
