package com.example.pokemonapitest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemonapitest.AppConstants;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {
    private static volatile PokemonDatabase instance;
    private static final Object sLock = new Object();
    public abstract PokemonDao pokemonDao();

    public static PokemonDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (sLock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PokemonDatabase.class,
                            AppConstants.DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;

    }
}
