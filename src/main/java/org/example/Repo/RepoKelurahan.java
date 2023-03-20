package org.example.Repo;

import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RepoKelurahan extends PagingAndSortingRepository<Kelurahan, Long>, CrudRepository<Kelurahan, Long> {
    public Page<Kelurahan> findAll(Pageable pageable);
    Page<Kelurahan> findByNamaContains(String nama, Pageable pageable);

//    public Set<Kependudukan> addKependudukan(Set<Kependudukan> kependudukans, Long id) throws Exception;



}
