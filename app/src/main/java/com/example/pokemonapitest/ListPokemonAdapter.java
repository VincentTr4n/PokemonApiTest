package com.example.pokemonapitest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapitest.data.Pokemon;
import com.example.pokemonapitest.databinding.PokemonItemLayoutBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ListPokemonAdapter extends ListAdapter<Pokemon, ListPokemonAdapter.PokemonViewHolder> {

    public ListPokemonAdapter() {
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Pokemon> CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pokemon_item_layout, parent, false);
        return new PokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        PokemonItemLayoutBinding mBinding;

        public PokemonViewHolder(PokemonItemLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Pokemon pokemon) {
            mBinding.tvPokemonId.setText(String.format("#%04d", pokemon.getId()));
            mBinding.tvPokemon.setText(pokemon.getName());
            String imageUrl = makeImageUrl(pokemon.getId());
            Picasso.get().load(imageUrl).into(mBinding.ivPokemon, new PaletteCallBack());
        }

        String makeImageUrl(int id) {
            return String.format(AppConstants.BASE_IMAGE_URL, id);
        }

        class PaletteCallBack implements Callback {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) mBinding.ivPokemon.getDrawable()).getBitmap();
                Palette palette = Palette.generate(bitmap);
                if (palette != null) {
                    Palette.Swatch swatch = palette.getMutedSwatch();
                    if (swatch != null) {
                        int color = swatch.getRgb();
                        mBinding.getRoot().setBackgroundColor(color);
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                mBinding.ivPokemon.setImageResource(R.drawable.miss);
            }
        }
    }
}
