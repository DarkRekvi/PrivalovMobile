package com.example.up.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.up.database.entities.products;
import com.example.up.database.viewmodels.productsViewModel;
import com.example.up.databinding.FragmentArtistAddBinding;

public class ProductAddFragment extends Fragment {
    private boolean update;
    private products upd;

    public ProductAddFragment(boolean update, products upd) {

        this.update = update;

        this.upd = upd;
    }

    public ProductAddFragment() {

        this.update = false;
        this.upd = null;
    }

    FragmentArtistAddBinding binding;
    productsViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(productsViewModel.class);
        binding = FragmentArtistAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (update) {
            binding.addArtistSave.setVisibility(View.GONE);
            binding.addArtistFirstName.setText(upd.artist_first_name);
            binding.addArtistLastName.setText(upd.artist_last_name);
            binding.addArtistUpdate.setOnClickListener(view1 -> {
                products item = new products();
                item.artist_first_name = binding.addArtistFirstName.getText().toString();
                item.artist_last_name = binding.addArtistLastName.getText().toString();
                Log.d(ProductAddFragment.class.getName(), String.format("Updating artist with id %d to %s %s", item.artist_id, item.artist_first_name, item.artist_last_name));
                viewModel.updateArtist(item);
                getActivity().getSupportFragmentManager().popBackStack();
            });
        } else {

            binding.addArtistUpdate.setVisibility(View.GONE);
            binding.addArtistSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    products item = new products();
                    item.artist_first_name = binding.addArtistFirstName.getText().toString();
                    item.artist_last_name = binding.addArtistLastName.getText().toString();
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.addArtist(item);
                        }
                    });
                    getActivity().getSupportFragmentManager().popBackStack();
                    thread.start();
                }
            });
        }
    }
}