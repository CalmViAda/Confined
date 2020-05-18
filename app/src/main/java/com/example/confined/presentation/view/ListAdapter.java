package com.example.confined.presentation.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confined.R;
import com.example.confined.presentation.model.CountriesInfected;
import com.example.confined.presentation.model.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private List<CountriesInfected> values;
    private List<CountriesInfected> valuesFull;
    private final OnItemClickListener listener;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        ImageView iconFlag;
        View layout;


        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            iconFlag = (ImageView) v.findViewById(R.id.icon);
        }
    }


    public void add(int position, CountriesInfected item) {
        values.add(position, item);
        notifyItemInserted(position);
    }


    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }


    public ListAdapter(List<CountriesInfected> values, OnItemClickListener listener) {
        this.values = values;
        this.listener = (OnItemClickListener) listener;
        valuesFull = new ArrayList<>(values);
    }


    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(v);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CountriesInfected currentCountry = values.get(position);
        holder.txtHeader.setText(currentCountry.getCountry());
        String currentCountryCode = currentCountry.getCountryCode();
        holder.txtFooter.setText(currentCountryCode);
        Picasso.get().load("https://www.countryflags.io/" + currentCountryCode.toLowerCase() + "/flat/64.png").resize(120, 120).into(holder.iconFlag);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentCountry);
            }
        });
    }


    @Override
    public int getItemCount() {
        return values.size();
    }


    @Override
    public Filter getFilter() {
        return countryFilter;
    }


    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CountriesInfected> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(valuesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CountriesInfected countriesInfected : valuesFull) {
                    if (countriesInfected.getCountry().toLowerCase().contains(filterPattern)) {
                        filteredList.add(countriesInfected);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            values.clear();
            values.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}