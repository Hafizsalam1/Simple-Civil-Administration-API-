package org.example.Model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;

import java.util.Date;

public class KependudukanDTO {

    @NotNull(message="{invalid.NIK.NotFound}")
    @Digits(integer = 16, fraction = 0, message="{invalid.NIK.NOT16Digit}")
//    @Pattern(regexp="[^0-9]+", message = "{invalid.NIK.ErrorDataType}")
    private Long NIK;
    private String nama;
    private String TempatLahir;
    private Date TanggalLahir;
    private Kecamatan kecamatan;
    private Kelurahan kelurahan;
    public Long getNIK() {
        return NIK;
    }
    public void setNIK(Long NIK) {
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
