package com.mvsc.hotel.service;

import com.mvsc.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel getHotel(String id);
    List<Hotel> getHoteles();
    Hotel saveHotel(Hotel hotel);
}
