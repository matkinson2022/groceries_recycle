package Data;

import android.content.Context;
import android.graphics.Movie;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Grocery;

public class GroceryRecyclerViewAdapter extends RecyclerView.Adapter<GroceryRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Grocery> groceryList;
    public GroceryRecyclerViewAdapter(Context context, List<Grocery> groceries) {
    this.context=context;
    groceryList=groceries;
    }
    @NonNull
    @Override
    public GroceryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryRecyclerViewAdapter.ViewHolder holder, int position) {
        Grocery grocery = groceryList.get(position);
        holder.name.setText(grocery.getName());
        holder.brand.setText(grocery.getBrand());
        holder.year.setText(grocery.getPublishedDate());

    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView brand;
        TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
