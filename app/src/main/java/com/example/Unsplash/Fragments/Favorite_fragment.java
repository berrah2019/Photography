package com.example.Unsplash.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Unsplash.R;
import com.example.Unsplash.Utils.RealmController;
import com.example.Unsplash.adapters.PhotosAdatper;
import com.example.Unsplash.models.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Favorite_fragment extends Fragment {

    @BindView(R.id.fragment_favorite_recyclerview)
    RecyclerView photosRecyclerView;
    @BindView(R.id.fragment_favorite_notification)
    TextView notification;

    private PhotosAdatper photosAdatper;
    private List<Photo> photos = new ArrayList<>();
    private Unbinder unbinder;


        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_favorite,container,false);
            unbinder = ButterKnife.bind(this, view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            photosRecyclerView.setLayoutManager(linearLayoutManager);
            photosAdatper = new PhotosAdatper(getActivity(), photos);
            photosRecyclerView.setAdapter(photosAdatper);
            Log.d("Favorite", "Favorite");
            getPhotos();

            return view;
    }

    private void getPhotos() {
        RealmController realmController = new RealmController();
        photos.addAll(realmController.getPhotos());
        if(photos.size() == 0){
            notification.setVisibility(View.VISIBLE);
            photosRecyclerView.setVisibility(View.GONE);
        }else{
            photosAdatper.notifyDataSetChanged();
        }

    }

    @Override
        public void onDestroyView() {

            super.onDestroyView();

        unbinder.unbind();
    }
}
