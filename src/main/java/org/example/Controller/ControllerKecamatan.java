package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.DTO.KecamatanDTO;
import org.example.Model.DTO.KependudukanDTO;
import org.example.Model.Kecamatan;
import org.example.Model.Kependudukan;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.ServiceKecamatan;
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
@RequestMapping("/kecamatan")
public class ControllerKecamatan {

    @Autowired
    ServiceKecamatan serviceKecamatan;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{size}/{page}/{sort}")
    public ResponseEntity getAllKecamatan(@PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Iterable<Kecamatan> kecamatan = serviceKecamatan.findAll(pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kecamatan>>("Get All Succeed", kecamatan));
    }

    @PostMapping
    public ResponseEntity createKecamatan(@Valid @RequestBody KecamatanDTO kecamatanDTO) throws Exception {
        Kecamatan kecamatan  = modelMapper.map(kecamatanDTO, Kecamatan.class);
        Kecamatan kecamatan1 = serviceKecamatan.save(kecamatan);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kecamatan>("Add Kecamatan Succeed", kecamatan));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Kecamatan> kecamatan = serviceKecamatan.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kecamatan>>("Get by id Succeed", kecamatan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        Optional<Kecamatan> kecamatan = serviceKecamatan.findById(id);
        serviceKecamatan.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kecamatan>>("Delete Succeed",kecamatan));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid@RequestBody KecamatanDTO kecamatanDTO, @PathVariable Long id) throws Exception {
        Kecamatan kecamatan = modelMapper.map(kecamatanDTO, Kecamatan.class);
        serviceKecamatan.update(kecamatan, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kecamatan>("Update Succeed",kecamatan));
    }

    @PostMapping("/search/nama/{size}/{page}/{sort}")
    public ResponseEntity findByNamaContains(@RequestBody KecamatanDTO kecamatanDTO, @PathVariable int page, @PathVariable int size, @PathVariable String sort) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Kecamatan kecamatan = modelMapper.map(kecamatanDTO, Kecamatan.class);
        Iterable<Kecamatan> kecamatans =  serviceKecamatan.findByNamaContains(kecamatan.getNama(),pageable);
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kecamatan>>("Find Kecamatan succeed", kecamatans));
    }




}
