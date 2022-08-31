package meu.teste.soccernews.data.remote;

import java.util.List;

import meu.teste.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

// Essa classe cria uma ponte (interface) entre a api (remota) e o aplicativo

public interface SoccerNewsApi {
    @GET("news.json") // Declara o nome do arquivo a ser buscado
    Call<List<News>> getNews();
}
