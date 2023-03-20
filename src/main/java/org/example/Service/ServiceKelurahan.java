package org.example.Service;

import org.example.Exception.NotFoundException;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;
import org.example.Repo.RepoKelurahan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceKelurahan implements IService<Kelurahan> {

    @Autowired
    RepoKelurahan repoKelurahan;


    @Override
    public Kelurahan save(Kelurahan kelurahan) throws Exception {
        try {
            return repoKelurahan.save(kelurahan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Iterable<Kelurahan> findAll(Pageable pageable) throws Exception {
        try {
            Iterable<Kelurahan> kelurahan = repoKelurahan.findAll((org.springframework.data.domain.Pageable) pageable);
            List<Kelurahan> kelurahans = new ArrayList<Kelurahan>();
            for (Kelurahan kel : kelurahan) {
                kelurahans.add(kel);
            }

            if (kelurahans.isEmpty()) {
                throw new NotFoundException("Tidak ada kecamatan");
            }
            return kelurahan;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Kelurahan> findById(Long id) throws Exception {
        try{Optional<Kelurahan> kelurahan = repoKelurahan.findById(id);
            if (kelurahan.isEmpty()) {
                throw new NotFoundException("kelurahan tidak ada");
            }
            return kelurahan;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Kelurahan update(Kelurahan kelurahan, Long id) throws Exception {
        try{
            Optional<Kelurahan>kelurahans = repoKelurahan.findById(id);
            if(kelurahans.isEmpty()){
                throw new NotFoundException("Kelurahan tidak ditemukan");
            }
            kelurahan.setId(id);
            return repoKelurahan.save(kelurahan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<Kelurahan>kelurahan = repoKelurahan.findById(id);
            if(kelurahan.isEmpty()){
                throw new NotFoundException("Kelurahan tidak ditemukan");
            }
            repoKelurahan.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Iterable<Kelurahan> findByNamaContains(String namaLengkap, org.springframework.data.domain.Pageable pageable){

        try{
            Iterable <Kelurahan> kelurahan =  repoKelurahan.findByNamaContains(namaLengkap, pageable);
            List<Kelurahan> kelurahans = new ArrayList<Kelurahan>();
            for (Kelurahan kel : kelurahan) {
                kelurahans.add(kel);
            }

            if (kelurahans.isEmpty()) {
                throw new NotFoundException("Tidak ada kecamatan");
            }
            return kelurahan;

        }
        catch (Exception e){throw new RuntimeException(e);}
    }

}
