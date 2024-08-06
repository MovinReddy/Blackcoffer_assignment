package com.example.blackcoffer_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class RefineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner available;
    private EditText editText;
    private Button save;
    private SeekBar rating;
    private TextView display_distance;
    private ImageView return_back;
    private MaterialButtonToggleGroup toggleGroup;
    private RelativeLayout headingBar;
    private TextView availableTextView, statusText, distanceText, distanceLow, distanceHigh;
    private View[] animatedViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine);

        headingBar = findViewById(R.id.heading_bar);
        return_back = findViewById(R.id.return_back_btn);
        available = findViewById(R.id.spinner_availability);
        editText = findViewById(R.id.user_status);
        save = findViewById(R.id.refine_submit_btn);
        rating = findViewById(R.id.rating_bar);
        display_distance = findViewById(R.id.count_distance);
        toggleGroup = findViewById(R.id.toggle_group);
        availableTextView = findViewById(R.id.available_text_view);
        statusText = findViewById(R.id.status_text);
        distanceText = findViewById(R.id.distance_text);
        distanceLow = findViewById(R.id.distance_low);
        distanceHigh = findViewById(R.id.distance_high);


        animatedViews = new View[]{availableTextView, available, statusText, editText, distanceText, rating, distanceLow, distanceHigh};

        setUpSpinner1();
        applyAnimations();
        setListeners();
    }

    private void applyAnimations() {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);


        headingBar.startAnimation(fadeIn);


        for (View view : animatedViews) {
            view.startAnimation(slideInRight);
        }

        // Apply bounce animation to buttons when clicked
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        save.setOnClickListener(view -> {
            view.startAnimation(bounce);
            // Handle button click logic
            Intent intent = new Intent(RefineActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                Button selectedButton = findViewById(checkedId);
                selectedButton.startAnimation(bounce);
                String buttonText = selectedButton.getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + buttonText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpSpinner1() {
        String[] items = {
                "Available | Hey Let Us Connect",
                "Away | Stay Discrete And Watch",
                "Busy | Do not Disturb | Will Catch Up Later",
                "SOS | Emergency | Need Assistance | HELP"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        available.setAdapter(adapter);
        available.setOnItemSelectedListener(this);
    }

    private void setListeners() {
        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                display_distance.setText("Distance: " + i + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return_back.setOnClickListener(view -> {
            Intent intent = new Intent(RefineActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "Selected : " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "nothing selected", Toast.LENGTH_LONG).show();
    }
}
