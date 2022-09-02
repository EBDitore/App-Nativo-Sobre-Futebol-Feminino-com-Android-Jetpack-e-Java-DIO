package meu.teste.soccernews.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey
    public int id;

//    @ColumnInfo(name = "first_name") // Exemplo de como mudar o nome do campo no banco de dados
//    public String firstName;

    public String title;
    public String description;
    public String image;
    public String link;
    public boolean favorite;

}