package com.example.ashraf.movieapp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.ashraf.movieapp.data.Cache.DbAdaptor;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ashraf on 11/4/2016.
 */

public class MyUtil {

    public static boolean ch(Context mContext) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;
        return connected;
    }

    public static final String API_KEY = "5c40fca8dbce44d1d1098a8a024a633e";

    public static boolean time_compare_hours(String time1, String time2) {

        boolean flag = false;

        {
            try {

                String untildate1 = time1;
                String untildate2 = time2;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateFormat.parse(untildate1));

                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");
                Calendar cal2 = Calendar.getInstance();

                cal2.setTime(dateFormat2.parse(untildate2));

                if (cal1.before(cal2)) {
                    flag = true;
                    //system.out.println("date true" + cal1 + " " + cal2);
                }

            } catch (Exception e) {
            }

        }
        return flag;
    }

    public static String time_now() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        String output = sdf.format(c.getTime());
        return output;
    }

    public static String time_addition(String date, int value) {
        String convertedDate = "";
        try {
            String untildate = date;//can take any date in current format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(untildate));
            cal.add(Calendar.MINUTE, value);
            convertedDate = dateFormat.format(cal.getTime());
            //system.out.println("Date increase by one.." + convertedDate);
        } catch (Exception e) {
        }
        return convertedDate;
    }
   public static DbAdaptor dbAdaptor ;
    public static void createDbAdabtor(Context context)
    {
        dbAdaptor = new DbAdaptor(context);
    }
    public static DbAdaptor getDbAdaptor()
    {
        return dbAdaptor ;
    }

}
