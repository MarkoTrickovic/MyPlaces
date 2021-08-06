package com.mosis.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int NEW_PLACE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bundle positionBundle = new Bundle();
                //positionBundle.putInt("position", NEW_PLACE);
                Intent intent = new Intent(MainActivity.this, EditMyPlaceActivity.class);
                //intent.putExtras(positionBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.show_map_item) {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("state", MapsActivity.SHOW_MAP);
            startActivity(intent);
        }
        else if (id == R.id.new_place_item) {
            Intent intent = new Intent(this, EditMyPlaceActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.my_places_list_item) {
            Intent intent = new Intent(this, MyPlacesList.class);
            startActivity(intent);
        }
        else if (id == R.id.about_item) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(this,"New Place added", Toast.LENGTH_SHORT).show();
        }
    }
}
