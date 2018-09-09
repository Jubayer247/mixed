package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorResourceId;


    public WordAdapter(Activity context,ArrayList<Word> word,int colorResourceId) {
        super(context,0,word);
        this.colorResourceId=colorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word word = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.textView);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(word.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.textView2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(word.getMiwokTranslation());

        if (word.hasImage()) {
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

            imageView.setImageResource(word.getImageResourceID());

        }

        else {
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);
            imageView.setVisibility(View.GONE);
        }

        int color= ContextCompat.getColor(getContext(),colorResourceId);
        View textContainer=listItemView.findViewById(R.id.textContainer);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }


}
