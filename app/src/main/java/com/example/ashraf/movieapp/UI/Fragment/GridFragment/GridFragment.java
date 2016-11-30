package com.example.ashraf.movieapp.UI.Fragment.GridFragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.ashraf.movieapp.UI.Adaptor.CustomAdaptor;
import com.example.ashraf.movieapp.UI.DrawerLayout.MyDrawerLayout;
import com.example.ashraf.movieapp.R;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.gridview)
    public
    GridView gridView;
    @BindView(R.id.load_dialog)
    public
    ProgressBar load_sign;
    @BindView(R.id.navigation_view)
    public
    NavigationView navigationView;
    @BindView(R.id.drawer_our_main)
    public
    MyDrawerLayout mDrawerLayout;
    @BindView(R.id.fab)
    public
    FloatingActionButton floatingActionButton;
    public CustomAdaptor customAdaptor;
    View rootView;
    GridFragmentPresenter gridFragmentPresenter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_grid, container, false);
        ButterKnife.bind(this, rootView);
        gridFragmentPresenter=new GridFragmentPresenterImpl(this);
        gridFragmentPresenter.onCreateView();
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        gridFragmentPresenter.oStart();
    }
    @Override
    public void onResume() {
//        https://www.google.com.eg/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=how+to+make+back+button+affect+activity+not+fragment
//        http://stackoverflow.com/questions/7992216/android-fragment-handle-back-button-press
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
    }

}
