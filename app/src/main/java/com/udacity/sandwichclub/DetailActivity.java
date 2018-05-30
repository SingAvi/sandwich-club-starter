package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView origin;
    TextView description;
    TextView ingredients;
    TextView alsoKnownAs;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try
        {
            sandwich = JsonUtils.parseSandwichJson(json);
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        origin.setText(sandwich.getPlaceOfOrigin());

        description.setText(sandwich.getDescription());

        //display ingredients array
        List<String> ingredientsList = sandwich.getIngredients();
        for (int i=0;i<ingredientsList.size(); i++)
        {
            ingredients.append(ingredientsList.get(i) + " | ");
        }
        //display alsoKnownAs array
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        if (alsoKnownAsList == null)
            alsoKnownAs.setText("-");
        else
            for (int i=0;i<alsoKnownAsList.size(); i++)
            {
                alsoKnownAs.append(alsoKnownAsList.get(i) + " | ");
            }


        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
            setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        origin      = (TextView) findViewById(R.id.origin_tv);
        description = (TextView) findViewById(R.id.description_tv);
        ingredients = (TextView) findViewById(R.id.ingredients_tv);
        alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);



    }
}
