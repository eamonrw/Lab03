package com.weingoldeamon.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar slider;
    SharedPreferences pref;
    TextView[] tv = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv[0] = findViewById(R.id.t1_box);
        tv[1] = findViewById(R.id.t2_box);
        tv[2] = findViewById(R.id.t3_box);
        tv[3] = findViewById(R.id.t4_box);
        slider = findViewById(R.id.text_size_bar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(TextView t : tv)
                    t.setTextSize(progress+10);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        pref = getSharedPreferences("TextView_Clicks", Context.MODE_PRIVATE);

    }

    public void showToast(View view) {
        SharedPreferences.Editor editor = pref.edit();

        String tvText = ((TextView)view).getText().toString();
        int count = pref.getInt(tvText, 0);
        count++;
        String text = tvText + " Pressed " + count + " Time(s)";
        Toast t = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
        t.show();
        editor.putInt(tvText, count);

        editor.apply();
    }
}
