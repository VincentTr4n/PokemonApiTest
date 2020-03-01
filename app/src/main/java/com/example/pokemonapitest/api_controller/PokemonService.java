package com.example.pokemonapitest.api_controller;

import com.example.pokemonapitest.api_model.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {

    @GET("pokemon/")
    Call<PokemonListResponse> getListPokemons(@Query("offset") int offset, @Query("limit") int limit);
}
