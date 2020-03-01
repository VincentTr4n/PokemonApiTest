package com.example.pokemonapitest.api_model;

import com.example.pokemonapitest.api_model.common.NamedApiResponse;

import java.util.List;

public class PokemonListResponse {
    private int count;
    private String next;
    private String previous;
    private List<NamedApiResponse> results;

    public PokemonListResponse() {
    }

    public PokemonListResponse(int count, String next, String previous, List<NamedApiResponse> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<NamedApiResponse> getResults() {
        return results;
    }

    public void setResults(List<NamedApiResponse> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PokemonListResponse{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
