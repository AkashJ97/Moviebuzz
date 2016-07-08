package org.world.asa.moviebuzz;


import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.world.asa.moviebuzz.api.ApiInterface;
import org.world.asa.moviebuzz.model.OmdbModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private View supportMenuInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ApiInterface.retrofit.create(ApiInterface.class);

                EditText t = (EditText) findViewById(R.id.movie);
                String movie = t.getText().toString();

                final Call<OmdbModel> call =
                        apiInterface.addtitle(movie);

                call.enqueue(new Callback<OmdbModel>() {
                    @Override
                    public void onResponse(Call<OmdbModel> call, Response<OmdbModel> response) {
                        final TextView textView = (TextView) findViewById(R.id.title);
                        final TextView textView1 = (TextView) findViewById(R.id.year);
                        final TextView textView2 = (TextView) findViewById(R.id.genre);
                        final TextView textView3 = (TextView) findViewById(R.id.runtime);
                        final TextView textView4 = (TextView) findViewById(R.id.director);
                        final TextView textView5 = (TextView) findViewById(R.id.actors);
                        final TextView textView6 = (TextView) findViewById(R.id.plot);
                        final TextView textView7 = (TextView) findViewById(R.id.imdbr);
                        final TextView textView8 = (TextView) findViewById(R.id.type);
                        final TextView textView9 = (TextView) findViewById(R.id.lang);
                        final TextView textView10 = (TextView) findViewById(R.id.rated);
                        OmdbModel model = (OmdbModel) (response.body());
                        ImageView imageView = (ImageView) findViewById(R.id.imageView);

                        Picasso.with(MainActivity.this)
                                .load(model.getPoster())
                                .into(imageView);
                        textView.setText("  " + model.getTitle());
                        textView1.setText("  " + model.getYear());
                        textView2.setText("  " + model.getGenre());
                        textView3.setText("  " + model.getRuntime());
                        textView4.setText("  " + model.getDirector());
                        textView5.setText("  " + model.getActors());
                        textView6.setText("  " + model.getPlot());
                        textView7.setText("  " + model.getImdbRating());
                        textView8.setText("  " + model.getType());
                        textView9.setText("  " + model.getLanguage());
                        textView10.setText("  " + model.getRated());
                    }

                    @Override
                    public void onFailure(Call<OmdbModel> call, Throwable t) {
                        final TextView textView = (TextView) findViewById(R.id.genre);
                        textView.setText("Something went wrong: " + t.getMessage());

                    }
                });
            }
        });


    }


}


