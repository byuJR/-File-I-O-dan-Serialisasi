package Praktikum12_1;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class demonstrates basic file I/O operations in Java.
 * It includes examples of writing to and reading from a text file.
 */
public class FileIOPractic {
    public static void main(String[] args) {
        String filePath = "data.txt";

        // Writing data to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Belajar File I/O di Java!\n");
            writer.write("Pemrograman Berorientasi Objek\n");
            System.out.println("Data berhasil ditulis ke file: " + filePath);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis ke file.");
            e.printStackTrace();
        }

        // Reading data from the file
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            System.out.println("Isi file:");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file.");
            e.printStackTrace();
        }
    }
}
