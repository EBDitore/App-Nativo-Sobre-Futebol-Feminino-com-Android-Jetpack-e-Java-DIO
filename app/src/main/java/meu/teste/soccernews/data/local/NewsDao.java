package meu.teste.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import meu.teste.soccernews.domain.News;

@Dao
public interface NewsDao { // Uma Dao é uma interface

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Insere dados no banco // Se já houver registro do campo no banco de dados vai fazer um replace
    void insert(News... news);

    @Query("SELECT * FROM news WHERE favorite = 1") // 1 em SQLite é true // Busca dados no banco
    List<News> loadFavoriteNews(boolean favorite);
}
