package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PostRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_registro);
    }
    void PostLog(View view) {
        Intent intent = new Intent(PostRegistro.this, Login.class);
        startActivity(intent);
    }

}
