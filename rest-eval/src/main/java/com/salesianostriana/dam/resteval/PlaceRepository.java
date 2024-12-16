package com.salesianostriana.dam.resteval;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PlaceRepository {

    private HashMap<Long, Place> places = new HashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @PostConstruct
    public void init() {
        // Añadir aquí datos de ejemplo
        List<String> tags = new ArrayList<String>();
        tags.add("TAG 1");
        tags.add("TAG 2");
        tags.add("TAG 3");

        add(Place.builder()
                .id(1L)
                .name("Cafetería Salesianos Triana")
                .address("Condes de Bustillo")
                .coords("123456, -654321")
                .desc("Allí desayunan los que nos suspenden")
                .tags(tags)
                .image("Foto")
                .build()
        );

        add(Place.builder()
                .id(2L)
                .name("Bar Paletas")
                .address("Condes de Bustillo")
                .coords("98765, -56789")
                .desc("El bar más guarro de todo Triana")
                .tags(tags)
                .image("Foto")
                .build()
        );
    }

    public Place add(Place place) {
        var id = counter.incrementAndGet();
        place.setId(id);
        places.put(id, place);
        return place;
    }

    public Optional<Place> get(Long id) {
        return Optional.ofNullable(places.get(id));
    }

    public List<Place> getAll() {
        return List.copyOf(places.values());
    }

    public Optional<Place> edit(Long id, Place place) {
        return Optional.ofNullable(places.computeIfPresent(id, (k,v) -> {
            v.setName(place.getName());
            v.setDesc(place.getDesc());
            v.setImage(place.getImage());
            v.setCoords(place.getCoords());
            v.setAddress(place.getAddress());
            return v;
        }));
    }

    public void delete(Long id) {
        places.remove(id);
    }


}
