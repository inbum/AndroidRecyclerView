package com.journaldev.recyclerviewmultipleviewtype;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class MultiViewTypeAdapter extends RecyclerView.Adapter<MultiViewTypeAdapter.BaseViewHolder> {

    public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
        private BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bind(T type);
    }

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;
    public MediaPlayer mPlayer;
    public boolean fabStateVolume = false;

    public class TextTypeViewHolder extends BaseViewHolder<Model> {

        @BindView(R.id.text_type)
        TextView txtType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Model type) {
            txtType.setText(type.text);
        }
    }

    public class ImageTypeViewHolder extends BaseViewHolder<Model> {

        @BindView(R.id.image_type)
        TextView txtType;
        @BindView(R.id.background)
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Model type) {
            txtType.setText(type.text);
            image.setImageResource(type.data);
        }
    }

    public class AudioTypeViewHolder extends BaseViewHolder<Model> {
        @BindView(R.id.audio_type)
        TextView txtType;
        @BindView(R.id.fab)
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(Model type) {
            txtType.setText(type.text);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (fabStateVolume) {
                        if (mPlayer.isPlaying()) {
                            mPlayer.stop();

                        }
                        fab.setImageResource(R.drawable.volume);
                        fabStateVolume = false;

                    } else {
                        mPlayer = MediaPlayer.create(mContext, R.raw.sound);
                        mPlayer.setLooping(true);
                        mPlayer.start();
                        fab.setImageResource(R.drawable.mute);
                        fabStateVolume = true;

                    }
                }
            });
        }
    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);
        }
        return null;


    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);
        holder.bind(object);

    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.AUDIO_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
