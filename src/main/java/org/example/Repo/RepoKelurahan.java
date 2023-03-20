package org.example.Repo;

import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoKelurahan extends PagingAndSortingRepository<Kelurahan, Long>, CrudRepository<Kelurahan, Long> {
    public Page<Kelurahan> findAll(Pageable pageable);
    Page<Kelurahan> findByNamaContains(String nama, Pageable pageable);


}
