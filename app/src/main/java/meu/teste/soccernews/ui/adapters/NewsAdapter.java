package meu.teste.soccernews.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import meu.teste.soccernews.databinding.NewsItemBinding;
import meu.teste.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> news;
    private NewsListener favoriteListener; // Adiciona o OnClickListener para ativar a função do botão de favoritar

    public NewsAdapter(List<News> news, NewsListener favoriteListener) { // Adiciona o OnClickListener para ativar a função do botão de favoritar
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        // holder.binding.tvTitle.setText(news.getTitle()); // Usava getTilte (do getter and setter) pq a variável era privada
        holder.binding.tvTitle.setText(news.image);
        // holder.binding.tvDescription.setText(news.getDescription()); // Usava getTilte (do getter and setter) pq a variável era privada
        holder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image).fit().into(holder.binding.ivThumbnail); // Atribui ao imageview a imagem da api

        holder.binding.btOpenLink.setOnClickListener(view -> { // Abre o link
            String url = "http://www.example.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            holder.itemView.getContext().startActivity(i); // Pega o context para o startActivity
        });

        holder.binding.ivShare.setOnClickListener(view -> { // Compartilha o link
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i, ""));
        });

        holder.binding.ivFavorite.setOnClickListener(view -> {
            this.favoriteListener.click(news);
        }); // Salva o clicado no banco de dados (O evento é instanciado pelo fragmento)
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface NewsListener {
        void click(News news);
    }

}
