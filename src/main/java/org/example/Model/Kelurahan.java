package org.example.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "kelurahan")
public class Kelurahan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String Nama;

    @ManyToOne
    @JsonBackReference(value="kecamatan-kelurahan")
    private Kecamatan kecamatan;

    @OneToMany(mappedBy = "kelurahan")
    private Set<Kependudukan> penduduk;

    public Kelurahan() {
    }

    public Kelurahan(Long id, String nama, Kecamatan kecamatan) {
        this.id = id;
        Nama = nama;
        this.kecamatan = kecamatan;
    }

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
