package meu.teste.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import meu.teste.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news; // Se trocar news por mNews pode retirar os this

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("Gol Gol Gol", "Ab"));
        news.add(new News("Mais Gol", "Abc"));
        news.add(new News("Outro Gol", "Abcd"));
        news.add(new News("Mais Um (Gol)", "A"));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}