package com.CordyTech.Buque.controller;
import com.CordyTech.Buque.model.Buque;
import com.CordyTech.Buque.service.BuqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buques")
@CrossOrigin(origins = "*") // si estás consumiendo desde un frontend local
public class BuqueController {

    @Autowired
    private BuqueService buqueService;

    @PostMapping
    public ResponseEntity<Buque> createBuque(@RequestBody Buque buque) {
        Buque nuevoBuque = buqueService.saveBuque(buque);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBuque);
    }

    @GetMapping
    public List<Buque> getAllBuques() {
        return buqueService.getAllBuques();
    }

    @GetMapping("/{codBuque}")
    public Optional<Buque> getBuqueById(@PathVariable String codBuque) {
        return buqueService.getBuqueById(codBuque);
    }

    @DeleteMapping("/{codBuque}")
    public void deleteBuque(@PathVariable String codBuque) {
        buqueService.deleteBuque(codBuque);
    }
}

