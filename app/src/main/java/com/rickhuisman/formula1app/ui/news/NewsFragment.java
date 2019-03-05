package com.rickhuisman.formula1app.ui.news;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.news.models.RssFeed;
import com.rickhuisman.formula1app.viewmodels.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private View mView;
    private NewsViewModel mNewsViewModel;
    private NewsAdapter mAdapter;
    private RecyclerView mArticlesList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("NEWS");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mArticlesList = mView.findViewById(R.id.race_schedule);
        mArticlesList.setLayoutManager(new LinearLayoutManager(getContext()));
        mArticlesList.setHasFixedSize(true);

        mAdapter = new NewsAdapter();
        mArticlesList.setAdapter(mAdapter);

        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mNewsViewModel.getArticles().enqueue(test);
    }

    Callback<RssFeed> test = new Callback<RssFeed>() {
        @Override
        public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
            mAdapter.setArticles(response.body().getChannel().getItemList());
        }

        @Override
        public void onFailure(Call<RssFeed> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news, container, false);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.filter_new:
                System.out.println("Filter_new");
                break;
            case R.id.filter_populair:
                System.out.println("Filter_populair");
                break;
            case R.id.filter_videos:
                System.out.println("Filter_videos");
                break;
            case R.id.item_sources:
                showSourcesDialog();
                break;
        }

        return true;
    }

    private void showSourcesDialog() {
        CharSequence[] sources = {"Autosport.com", "PlanetF1.com", "SkySports.com", "Formula1.com", "GPfans.com", "Youtube.com"};
        boolean[] checkedSources = {false, true, true, true, true, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
        builder
                .setTitle("News Sources")
                .setMultiChoiceItems(sources, checkedSources, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
}
