package com.salesianostriana.dam.resteval;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceRepository placeRepository;

    // Listar todos los bares o restaurantes
    @GetMapping
    public ResponseEntity<List<Place>> getAll() {
        List<Place> places = placeRepository.getAll();
        if (places.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        //return ResponseEntity.ok(places);
        //ResponseEntity.status(200).build();
        return ResponseEntity.status(200).body(places);
    }

    //Buscar un bar o restaurante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Place> findById(@PathVariable Long id) {
        if(placeRepository.get(id).isPresent()) {
            //return ResponseEntity.ok(placeRepository.get(id).get());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    //Crear un nuevo bar o restaurante
    @PostMapping
    public ResponseEntity<Place> add(@RequestBody Place place) {
        //return ResponseEntity.ok(placeRepository.add(place));
        return ResponseEntity.status(201).build();
    }

    //Modificar un bar o restaurante
    @PutMapping("/{id}")
    public ResponseEntity<Place> edit(@RequestBody Place place, @PathVariable Long id) {
        if(placeRepository.get(id).isPresent()) {
            placeRepository.edit(id, place);
            //return ResponseEntity.ok(placeRepository.get(id).get());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    //Agregar un tag a un restaurante
    @PutMapping("/{id}/tag/add/{nuevo_tag}")
    public ResponseEntity<Place> addTag(@PathVariable Long id, @PathVariable String nuevo_tag) {
        if(placeRepository.get(id).isPresent()) {
            placeRepository.get(id).get().addTag(nuevo_tag);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    //Eliminar un tag
    @PutMapping("/{id}/tag/del/{tag}")
    public ResponseEntity<Place> deleteTag(@PathVariable Long id, @PathVariable String tag) {
        if(placeRepository.get(id).isPresent()) {
            placeRepository.get(id).get().removeTag(tag);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    //Eliminar un bar o restaurante
    @DeleteMapping("/{id}")
    public ResponseEntity<Place> delete(@PathVariable Long id) {
        placeRepository.delete(id);
        return ResponseEntity.status(204).build();
    }

}
