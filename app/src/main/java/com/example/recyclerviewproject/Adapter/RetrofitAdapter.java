package com.example.recyclerviewproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recyclerviewproject.R;
import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Model.TestingModelItem;

import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    Context context;
    List<TestingModelItem> cocktailModelArrayList;
    ItemOnClickListener itemOnClickListener;

    public RetrofitAdapter(Context context, List<TestingModelItem> cocktailModelArrayList, ItemOnClickListener itemOnClickListener) {
        this.context = context;
        this.cocktailModelArrayList = cocktailModelArrayList;
        this.itemOnClickListener = itemOnClickListener;
    }

    public void setCatList(List<TestingModelItem> cocktailModelArrayList) {
        this.cocktailModelArrayList = cocktailModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.title.setText(cocktailModelArrayList.get(position).getTitle());
        Glide.with(context)
                .load(cocktailModelArrayList.get(position).getUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickListener.imageClick(cocktailModelArrayList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cocktailModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title_txt);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public interface ItemOnClickListener {
        void imageClick(TestingModelItem item);
    }
}


