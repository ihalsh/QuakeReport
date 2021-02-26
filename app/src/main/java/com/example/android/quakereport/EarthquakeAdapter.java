package com.example.android.quakereport;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.databinding.EarthquakeItemBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull EarthquakeActivity context, @NonNull List<Earthquake> objects) {
            super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item, parent, false);
        }

        // Get the object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        DecimalFormat formatter = new DecimalFormat("0.0");
        double magnitude = currentEarthquake.getMagnitude();

        // Find the TextView in the list_item.xml layout with the ID magnitude_text_view
        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude);
        // set this text
        magnitudeTextView.setText(formatter.format(magnitude));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(ContextCompat.getColor(getContext(), getMagnitudeColor(magnitude)));

        // Find the TextView in the list_item.xml layout with the ID city_text_view
        TextView distanceTextView = listItemView.findViewById(R.id.location_offset);
        TextView placeTextView = listItemView.findViewById(R.id.primary_location);

        // set this text
        String dp = currentEarthquake.getCityLocation();
        if (dp.contains("km ")) {
            distanceTextView.setText(dp.substring(0, dp.lastIndexOf(" of")+4));
            placeTextView.setText(dp.substring(dp.lastIndexOf(" of")+4));
        } else {
            distanceTextView.setText("Near the");
            placeTextView.setText(dp);
        }

        // Find the TextView in the list_item.xml layout with the ID time_text_view
        TextView dateTextView = listItemView.findViewById(R.id.date);
        TextView timeTextView = listItemView.findViewById(R.id.time);
        // set this text

        String date = new SimpleDateFormat("MMM dd, yyyy")
                .format(new Date(currentEarthquake.getTime()));
        String time = new SimpleDateFormat("HH:mm a")
                .format(new Date(currentEarthquake.getTime()));

        dateTextView.setText(date);
        timeTextView.setText(time);

        return listItemView;
    }
    private int getMagnitudeColor(double magnitude) {

        int magnitudeColor = R.color.magnitude1;

        int iMagnitude = (int) magnitude;

        switch (iMagnitude) {
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            case 10:
                return R.color.magnitude10plus;
        }
        return magnitudeColor;
    }
}
