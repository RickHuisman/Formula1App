package com.rickhuisman.formula1app.ui.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.news.models.RssItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Article> {

    private Context mContext;
    private List<RssItem> mArticles = new ArrayList<>();

    @NonNull
    @Override
    public Article onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
        return new Article(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Article holder, int position) {
        holder.textViewTitle.setText(mArticles.get(position).getTitle());
        holder.textViewDate.setText("AUTOSPORT.COM • 10-1-2019");
        Glide.with(mContext)
                .load("https://d2d0b2rxqzh1q5.cloudfront.net/sv/1.67/dir/6a6/image/6a6886b3f39268e444ab859755b4bad4.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mArticles.get(holder.getAdapterPosition()).getLink();
                Intent intent = new Intent(mContext, NewsWebView.class);
                intent.putExtra("url", url);
                mContext.startActivity(intent);
            }
        });
    }

    public void setArticles(List<RssItem> articles) {
        this.mArticles = articles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class Article extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewDate;
        ImageView imageView;

        public Article(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_text_view);
            textViewDate = itemView.findViewById(R.id.date_text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
