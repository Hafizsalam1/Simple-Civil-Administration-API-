package org.example.Service;

import org.example.Exception.NotFoundException;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;
import org.example.Repo.RepoKependudukan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceKependudukan  {

    @Autowired
    RepoKependudukan repoKependudukan;

    public Kependudukan save(Kependudukan kependudukan) throws Exception{
    try {
        if(kependudukan.getKelurahan() != null){
            kependudukan.setKecamatan(kependudukan.getKelurahan().getKecamatan());
        }
        kependudukan.setKecamatan(kependudukan.getKelurahan().getKecamatan());
        return repoKependudukan.save(kependudukan);
    }
    catch (Exception e){
        throw new RuntimeException(e);
    }

}

public Iterable<Kependudukan> findAll(Pageable pageable) throws Exception {
    try {
        Iterable<Kependudukan> penduduk = repoKependudukan.findAll((org.springframework.data.domain.Pageable) pageable);
        List<Kependudukan> kependudukans = new ArrayList<Kependudukan>();
        for (Kependudukan kep : penduduk) {
            kependudukans.add(kep);
        }

        if (kependudukans.isEmpty()) {
            throw new NotFoundException("Tidak ada penduduk");
        }
        return penduduk;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
public Optional<Kependudukan> findById(String nik) throws Exception {
        try{Optional<Kependudukan> penduduk = repoKependudukan.findById(nik);
            if (penduduk.isEmpty()) {
                throw new NotFoundException("penduduk tidak ada");
            }
            return penduduk;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
}

    public Kependudukan update(Kependudukan kependudukan, String nik) throws Exception {
        try{
            Optional<Kependudukan>penduduk = repoKependudukan.findById(nik);
            if(penduduk.isEmpty()){
                throw new NotFoundException("Penduduk tidak ditemukan");
            }
            kependudukan.setNIK(nik);
            return repoKependudukan.save(kependudukan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void deleteById(String nik) throws Exception {
        try{
            Optional<Kependudukan>kependudukan = repoKependudukan.findById(nik);
            if(kependudukan.isEmpty()){
                throw new NotFoundException("Penduduk tidak ditemukan");
            }
            repoKependudukan.deleteById(nik);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Iterable<Kependudukan> findByNamaContains(String namaLengkap, org.springframework.data.domain.Pageable pageable) throws Exception{

        try{
        Iterable<Kependudukan> penduduk = repoKependudukan.findByNamaContains(namaLengkap, pageable);
        if (penduduk==null) {
            throw new NotFoundException("Penduduk tidak ditemukan");
        }
        return penduduk;

    }
        catch (Exception e){throw new RuntimeException(e);}
    }




}
