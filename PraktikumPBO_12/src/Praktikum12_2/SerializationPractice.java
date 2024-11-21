package Praktikum12_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class demonstrates serialization and deserialization of Produk objects.
 */
public class SerializationPractice {
    public static void main(String[] args) {
        Produk1 produk1 = new Produk1("Laptop", 8000000, 10);

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produk.ser"))) {
            oos.writeObject(produk1);
            System.out.println("Objek Produk telah diserialisasi");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("produk.ser"))) {
            Produk1 produk1Deserialisasi = (Produk1) ois.readObject();
            System.out.println("Objek Produk telah dideserialisasi");
            produk1Deserialisasi.tampilkanInfo();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

