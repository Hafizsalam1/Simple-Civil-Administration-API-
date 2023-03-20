package org.example.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="kecamatan")
public class Kecamatan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String Nama;

    @OneToMany(mappedBy = "kecamatan")
    private Set<Kependudukan> penduduk;

    @OneToMany(mappedBy = "kecamatan")
    private Set<Kelurahan> kelurahan;

    public Kecamatan() {
    }

    public Kecamatan(Long id, String nama, Set<Kependudukan> penduduk, Set<Kelurahan> kelurahan) {
        this.id = id;
        Nama = nama;
        this.penduduk = penduduk;
        this.kelurahan = kelurahan;
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

    public Set<Kependudukan> getPenduduk() {
        return penduduk;
    }

    public void setPenduduk(Set<Kependudukan> penduduk) {
        this.penduduk = penduduk;
    }

    public Set<Kelurahan> getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(Set<Kelurahan> kelurahan) {
        this.kelurahan = kelurahan;
    }
}
