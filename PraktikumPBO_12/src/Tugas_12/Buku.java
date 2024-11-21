package Tugas_12;

import java.io.Serializable; // Import statement for Serializable

public class Buku implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures serialization compatibility
    private final String judul;
    private final String pengarang;
    private final int tahun;

    public Buku(String judul, String pengarang, int tahun) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahun = tahun;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public int getTahun() {
        return tahun;
    }
}

