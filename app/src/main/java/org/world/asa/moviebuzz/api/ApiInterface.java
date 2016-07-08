package org.world.asa.moviebuzz.api;
import org.world.asa.moviebuzz.model.OmdbModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET ("?&plot=short&r=json")
    Call <OmdbModel> addtitle(
            @Query("t") String title);



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}