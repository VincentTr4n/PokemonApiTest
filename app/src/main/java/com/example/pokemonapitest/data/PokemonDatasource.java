package com.example.pokemonapitest.data;

import android.app.Application;
import android.util.Log;

import com.example.pokemonapitest.AppConstants;
import com.example.pokemonapitest.ThreadExecutor;
import com.example.pokemonapitest.api_controller.PokemonApi;
import com.example.pokemonapitest.api_controller.PokemonService;
import com.example.pokemonapitest.api_model.PokemonListResponse;
import com.example.pokemonapitest.api_model.common.NamedApiResponse;

import java.util.List;

public class PokemonDatasource {
    private static volatile PokemonDatasource instance;
    private static final Object sLock = new Object();
    private PokemonDao pokemonDao;

    public static PokemonDatasource getInstance(Application application) {
        if (instance == null) {
            synchronized (sLock) {
                if (instance == null) {
                    instance = new PokemonDatasource(application);
                }
            }
        }
        return instance;
    }

    private PokemonDatasource(Application application) {
        pokemonDao = PokemonDatabase.getInstance(application).pokemonDao();
    }

    public void fullSync(DataLoaderCallBack callBack) {
        final int limit = 20;
        ThreadExecutor.getInstance().execute(() -> {
            int offset = 0;
            PokemonService service = PokemonApi.getRetrofitInstance().create(PokemonService.class);
            try {
                pokemonDao.deleteAll();
                PokemonListResponse response = service.getListPokemons(offset, offset + limit).execute().body();
                while (response.getNext() != null) {
                    offset += limit;
                    Log.d(AppConstants.APP_TAG, "fullSync: " + response.getNext());
                    int resultSize = response.getResults().size();
                    List<NamedApiResponse> responseList = response.getResults();
                    Pokemon[] pokemons = new Pokemon[resultSize];
                    for (int i = 0; i < resultSize; ++i) {
                        NamedApiResponse namedApiResponse = responseList.get(i);
                        pokemons[i] = new Pokemon(namedApiResponse.getId(), namedApiResponse.getName());
                    }
                    pokemonDao.insert(pokemons);
                    response = service.getListPokemons(offset, limit).execute().body();
                }
                callBack.onLoadFinished();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
