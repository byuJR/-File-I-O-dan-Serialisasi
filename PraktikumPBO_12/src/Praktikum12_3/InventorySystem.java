package Praktikum12_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventorySystem {
    private static final String TEXT_FILE = "produk.txt";
    private static final String SERIAL_FILE = "produk.ser";
    private static ArrayList<Produk2> productList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1: addProduct(); break;
                case 2: saveToTextFile(); break;
                case 3: saveToSerialFile(); break;
                case 4: readFromSerialFile(); break;
                case 5: displayProducts(); break;
                case 6: running = false; break;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Simpan ke File Teks");
        System.out.println("3. Simpan ke File Serial");
        System.out.println("4. Baca dari File Serial");
        System.out.println("5. Tampilkan Produk");
        System.out.println("6. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private static int getUserChoice() {
        return scanner.nextInt();
    }

    private static void addProduct() {
        System.out.print("Masukkan nama produk: ");
        String name = scanner.next();
        System.out.print("Masukkan harga: ");
        double price = scanner.nextDouble();
        System.out.print("Masukkan stok: ");
        int stock = scanner.nextInt();

        productList.add(new Produk2(name, price, stock));
        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void saveToTextFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEXT_FILE))) {
            for (Produk2 product : productList) {
                writer.println(product.getNamaProduk() + "," + product.getHarga() + "," + product.getStok());
            }
            System.out.println("Data berhasil disimpan ke file teks.");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan ke file teks: " + e.getMessage());
        }
    }

    private static void saveToSerialFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(productList);
            System.out.println("Data berhasil diserialisasi.");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan ke file serial: " + e.getMessage());
        }
    }

    private static void readFromSerialFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {
            productList = (ArrayList<Produk2>) ois.readObject();
            System.out.println("Data berhasil dibaca dari file serial.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error saat membaca dari file serial: " + e.getMessage());
        }
    }

    private static void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Tidak ada produk untuk ditampilkan.");
        } else {
            for (Produk2 product : productList) {
                product.tampilkanInfo();
            }
        }
    }
}
