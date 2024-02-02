package com.example.grocery;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {

    private List<GroceryItem> groceryItemList;

    public GroceryAdapter(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroceryItem groceryItem = groceryItemList.get(position);
        holder.checkBoxItem.setText(groceryItem.getName());
        holder.checkBoxItem.setChecked(groceryItem.isSelected());

        holder.checkBoxItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groceryItem.setSelected(holder.checkBoxItem.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return groceryItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBoxItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxItem = itemView.findViewById(R.id.checkBoxItem);
        }
    }
}
