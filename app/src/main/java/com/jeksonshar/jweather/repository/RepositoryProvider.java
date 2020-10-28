package com.jeksonshar.jweather.repository;

import android.content.Context;

import com.jeksonshar.jweather.repository.room.RoomRepository;

public class RepositoryProvider {

    private static Repository instance;

    private RepositoryProvider() {
    }

    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new RoomRepository(context);
        }
        return instance;
    }
}
