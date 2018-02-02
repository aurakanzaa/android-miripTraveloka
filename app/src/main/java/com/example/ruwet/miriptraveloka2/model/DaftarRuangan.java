package com.example.ruwet.miriptraveloka2.model;

/**
 * Created by aura on 16/01/18.
 */

public class DaftarRuangan {
    public String nama;
    //public int  foto;
    public int harga;

    public DaftarRuangan(String nama, int harga) {
        this.nama = nama;
        //this.foto = foto;
        this.harga = harga;
    }

    public DaftarRuangan() {
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

//    public int getfoto() {
//        return foto;
//    }
//
//    public void setFoto(int foto) {
//        this.foto = foto;
//    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }


}
