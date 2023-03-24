package com.mvsc.hotel.controller;

import com.mvsc.hotel.entity.Hotel;
import com.mvsc.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        var hotel = hotelService.getHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHoteles(){
        var hoteles = hotelService.getHoteles();
        return ResponseEntity.ok(hoteles);
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotelRequest){
        var hotel = hotelService.saveHotel(hotelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }


}
