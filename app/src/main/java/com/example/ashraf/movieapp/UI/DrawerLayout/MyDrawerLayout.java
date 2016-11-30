package com.example.ashraf.movieapp.UI.DrawerLayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by ashraf on 11/24/2016.
 */
//https://www.google.com.eg/search?q=DrawerLayout+must+be+measured+with+MeasureSpec.EXACTLY.&oq=DrawerLayout+must+be+measured+with+MeasureSpec.EXACTLY.&aqs=chrome..69i57j0l5.542j0j7&sourceid=chrome&ie=UTF-8
//http://stackoverflow.com/questions/32515527/illegal-argument-exception-drawerlayout-must-be-measured-with-measurespec-exac
public class MyDrawerLayout extends DrawerLayout {

    public MyDrawerLayout(Context context) {
        super(context);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}