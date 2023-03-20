package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.DTO.KecamatanDTO;
import org.example.Model.DTO.KelurahanDTO;
import org.example.Model.Kecamatan;
import org.example.Model.Kelurahan;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.ServiceKelurahan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kelurahan")
public class ControllerKelurahan {

    @Autowired
    ServiceKelurahan serviceKelurahan;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{size}/{page}/{sort}")
    public ResponseEntity getAllKelurahan(@PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Iterable<Kelurahan> kelurahans = serviceKelurahan.findAll(pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kelurahan>>("Get All Succeed", kelurahans));
    }

    @PostMapping
    public ResponseEntity createKelurahan(@Valid @RequestBody KelurahanDTO kelurahanDTO) throws Exception {
        Kelurahan kelurahan  = modelMapper.map(kelurahanDTO, Kelurahan.class);
        Kelurahan kelurahan1 = serviceKelurahan.save(kelurahan);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kelurahan>("Add Kelurahan Succeed", kelurahan));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Kelurahan> kelurahan = serviceKelurahan.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kelurahan>>("Get by id Succeed", kelurahan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        Optional<Kelurahan> kelurahan = serviceKelurahan.findById(id);
        serviceKelurahan.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kelurahan>>("Delete Succeed",kelurahan));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid@RequestBody KelurahanDTO kelurahanDTO, @PathVariable Long id) throws Exception {
        Kelurahan kelurahan = modelMapper.map(kelurahanDTO, Kelurahan.class);
        kelurahan.setId(id);
        serviceKelurahan.update(kelurahan, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kelurahan>("Update Succeed",kelurahan));
    }

    @PostMapping("/search/nama/{size}/{page}/{sort}")
    public ResponseEntity findByNamaContains(@RequestBody KelurahanDTO kelurahanDTO, @PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Kelurahan kelurahan = modelMapper.map(kelurahanDTO, Kelurahan.class);
        Iterable<Kelurahan> kelurahans =  serviceKelurahan.findByNamaContains(kelurahan.getNama(),pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kelurahan>>("Find Kelurahan succeed", kelurahans));
    }




}
