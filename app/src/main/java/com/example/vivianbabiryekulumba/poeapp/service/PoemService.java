package com.example.vivianbabiryekulumba.poeapp.service;

import com.example.vivianbabiryekulumba.poeapp.models.Poem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PoemService {

    @GET("api/v1/randompoems")
    Call<List<Poem>> getPoemList();
}
