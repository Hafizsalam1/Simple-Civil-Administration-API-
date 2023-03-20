package org.example.Service;

import org.example.Exception.NotFoundException;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;
import org.example.Repo.RepoKelurahan;
import org.example.Repo.RepoKependudukan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceKelurahan implements IService<Kelurahan> {

    @Autowired
    RepoKelurahan repoKelurahan;

    @Autowired
    RepoKependudukan repoKependudukan;


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
                throw new NotFoundException("Tidak ada kelurahan");
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
                throw new NotFoundException("Tidak ada kelurahan");
            }
            return kelurahan;

        }
        catch (Exception e){throw new RuntimeException(e);}
    }

    public Set<Kependudukan> saveAll(List<Kependudukan> kependudukans, Long id) throws Exception{
        try{
            Set<Kependudukan>kependudukans1 = new HashSet<>();
            for (Kependudukan kep :kependudukans) {
                kep.setKelurahan(repoKelurahan.findById(id).get());
                kep.setKecamatan(kep.getKelurahan().getKecamatan());
                repoKependudukan.save(kep);
                kependudukans1.add(kep);
            }
            return kependudukans1;

        }
        catch (Exception e){
            throw new RuntimeException(e);

        }

    }


}
