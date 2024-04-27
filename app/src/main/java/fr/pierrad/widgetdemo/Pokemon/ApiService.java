package fr.pierrad.widgetdemo.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random/team/")
    Call<List<Pokemon>> getTeam();
}
