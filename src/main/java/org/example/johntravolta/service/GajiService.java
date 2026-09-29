package org.example.johntravolta.service;

import org.springframework.stereotype.Service;

@Service
public class GajiService {

    // Menghitung pemasukan mingguan
    public double hitungPemasukan(
            double jamKerja,
            double jamNormal,
            double rate,
            double pengaliLembur) {

        if (jamKerja <= jamNormal) {
            return jamKerja * rate;
        }

        double jamLembur = jamKerja - jamNormal;

        double pemasukanNormal = jamNormal * rate;

        double rateLembur = rate * pengaliLembur;

        double pemasukanLembur = jamLembur * rateLembur;

        return pemasukanNormal + pemasukanLembur;
    }

    // Menghitung jumlah tabungan
    public double hitungTabungan(
            double pemasukan,
            double pengeluaran) {

        return pemasukan - pengeluaran;
    }

    // Menentukan kondisi keuangan
    public String tentukanKondisi(
            double pemasukan,
            double pengeluaran) {

        if (pemasukan > pengeluaran) {
            return "Bisa menabung";
        } else if (pemasukan == pengeluaran) {
            return "Tidak bisa menabung";
        } else {
            return "Cari tambahan";
        }
    }
}