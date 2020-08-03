package com.example.sphinxadmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {

    private List<CategoryModel> categoryModelList;
    private DeleteListener deleteListener;

    public CategoryAdapter(List<CategoryModel> categoryModelList,DeleteListener deleteListener) {
        this.categoryModelList = categoryModelList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(categoryModelList.get(position).getUrl(),categoryModelList.get(position).getName(),categoryModelList.get(position).getKey(),position);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class  viewholder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView title;
        private ImageButton delete;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.titlehead);
            delete = itemView.findViewById(R.id.delete);
        }

        private void setData(String url, final String title1, final String key,final int position){
            Glide.with(itemView.getContext()).load(url).into(imageView);


            //System.out.println((title));
            title.setText(title1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent = new Intent(itemView.getContext(),SetsActivity.class);
                    setIntent.putExtra("title",title1);
                    setIntent.putExtra("position",position);
                    setIntent.putExtra("key",key);
                    itemView.getContext().startActivity(setIntent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteListener.ondelete(key,position);
                }
            });
        }
    }

    public interface DeleteListener{
        public void ondelete(String key,int position);
    }
}
