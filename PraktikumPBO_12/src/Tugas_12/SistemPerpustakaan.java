package Tugas_12;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemPerpustakaan {
    private static final String TEXT_FILE = "buku.txt";
    private static final String SERIAL_FILE = "buku.ser";
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();
            int pilihan = ambilPilihanPengguna();
            switch (pilihan) {
                case 1 -> tambahBuku();
                case 2 -> simpanKeFileTeks();
                case 3 -> simpanKeFileSerial();
                case 4 -> tampilkanBuku();
                case 5 -> berjalan = false;
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu to the user.
     */
    private static void tampilkanMenu() {
        System.out.println("\nMenu Sistem Perpustakaan:");
        System.out.println("1. Tambah Buku Baru dan Simpan ke File Teks");
        System.out.println("2. Simpan Buku ke File txt");
        System.out.println("3. Simpan Buku ke File Serial");
        System.out.println("4. Tampilkan Buku dari File");
        System.out.println("5. Keluar");
        System.out.print("Masukkan pilihan Anda: ");
    }

    /**
     * Reads and validates the user's menu choice.
     *
     * @return the user's choice as an integer
     */
    private static int ambilPilihanPengguna() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Input tidak valid. Harap masukkan angka.");
            scanner.nextLine(); // Membersihkan input yang tidak valid
            return -1;
        }
    }

    /**
     * Allows the user to add a new book to the system.
     */
    private static void tambahBuku() {
        System.out.print("Masukkan judul buku: ");
        scanner.nextLine(); // Membersihkan newline
        String judul = scanner.nextLine();
        System.out.print("Masukkan nama pengarang: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan tahun terbit: ");
        int tahun = scanner.nextInt();

        daftarBuku.add(new Buku(judul, pengarang, tahun));
        System.out.println("Buku berhasil ditambahkan.");
    }

    /**
     * Saves the current list of books to a text file.
     */
    private static void simpanKeFileTeks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEXT_FILE))) {
            for (Buku buku : daftarBuku) {
                writer.println(buku.getJudul() + "," + buku.getPengarang() + "," + buku.getTahun());
            }
            System.out.println("Buku berhasil disimpan ke file teks.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan ke file teks: " + e.getMessage());
        }
    }

    /**
     * Serializes the current list of books to a binary file.
     */
    private static void simpanKeFileSerial() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(daftarBuku);
            System.out.println("Buku berhasil diserialisasi ke file.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat serialisasi buku: " + e.getMessage());
        }
    }

    /**
     * Displays books from both the text and serialized files.
     */
    private static void tampilkanBuku() {
        System.out.println("\nBuku dari File Teks:");
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split(",");
                System.out.printf("Judul: %s, Pengarang: %s, Tahun: %s%n", data[0], data[1], data[2]);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file teks: " + e.getMessage());
        }

        System.out.println("\nBuku dari File Serial:");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {
            ArrayList<Buku> bukuDeserialisasi = (ArrayList<Buku>) ois.readObject();
            for (Buku buku : bukuDeserialisasi) {
                System.out.printf("Judul: %s, Pengarang: %s, Tahun: %d%n", buku.getJudul(), buku.getPengarang(), buku.getTahun());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Terjadi kesalahan saat membaca file serial: " + e.getMessage());
        }
    }
}


