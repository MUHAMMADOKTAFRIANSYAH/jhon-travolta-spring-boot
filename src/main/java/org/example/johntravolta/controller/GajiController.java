package org.example.johntravolta.controller;

import org.example.johntravolta.service.GajiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GajiController {

    private final GajiService gajiService;

    public GajiController(GajiService gajiService) {
        this.gajiService = gajiService;
    }

    // Menampilkan halaman form
    @GetMapping("/")
    public String halamanUtama() {
        return "index";
    }

    // Memproses perhitungan gaji
    @PostMapping("/hitung")
    public String hitungGaji(
            @RequestParam double jamKerja,
            @RequestParam double jamNormal,
            @RequestParam double rate,
            @RequestParam double pengaliLembur,
            @RequestParam double pengeluaran,
            Model model) {

        // Menghitung pemasukan
        double pemasukan = gajiService.hitungPemasukan(
                jamKerja,
                jamNormal,
                rate,
                pengaliLembur
        );

        // Menghitung tabungan
        double tabungan = gajiService.hitungTabungan(
                pemasukan,
                pengeluaran
        );

        // Menentukan kondisi
        String kondisi = gajiService.tentukanKondisi(
                pemasukan,
                pengeluaran
        );

        // Mengirim hasil ke halaman
        model.addAttribute("jamKerja", jamKerja);
        model.addAttribute("pemasukan", pemasukan);
        model.addAttribute("pengeluaran", pengeluaran);
        model.addAttribute("tabungan", tabungan);
        model.addAttribute("kondisi", kondisi);

        return "hasil";
    }
}