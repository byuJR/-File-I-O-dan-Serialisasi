package Praktikum12_2;

import java.io.*;

/**
 * This class represents a product that can be serialized.
 */
class Produk1 implements Serializable {
    private String namaProduk;
    private double harga;
    private int stok;

    public Produk1(String namaProduk, double harga, int stok) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Produk: " + namaProduk);
        System.out.println("Harga: " + harga);
        System.out.println("Stok: " + stok);
    }
}
