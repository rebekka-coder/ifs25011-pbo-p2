package adapter.repository;

import domain.entity.Contact;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 */
public class ContactRepository implements IContactRepository {
    /** Penyimpanan data kontak di memori. */
    private final List<Contact> data = new ArrayList<>();

    /** Penghitung ID otomatis. */
    private int idCounter = 0;

    @Override
    public List<Contact> findAll() {
        // Salinan list yang tidak bisa dimodifikasi; Contact sendiri immutable
        return List.copyOf(data);
    }

    @Override
    public Optional<Contact> findById(int id) {
        return data.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();
    }

    @Override
    public Contact save(String name, String phone, String email) {
        Contact contact = new Contact(nextId(), name, phone, email);
        data.add(contact);
        return contact;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(contact -> contact.getId() == id);
    }

    @Override
    public boolean update(Contact contact) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == contact.getId()) {
                data.set(i, contact);
                return true;
            }
        }
        return false;
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
