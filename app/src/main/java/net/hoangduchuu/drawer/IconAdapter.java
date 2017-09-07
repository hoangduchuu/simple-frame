package net.hoangduchuu.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by hoang on 8/8/17.
 */

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.MyViewHolder> {
    List<Icon> iconList;
    Context context;
    RecyclerView recyclerView;
    ImageView ivFrame;

    public IconAdapter(List<Icon> iconList, Context context, RecyclerView recyclerView, ImageView ivFrame) {
        this.iconList = iconList;
        this.context = context;
        this.recyclerView = recyclerView;
        this.ivFrame = ivFrame;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_colum, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.ivIcon.setImageResource(iconList.get(position).url);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;

        MyViewHolder(final View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p = recyclerView.getChildAdapterPosition(itemView);
//                    final Animation anim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.anim);
//                    ivFrame.setAnimation(anim);
                    ivFrame.setImageResource(iconList.get(p).url);
                }
            });
        }
    }


}
