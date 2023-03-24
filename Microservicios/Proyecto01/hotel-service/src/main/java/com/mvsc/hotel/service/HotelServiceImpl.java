package com.mvsc.hotel.service;

import com.mvsc.hotel.entity.Hotel;
import com.mvsc.hotel.exceptions.ResourceNotFountException;
import com.mvsc.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFountException("No existe un hotel con el id: " + id));
    }

    @Override
    public List<Hotel> getHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        var hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }
}
