package com.journaldev.recyclerviewmultipleviewtype;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }
    abstract void bind(Model model);
}
