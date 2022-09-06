package meu.teste.soccernews.ui.news;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import meu.teste.soccernews.data.SoccerNewsRepository;
import meu.teste.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();


    public NewsViewModel() { // Injeta a instacia do aplicativo no ViewModel
        this.findNews();
    }

    private void findNews() {
        SoccerNewsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() { // Busca os dados na Interface SoccerNewsApi
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

    public void saveNews(News news) {
        SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news);
    }

    public LiveData<List<News>> getNews() { return this.news; }
    public LiveData<State> getState() { return this.state; }
}