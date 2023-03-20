package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.DTO.KependudukanDTO;
import org.example.Model.Kependudukan;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.ServiceKependudukan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@Validated
@RequestMapping("/penduduk")
public class ControllerKependudukan {

    @Autowired
    ServiceKependudukan serviceKependudukan;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{size}/{page}/{sort}")
    public ResponseEntity getAllPenduduk(@PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nama").ascending());
        Iterable<Kependudukan> penduduk = serviceKependudukan.findAll(pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("nama").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kependudukan>>("Get All Succeed", penduduk));
    }

    @PostMapping
    public ResponseEntity createPenduduk(@Valid @RequestBody KependudukanDTO kependudukanDTO) throws Exception {
        Kependudukan kependudukan = modelMapper.map(kependudukanDTO, Kependudukan.class);
        Kependudukan kependudukan1 = serviceKependudukan.save(kependudukan);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kependudukan>("Add Penduduk Succeed", kependudukan));
    }

    @GetMapping("/{nik}")
    public ResponseEntity getByNIK(@PathVariable String nik) throws Exception {
        Optional<Kependudukan> kependudukan = serviceKependudukan.findById(nik);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kependudukan>>("Get by NIK Succeed", kependudukan));
    }

    @DeleteMapping("/{nik}")
    public ResponseEntity deleteByNIK(@PathVariable String nik) throws Exception {
        Optional<Kependudukan> kependudukan = serviceKependudukan.findById(nik);
        serviceKependudukan.deleteById(nik);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kependudukan>>("Delete Succeed",kependudukan));
    }

    @PutMapping("/{nik}")
    public ResponseEntity updateByNIK(@Valid@RequestBody KependudukanDTO kependudukanDTO, @PathVariable String nik) throws Exception {
        Kependudukan kependudukan = modelMapper.map(kependudukanDTO, Kependudukan.class);
        kependudukan.setNIK(nik);
        serviceKependudukan.update(kependudukan, nik);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kependudukan>("Update Succeed",kependudukan));
    }

    @PostMapping("/search/nama/{size}/{page}/{sort}")
    public ResponseEntity findByNamaContains(@RequestBody KependudukanDTO kependudukanDTO, @PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("NIK").ascending());
        Kependudukan kependudukan = modelMapper.map(kependudukanDTO, Kependudukan.class);
        Iterable<Kependudukan> kependudukans =  serviceKependudukan.findByNamaContains(kependudukan.getNama(),pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("NIK").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kependudukan>>("Find Penduduk succeed", kependudukans));
    }





}
