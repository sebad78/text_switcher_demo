package com.zezoz.textanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zezoz.textanim.component.AnimatedText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);

        final AnimatedText a2 = (AnimatedText) findViewById(R.id.at);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(new Random().nextInt(10));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.remove();
            }
        });

        Button b_1 = (Button) findViewById(R.id.button_1);
        Button b_2 = (Button) findViewById(R.id.button_2);
        Button b_3 = (Button) findViewById(R.id.button_3);
        Button b_4 = (Button) findViewById(R.id.button_4);
        Button b_5 = (Button) findViewById(R.id.button_5);
        Button b_6 = (Button) findViewById(R.id.button_6);
        Button b_7 = (Button) findViewById(R.id.button_7);
        Button b_8 = (Button) findViewById(R.id.button_8);
        Button b_9 = (Button) findViewById(R.id.button_9);
        Button b_0 = (Button) findViewById(R.id.button_0);

        b_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(0);
            }
        });

        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(1);
            }
        });

        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(2);
            }
        });

        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(3);
            }
        });

        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(4);
            }
        });

        b_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(5);
            }
        });

        b_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(6);
            }
        });

        b_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(7);
            }
        });

        b_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(8);
            }
        });

        b_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2.addNumber(9);
            }
        });
    }
}
