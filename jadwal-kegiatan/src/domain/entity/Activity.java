package domain.entity;

import java.util.List;

/**
 * Entity inti yang merepresentasikan satu kegiatan terjadwal.
 * Bersifat immutable: perubahan data dilakukan dengan membuat salinan baru
 * lewat method {@code withXxx}, sehingga instance aman dibagikan antar-layer.
 */
public class Activity {
    /** Urutan hari dalam sepekan, dipakai untuk pengurutan berdasarkan hari. */
    private static final List<String> DAY_ORDER =
            List.of("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");

    /** ID unik kegiatan. */
    private final int id;

    /** Judul kegiatan. */
    private final String title;

    /** Hari pelaksanaan (mis. Senin). */
    private final String day;

    /** Waktu pelaksanaan (mis. 08:00). */
    private final String time;

    public Activity(int id, String title, String day, String time) {
        this.id = id;
        this.title = title;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    /**
     * Posisi hari dalam sepekan (Senin = 0 ... Minggu = 6).
     * Hari yang tidak dikenal mendapat nilai terbesar sehingga tampil paling akhir.
     */
    public int getDayOrder() {
        int index = DAY_ORDER.indexOf(day.trim().toLowerCase());
        return index >= 0 ? index : DAY_ORDER.size();
    }

    /** Mengembalikan salinan kegiatan ini dengan judul yang baru. */
    public Activity withTitle(String title) {
        return new Activity(id, title, day, time);
    }

    /** Mengembalikan salinan kegiatan ini dengan hari yang baru. */
    public Activity withDay(String day) {
        return new Activity(id, title, day, time);
    }

    /** Mengembalikan salinan kegiatan ini dengan waktu yang baru. */
    public Activity withTime(String time) {
        return new Activity(id, title, day, time);
    }
}
