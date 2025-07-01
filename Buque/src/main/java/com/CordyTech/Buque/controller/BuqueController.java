package com.CordyTech.Buque.controller;
import com.CordyTech.Buque.model.Buque;
import com.CordyTech.Buque.service.BuqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/buques")
@CrossOrigin(origins = "*") // si estás consumiendo desde un frontend local
public class BuqueController {

    @Autowired
    private BuqueService buqueService;

    @PostMapping
    public ResponseEntity<EntityModel<Buque>> createBuque(@RequestBody Buque buque) {
        Buque nuevo = buqueService.saveBuque(buque);

        EntityModel<Buque> recurso = EntityModel.of(nuevo,
                linkTo(methodOn(BuqueController.class).getBuqueById(nuevo.getCodBuque())).withSelfRel(),
                linkTo(methodOn(BuqueController.class).getAllBuques()).withRel("todos"));

        return ResponseEntity
                .created(linkTo(methodOn(BuqueController.class).getBuqueById(nuevo.getCodBuque())).toUri())
                .body(recurso);
    }

    @GetMapping
    public CollectionModel<EntityModel<Buque>> getAllBuques() {
        List<EntityModel<Buque>> buques = buqueService.getAllBuques().stream()
                .map(buque -> EntityModel.of(buque,
                        linkTo(methodOn(BuqueController.class).getBuqueById(buque.getCodBuque())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(buques,
                linkTo(methodOn(BuqueController.class).getAllBuques()).withSelfRel());
    }

    @GetMapping("/{codBuque}")
    public ResponseEntity<EntityModel<Buque>> getBuqueById(@PathVariable String codBuque) {
        return buqueService.getBuqueById(codBuque)
                .map(buque -> {
                    EntityModel<Buque> recurso = EntityModel.of(buque,
                            linkTo(methodOn(BuqueController.class).getBuqueById(codBuque)).withSelfRel(),
                            linkTo(methodOn(BuqueController.class).getAllBuques()).withRel("todos"));

                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codBuque}")
    public void deleteBuque(@PathVariable String codBuque) {
        buqueService.deleteBuque(codBuque);
    }
}

