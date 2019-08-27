package com.example.testexpandablerecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Movie> movieList = new ArrayList<>();
    Context context;

    public Adapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {
        final Movie model = movieList.get(position);

        // Get the state
        boolean expanded = model.isExpanded();
        // Set the visibility based on state
        holder.sub_item.setVisibility(expanded ? View.VISIBLE : View.GONE);


        holder.item_title.setText(model.getTitle());
        holder.sub_item_genre.setText(model.getGenre());
        holder.sub_item_year.setText(String.valueOf(model.getYear()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current state of the item
                boolean expanded = model.isExpanded();
                // Change the state
                model.setExpanded(!expanded);
                // Notify the adapter that item has changed


                holder.webview.loadUrl("https://www.shahrekhabar.com");

                holder.webview.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here
                        Toast.makeText(context, "loading finished", Toast.LENGTH_SHORT).show();
                        // hide loading...
                    }
                });


                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_title, sub_item_genre, sub_item_year;
        LinearLayout sub_item;
        WebView webview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title = itemView.findViewById(R.id.item_title);
            sub_item_genre = itemView.findViewById(R.id.sub_item_genre);
            sub_item_year = itemView.findViewById(R.id.sub_item_year);
            sub_item = itemView.findViewById(R.id.sub_item);
            webview = itemView.findViewById(R.id.webview);

        }


    }
}
