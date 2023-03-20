package org.example.Model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.example.Model.Kecamatan;
import org.example.Model.Kependudukan;

import java.util.Set;

public class KelurahanDTO {
    private Long id;

    private String Nama;

    private Kecamatan kecamatan;

    private Set<Kependudukan> penduduk;

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

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Set<Kependudukan> getPenduduk() {
        return penduduk;
    }

    public void setPenduduk(Set<Kependudukan> penduduk) {
        this.penduduk = penduduk;
    }
}
