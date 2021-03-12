package Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.matkinson.recycleviewgroceries.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

import Data.GroceryRecyclerViewAdapter;
import Model.Grocery;
import Util.Constants;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroceryRecyclerViewAdapter groceryRecyclerViewAdapter;
    private List<Grocery> groceryList;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryList = new ArrayList<>();
    }


    // Methode pour recupérer les différents films...
    public List<Grocery> getMovies(String searchTerm) {
        groceryList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_LEFT   + Constants.API_KEY + Constants.URL_RIGHT,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray groceriesArray = response.getJSONArray("Search");

                    for (int i = 0; i < groceriesArray.length(); i++) {

                        JSONObject groceryObj = groceriesArray.getJSONObject(i);

                        Grocery grocery = new Grocery();
                        grocery.setBrand(grocery.getBrand());
                        grocery.setName(grocery.getName());
                        grocery.setIngredients(grocery.getIngredients());
                        grocery.setPublishedDate(grocery.getPublishedDate());

                        Log.d("Movies =: ", grocery.getName());
                      //  groceryList.add(grocery);

                    }

                    // pour mettre à jour les résultats de la recherche
                  //  groceryRecyclerViewAdapter.notifyDataSetChanged();


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);

        return groceryList;

    }
}