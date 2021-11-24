package com.example.logingoogledangtruongan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements OnClickListener{
    private RecyclerView rcvNhac;
    private NhacAdapter nhacAdapter;
    private ArrayList<Nhac> nhacs;

    GoogleSignInClient mGoogleSignInClient;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signout = findViewById(R.id.sign_out);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.sign_out:
                        signOut();
                        break;
                    // ...
                }
            }
        });
        rcvNhac = findViewById(R.id.rcvNhac);
        nhacs = new ArrayList<>();
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));
        nhacs.add(new Nhac("Love Story", "Taylor Swift", R.drawable.eclipse));

        nhacAdapter = new NhacAdapter(this, nhacs, this);
        rcvNhac.setAdapter(nhacAdapter);
        rcvNhac.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvNhac.setLayoutManager(linearLayoutManager);

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(SecondActivity.this, "Sign out sussecfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    @Override
    public void itemClick(int pos, ImageView imgTayLoy, TextView tvTayLoy, TextView tvLove) {
        Intent intent = new Intent(SecondActivity.this, MainActivity2.class);

        intent.putExtra("nhac", nhacs.get(pos));

        Pair<View, String> p1 = Pair.create((View) imgTayLoy, "img");
        Pair<View, String> p2 = Pair.create((View) tvTayLoy, "tvTayLoy");
        Pair<View, String> p3 = Pair.create((View) tvLove, "tvLove");

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, optionsCompat.toBundle());
        } else
            startActivity(intent);
    }
}