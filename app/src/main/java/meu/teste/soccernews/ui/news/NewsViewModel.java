package meu.teste.soccernews.ui.news;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import meu.teste.soccernews.data.local.AppDatabase;
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


    public NewsViewModel() { // Injeta a instacia do aplicativo no ViewModel
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ebditore.github.io/soccer_news_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsApi.class);

//        db = Room.databaseBuilder(app, // Instancia o banco de dados passando a instancia do aplicativo (app)
//                AppDatabase.class, "soccer-news").build(); // Nome atribuído ao banco de dados = soccer-news

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
                t.printStackTrace(); // Imprime um problema
                // TODO tratar erros
            }
        });
    }

    public LiveData<List<News>> getNews() { return this.news; }
}