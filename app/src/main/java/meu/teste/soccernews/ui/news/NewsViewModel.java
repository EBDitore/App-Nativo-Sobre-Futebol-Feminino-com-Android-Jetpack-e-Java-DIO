package meu.teste.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import meu.teste.soccernews.data.remote.SoccerNewsApi;
import meu.teste.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api; // Declara a inteface SoccerNewsApi à variável api

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ebditore.github.io/soccer_news_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsApi.class);
        this.findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() { // Busca os dados na Interface SoccerNewsApi
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {       // Se a chamada for respondida como sucesso
                    news.setValue(response.body());     // Atribui ao LiveData news o conteúdo do json (api)
                } else {
                    // TODO tratar erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                // TODO tratar erros
            }
        });
    }

    public LiveData<List<News>> getNews() { return this.news; }
}