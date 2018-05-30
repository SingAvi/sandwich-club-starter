package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws Exception

    {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject name = jsonObject.getJSONObject("name");
        String mainName = name.getString("mainName");

        // String containing multiple names of respective items
        List<String> alsoKnownAsList = new ArrayList<String>();
        JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
        if (alsoKnownAs != null)
            for (int i =0; i< alsoKnownAs.length();i++)
                alsoKnownAsList.add(alsoKnownAs.getString(i));





        //Origin
        String placeOfOrigin = jsonObject.getString("placeOfOrigin");


        //Description of the item
        String Description = jsonObject.getString("description");

        //Image of the item
        String image = jsonObject.getString("image");


        //Ingredients in the item
        List<String> IngredientsList = new ArrayList<String>();
        JSONArray ingredients = jsonObject.getJSONArray("ingredients");
        if (ingredients != null)

            for (int i=0; i< ingredients.length(); i++)

                IngredientsList.add(ingredients.getString(i));





        return new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,Description,image,IngredientsList);

    }
}
