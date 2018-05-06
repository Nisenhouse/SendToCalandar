package com.example.nisenhouse.texttocalander;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        // shared link support
        ClipData clipData = intent.getClipData();
        if (clipData == null || clipData.getItemCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuilder text = new StringBuilder();
        for (int i = 0; i < clipData.getItemCount(); i ++) {
            text.append(clipData.getItemAt(i).getText()).append("\n");
        }
        addToCalandar(text.toString());
    }

    private void addToCalandar(String s) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, s);
        intent.setType("vnd.android.cursor.item/event");
        startActivity(intent);
    }
}
