package com.example.callory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class calculate_calories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_calculate_calories);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Intent intent2 = getIntent();
            Recipe recipe= (Recipe) getIntent().getSerializableExtra("Recipe_class");

            // Image
            ImageView imageView = findViewById(R.id.Counter_img);
            int imgRes = recipe.getImageResource();
//                    intent2.getIntExtra("image", 0);
            imageView.setImageResource(imgRes);

//intent2.getStringExtra("Name")
            TextView titleTextView = findViewById(R.id.Counter_Tittleview);
            titleTextView.setText(recipe.getName());

            int protein = recipe.getProtein();
            int fat = recipe.getFat();
            int carbohydrates = recipe.getCarbohydrates();
            int totalCalories = recipe.getCalories();

            TextView caloryTextView = findViewById(R.id.Calory_Result);
            caloryTextView.setText("Total Calories: " + totalCalories);


            List<String> nutritionList = List.of("Protein: " + protein, "Fat: " + fat, "Carbohydrates: " + carbohydrates);
            ListView listView = findViewById(R.id.listView_Counter);
            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nutritionList));


            List<String> ingredients = intent2.getStringArrayListExtra("ingredients");
            if (ingredients != null) {
                TextView descriptionTextView = findViewById(R.id.Discreption_counter);
                descriptionTextView.setText("Ingredients: " + String.join(", ", ingredients));
            }


            Button backButton = findViewById(R.id.Back_button_Counter);
            backButton.setOnClickListener(v1 -> finish());

            return insets;
        });
    }
}
