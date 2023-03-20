package org.example.Service;

import org.example.Model.Kependudukan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService<T> {

    public T save(T params) throws Exception;

    public Iterable<T> findAll(Pageable pageable) throws Exception;

    public Optional<T> findById(Long params) throws Exception;

    public T update(T params, Long id) throws Exception;

    public void deleteById(Long params) throws Exception;









}
