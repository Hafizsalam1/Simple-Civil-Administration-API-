package org.example.Service;

import org.example.Exception.NotFoundException;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Repo.RepoKecamatan;
import org.example.Repo.RepoKelurahan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceKecamatan implements IService<Kecamatan> {

    @Autowired
    RepoKecamatan repoKecamatan;


    @Override
    public Kecamatan save(Kecamatan kecamatan) throws Exception {
        try {
            return repoKecamatan.save(kecamatan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Kecamatan> findAll(Pageable pageable) throws Exception {
        try {
            Iterable<Kecamatan> kecamatan = repoKecamatan.findAll((Pageable) pageable);
            List<Kecamatan> kecamatans = new ArrayList<Kecamatan>();
            for (Kecamatan kec : kecamatan) {
                kecamatans.add(kec);
            }

            if (kecamatans.isEmpty()) {
                throw new NotFoundException("Tidak ada kecamatan");
            }
            return kecamatan;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Kecamatan> findById(Long id) throws Exception {
        try{Optional<Kecamatan> kecamatan = repoKecamatan.findById(id);
            if (kecamatan.isEmpty()) {
                throw new NotFoundException("kecamatan tidak ada");
            }
            return kecamatan;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Kecamatan update(Kecamatan kecamatan, Long id) throws Exception {
        try{
            Optional<Kecamatan>kecamatan1 = repoKecamatan.findById(id);
            if(kecamatan1.isEmpty()){
                throw new NotFoundException("Kecamatan tidak ditemukan");
            }
            kecamatan.setId(id);
            return repoKecamatan.save(kecamatan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<Kecamatan>kecamatan = repoKecamatan.findById(id);
            if(kecamatan.isEmpty()){
                throw new NotFoundException("Kecamatan tidak ditemukan");
            }
            repoKecamatan.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Iterable<Kecamatan> findByNamaContains(String namaLengkap, org.springframework.data.domain.Pageable pageable){

        try{
            Iterable <Kecamatan> kecamatan =  repoKecamatan.findByNamaContains(namaLengkap, pageable);
            List<Kecamatan> kecamatans = new ArrayList<Kecamatan>();
            for (Kecamatan kec : kecamatan) {
                kecamatans.add(kec);
            }
            if (kecamatans.isEmpty()) {
                throw new NotFoundException("Tidak ada kecamatan");
            }
            return kecamatan;
        }
        catch (Exception e){throw new RuntimeException(e);}
    }

}
