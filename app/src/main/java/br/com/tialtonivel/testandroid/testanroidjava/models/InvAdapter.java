package br.com.tialtonivel.testandroid.testanroidjava.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.tialtonivel.testandroid.testanroidjava.R;

public class InvAdapter extends RecyclerView.Adapter<InvAdapter.InvVH> {

    List<Investment> investments;
    Context ctx;
    Typeface tf;

    public InvAdapter(List<Investment> investments, Context ctx) {
        this.investments = investments;
        this.ctx = ctx;
        tf = Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf");
    }

    @NonNull
    @Override
    public InvVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvVH(LayoutInflater.from(ctx).inflate(R.layout.inv_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvVH holder, int position) {
        holder.bind(investments.get(position));
    }

    @Override
    public int getItemCount() {
        return investments.size();
    }

    class InvVH extends RecyclerView.ViewHolder {

        TextView tv_title, tv_first, tv_last;
        ImageView iv_download;

        InvVH(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.inv_title);
            tv_first = itemView.findViewById(R.id.inv_first);
            tv_last = itemView.findViewById(R.id.inv_last);
            iv_download = itemView.findViewById(R.id.inv_download);
            tv_title.setTypeface(tf);
            tv_first.setTypeface(tf);
            tv_last.setTypeface(tf);
        }

        void bind(Investment investment) {
            tv_title.setText(investment.title);
            tv_first.setText(investment.first);
            tv_last.setText(investment.last);
            tv_last.setTextColor(investment.textColor);
            boolean v = investment.download;
            iv_download.setVisibility(v ? View.VISIBLE : View.INVISIBLE);
        }
    }
    

}
