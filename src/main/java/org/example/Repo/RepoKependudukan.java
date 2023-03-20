package org.example.Repo;

import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Kependudukan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoKependudukan extends PagingAndSortingRepository<Kependudukan, String>, CrudRepository<Kependudukan, String> {
    public Page<Kependudukan> findAll(Pageable pageable);
    Page<Kependudukan> findByNamaContains(String namaLengkap, Pageable pageable);




}
