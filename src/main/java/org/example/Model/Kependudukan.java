package org.example.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "kependudukan")
public class Kependudukan implements Serializable {


    @Id
    @Column(length = 100)
    private Long NIK;

    @Column(length = 100)
    private String NamaLengkap;

    @Column(length = 100)
    private String TempatLahir;

    @Column(length = 100)
    @Temporal(TemporalType.DATE)
    private Date TanggalLahir;

    @ManyToOne
    @JsonBackReference(value="penduduk-kecamatan")
    private Kecamatan kecamatan;

    @ManyToOne
    @JsonBackReference(value="penduduk-kelurahan")
    private Kelurahan kelurahan;

    public Kependudukan() {
    }

    public Kependudukan(Long id, Long NIK, String namaLengkap, String tempatLahir, Date tanggalLahir, Kecamatan kecamatan, Kelurahan kelurahan) {
        this.NIK = NIK;
        NamaLengkap = namaLengkap;
        TempatLahir = tempatLahir;
        TanggalLahir = tanggalLahir;
    }


    public Long getNIK() {
        return NIK;
    }

    public void setNIK(Long NIK) {
        this.NIK = NIK;
    }

    public String getNamaLengkap() {
        return NamaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        NamaLengkap = namaLengkap;
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
