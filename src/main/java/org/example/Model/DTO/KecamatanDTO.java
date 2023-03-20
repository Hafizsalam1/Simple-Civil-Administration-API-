package org.example.Model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;

import java.util.Date;
import java.util.Set;

public class KecamatanDTO {

    private Long id;

    private String Nama;

//    private Set<Kependudukan> penduduk;

    private Set<Kelurahan> kelurahan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

//    public Set<Kependudukan> getPenduduk() {
//        return penduduk;
//    }
//
//    public void setPenduduk(Set<Kependudukan> penduduk) {
//        this.penduduk = penduduk;
//    }

    public Set<Kelurahan> getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(Set<Kelurahan> kelurahan) {
        this.kelurahan = kelurahan;
    }
}
