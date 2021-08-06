package com.mosis.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyPlaceActivity extends AppCompatActivity implements View.OnClickListener {
    int position = -1;
    boolean editMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_place);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Button finishedButton = (Button)findViewById(R.id.editmyplace_finished_button);
        Button cancelButton = (Button)findViewById(R.id.editmyplace_cancel_button);
        Button locationButton = (Button)findViewById(R.id.editmyplace_location_button);

        EditText nameEditText = (EditText)findViewById(R.id.editmyplace_name_edit);
        EditText descEditText = (EditText)findViewById(R.id.editmyplace_desc_edit);
        EditText latEditText = (EditText)findViewById(R.id.editmyplace_lat_edit);
        EditText lonEditText = (EditText)findViewById(R.id.editmyplace_lon_edit);

        finishedButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        locationButton.setOnClickListener(this);

        try {
            Intent listIntent = getIntent();
            Bundle positionBundle = listIntent.getExtras();
            if(positionBundle != null)
                position = positionBundle.getInt("position");
            else
                editMode = false;
        } catch (Exception e) {
            editMode = false;
        }

        if (!editMode) {
            finishedButton.setEnabled(false);
            finishedButton.setText("Add");
        } else if (position >= 0) {
            finishedButton.setText("Save");
            MyPlace place = MyPlacesData.getInstance().getPlace(position);
            nameEditText.setText(place.getName());
            descEditText.setText(place.getDescription());
            latEditText.setText(place.getLatitude());
            lonEditText.setText(place.getLongitude());
        }

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                finishedButton.setEnabled(s.length() > 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.editmyplace_finished_button: {
                EditText etName = (EditText) findViewById(R.id.editmyplace_name_edit);
                EditText etDesc = (EditText) findViewById(R.id.editmyplace_desc_edit);
                EditText latEdit = (EditText)findViewById(R.id.editmyplace_lat_edit);
                EditText lonEdit = (EditText)findViewById(R.id.editmyplace_lon_edit);

                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                String lat = latEdit.getText().toString();
                String lon = lonEdit.getText().toString();

                if (!editMode)
                    MyPlacesData.getInstance().addNewPlace(new MyPlace(name, desc, lon, lat));
                else
                    MyPlacesData.getInstance().updatePlace(position, name, desc, lon, lat);

                setResult(Activity.RESULT_OK);
                finish();
                break;
            }
            case R.id.editmyplace_cancel_button: {
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            }
            case R.id.editmyplace_location_button: {
                Intent i = new Intent(this, MapsActivity.class);
                i.putExtra("state", MapsActivity.SELECT_COORDINATES);
                startActivityForResult(i, 1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try
        {
            // Grab coordinates after MapsActivity completetion.
            if (requestCode == Activity.RESULT_OK)
            {
                EditText lonText = (EditText)findViewById(R.id.editmyplace_lon_edit);
                EditText latText = (EditText)findViewById(R.id.editmyplace_lat_edit);
                String lon = data.getExtras().getString("lon");
                String lat = data.getExtras().getString("lat");
                lonText.setText(lon);
                latText.setText(lat);
            }
        }
        catch (Exception e) {
            // TODO: handle exceptions
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflater - adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_my_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.show_map_item) {
            Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.my_places_list_item) {
            Intent intent = new Intent(this, MyPlacesList.class);
            startActivity(intent);
        }
        else if (id == R.id.about_item) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
