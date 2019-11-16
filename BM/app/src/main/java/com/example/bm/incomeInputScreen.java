package com.example.bm;

import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class incomeInputScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FloatingActionButton back;
    private EditText DescriptionBox;
    private Button submitButton;
    private dataViewModel dataViewModel;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_inputscreen);
        dataViewModel = new ViewModelProvider(this).get(dataViewModel.class);

        // Mitchell:
        // This code connects the xml back button and the java object
        // then it creates a click listener, so that when we click on it
        // the button calls the returnToMainMenuPlease()
        back = (FloatingActionButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenuPlease();
            }
        });

        // Mitchell
        // This code enables the categories spinner on the expense input page
        Spinner spinner = findViewById(R.id.spinner); // create new spinner object
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.incomeCategoriesArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Mitchell
        // --------------------------USER INPUT -----------------------------
        DescriptionBox = findViewById(R.id.incomeDescript);
        submitButton = (Button) findViewById(R.id.incomeSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String stringIN = DescriptionBox.getText().toString();
                if(!stringIN.equals("")) {
                    // Does not work because has to be called on a secondary thread
                    description newDataBaseItem = new description(stringIN);
                    dataViewModel.insert(newDataBaseItem);
                } else {
                    toastMessage("Nothing to submit");
                }
            }
        });


    } // end onCreate

    // This function simply sends the user back to the main menu activity
    public void returnToMainMenuPlease() {
        Intent intent = new Intent (this, homePageActivity.class);
        startActivity(intent);
    }

    // Mitchell
    // This code is so that the item tapped from the dropdown menu is actually selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //toastMessage(text);
    }

    // Code created by default in android studio
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    // Toast message function for data entry input
    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}