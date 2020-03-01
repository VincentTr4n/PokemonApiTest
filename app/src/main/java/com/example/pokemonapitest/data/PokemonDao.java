package com.example.pokemonapitest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDao {

    @Query("select * from pokemon_table")
    LiveData<List<Pokemon>> getListPokemons();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon ... pokemons);

    @Query("delete from pokemon_table")
    void deleteAll();
}
