package org.example.Repo;

import org.example.Model.Kecamatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoKecamatan extends PagingAndSortingRepository<Kecamatan,Long>, CrudRepository<Kecamatan, Long> {


    public Page<Kecamatan> findAll(Pageable pageable);

    public Page<Kecamatan> findByNamaContains(String nama, Pageable pageable);





}
