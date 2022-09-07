package meu.teste.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import meu.teste.soccernews.MainActivity;
import meu.teste.soccernews.databinding.FragmentNewsBinding;
import meu.teste.soccernews.ui.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext())); // Atribui ao Recyclerview um linear layout
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            // O view é chamado quando o botão ivFavorite de NewsAdapter é clicado
            binding.rvNews.setAdapter(new NewsAdapter(news, favoriteNews -> {
                newsViewModel.saveNews(favoriteNews).observe(getViewLifecycleOwner(), unused ->  {});
            })); // Atribui o Adapter ao recyclerview
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}