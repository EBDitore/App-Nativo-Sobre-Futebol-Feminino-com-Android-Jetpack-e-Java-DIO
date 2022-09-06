package meu.teste.soccernews.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import meu.teste.soccernews.data.SoccerNewsRepository;
import meu.teste.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {
    public FavoritesViewModel() {
    }

    public LiveData<List<News>> loadFavoriteNews() {
          return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news);
    }

}