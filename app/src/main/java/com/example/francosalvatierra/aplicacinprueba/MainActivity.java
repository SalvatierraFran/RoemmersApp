package com.example.francosalvatierra.aplicacinprueba;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtView = (TextView)findViewById(R.id.txt);

        Intent receivedIntent = getIntent();

        if(receivedIntent.getAction().equals(Intent.ACTION_SEND))
        {
            ClipData receivedAction = receivedIntent.getClipData();

            txtView.setText(receivedAction.toString());
        }

        ((Button)findViewById(R.id.commit_main)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlert("Simulación de Upload");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mShare:
                /*Intent i = new Intent(
                        Intent.ACTION_ALL_APPS);
                i.setType("text/plain");
                i.putExtra(
                        android.content.Intent.EXTRA_TEXT, "My new app https://play.google.com/store/search?q=TECHHUBINDIAN");
                startActivity(Intent.createChooser(
                        i,
                        "Share Via"));*/
                new MaterialFilePicker()
                        .withActivity(MainActivity.this)
                        .withRequestCode(1)
                        .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(true) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
        }

        Toast.makeText(getApplicationContext(), "You click on menu share", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    public void ShowAlert(String text){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Simulación de Upload")
                .setMessage(text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
