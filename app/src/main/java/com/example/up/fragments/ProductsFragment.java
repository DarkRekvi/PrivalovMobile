package com.example.up.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.up.R;
import com.example.up.adapters.artistAdapter;
import com.example.up.database.entities.products;
import com.example.up.database.Database;
import com.example.up.database.viewmodels.productsViewModel;
import com.example.up.databinding.FragmentArtistBinding;

import java.util.List;

public class ProductsFragment extends Fragment {

    FragmentArtistBinding binding;
    productsViewModel viewModel;
    artistAdapter artistAdapt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        viewModel = new ViewModelProvider(this).get(productsViewModel.class);
        binding = FragmentArtistBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        showArtistsList();
        addBtnInit();
        deleteArtist();
        updateArtist();
    }

    private void showArtistsList(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Database db = Database.getDatabase(getContext());
                List<products> productsList = db.artistDao().getAllArtists();
                artistAdapt = new artistAdapter(getContext(), R.layout.artist_item, productsList);
                binding.artistsView.setAdapter(artistAdapt);
            }
        });
        thread.start();
    }

    private void addBtnInit(){
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new ProductAddFragment(), "artistAdd")
                        .addToBackStack("artistAdd")
                        .commit();
            }
        });
    }

    private void deleteArtist(){

        binding.artistsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        products products = artistAdapt.getItem(i);
                        viewModel.deleteArtist(products);
                        removeArtistOnMainThread(products);
                    }
                });
                thread.start();
                return false;
            }
        });
    }

    private void removeArtistOnMainThread(products artist) {
        requireActivity().runOnUiThread(() -> {
            artistAdapt.remove(artist);
        });
    }

    private void updateArtist(){
        binding.artistsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new ProductAddFragment(true, artistAdapt.getItem(i)), "artistAdd")
                        .addToBackStack("artistAdd")
                        .commit();
            }
        });
    }
}
