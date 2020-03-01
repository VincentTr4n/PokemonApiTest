package com.example.pokemonapitest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pokemonapitest.data.DataLoaderCallBack;
import com.example.pokemonapitest.data.Pokemon;
import com.example.pokemonapitest.data.PokemonDatabase;
import com.example.pokemonapitest.data.PokemonDatasource;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements DataLoaderCallBack {

    private LiveData<List<Pokemon>> mData;
    private MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);

        mData = PokemonDatabase.getInstance(application)
                .pokemonDao()
                .getListPokemons();

        mLoading.setValue(true);
        PokemonDatasource.getInstance(application).fullSync(this);
    }

    public LiveData<List<Pokemon>> getData() {
        return mData;
    }

    @Override
    public void onLoadFinished() {
        mLoading.postValue(false);
    }

    public LiveData<Boolean> getLoading() {
        return mLoading;
    }
}
