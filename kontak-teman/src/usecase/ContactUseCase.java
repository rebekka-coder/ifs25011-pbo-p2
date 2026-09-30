package usecase;

import domain.entity.Contact;
import domain.entity.SortOption;
import domain.repository.IContactRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis kontak teman.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil.
 */
public class ContactUseCase {
    private final IContactRepository contactRepository;

    public ContactUseCase(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /** Mengambil semua kontak. */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /** Menambahkan kontak baru dan mengembalikan entity yang tersimpan. */
    public Contact addContact(String name, String phone, String email) {
        return contactRepository.save(name, phone, email);
    }

    /** Menghapus kontak berdasarkan ID. */
    public boolean removeContact(int id) {
        return contactRepository.deleteById(id);
    }

    /**
     * Mengubah nama, telepon, dan/atau email kontak dengan mengganti entity lama
     * dengan salinan baru. Parameter {@code null} berarti field tersebut tidak diubah.
     *
     * @return true jika kontak ditemukan dan diperbarui
     */
    public boolean updateContact(int id, String name, String phone, String email) {
        Optional<Contact> found = contactRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Contact updated = found.get();

        if (name != null) {
            updated = updated.withName(name);
        }
        if (phone != null) {
            updated = updated.withPhone(phone);
        }
        if (email != null) {
            updated = updated.withEmail(email);
        }

        return contactRepository.update(updated);
    }

    /** Mencari kontak yang namanya mengandung kata kunci (case-insensitive). */
    public List<Contact> searchContacts(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return contactRepository.findAll().stream()
                .filter(contact -> contact.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan kontak sesuai kriteria {@link SortOption}. */
    public List<Contact> sortContacts(SortOption option) {
        return contactRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
