package com.example.ashraf.movieapp.UI.Activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ashraf.movieapp.UI.Fragment.GridFragment.GridFragment;
import com.example.ashraf.movieapp.R;
import com.example.ashraf.movieapp.Util.MyUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.landscapeActivityLinearlayout)
    LinearLayout linearLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUtil.createDbAdabtor(getBaseContext());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.old_activity_main);
        else {
            setContentView(R.layout.landscape_main_activity);
            ButterKnife.bind(this);
        }

        if (getSupportFragmentManager().findFragmentByTag("GridFragment")==null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame1, new GridFragment(), "GridFragment");
            fragmentTransaction.commit();
        }
        else
        {
            android.support.v4.app.FragmentManager fm1 = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft1 = fm1.beginTransaction();
            ft1.replace(R.id.frame1, getSupportFragmentManager().findFragmentByTag("GridFragment"),"GridFragment").commit();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                Toast.makeText(getBaseContext(), "default", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }


    }
}
