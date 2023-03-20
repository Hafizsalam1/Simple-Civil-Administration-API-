package org.example.Model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;

import java.util.Date;

public class KependudukanDTO {

    @NotBlank(message="{invalid.data.NotFound}")
    @Pattern(regexp="[0-9]+", message = "{invalid.NIK.ErrorDataType}")
    @Size(max = 16, min = 16, message = "{invalid.NIK.NOT16Digit}")
    private String NIK;
    private String nama;
    private String TempatLahir;

    @Past(message="{invalid.Date.Future}")
    private Date TanggalLahir;
    private Kecamatan kecamatan;
    private Kelurahan kelurahan;
    public String getNIK() {
        return NIK;
    }
    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return TempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        TempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return TanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        TanggalLahir = tanggalLahir;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Kelurahan getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(Kelurahan kelurahan) {
        this.kelurahan = kelurahan;
    }
}
