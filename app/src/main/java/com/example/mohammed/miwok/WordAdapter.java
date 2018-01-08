package com.example.mohammed.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by M.M.M.M on 5/27/2017.
 */

/* array adapter instructions */
/* may be won`t work with the phrases activity because there is no image in phrase just 2 textviews */
/* so create phrase adapter wiht 2 text view */
/* OR using word adapter once with image when need image and once without image  -> hide image view */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceId) {
        super(context, 0, words);
        this.mColorResourceId=ColorResourceId;
    }

    @Override
    // existing views that can be re-use
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        // no views to reuse
        if (listItemView == null) {
            // inflate -> meaning create new list item from this XML resource list_item
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            // parent view = list view
            // false because we don`t want to attach the list item to the parent
        }

        // Get the {@link Word} object located at this position in the list
        // getItem method in ArrayAdapter super class
        Word currentWord = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        /**
         * background color for each category
         */

        /* can't specify background color for each category in activity because we have only one activity and
        the background color change depend on the category */


        View textContainer = listItemView.findViewById(R.id.text_container);
        // find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        // set the back ground of the text conatiner view
        textContainer.setBackgroundColor(color);



        /* ana me4 ba7od el 3alama bta3t el play 3ashan deh me4 bett8ayer f ba3mlha fe el activity */
        /* we kaman me4 ba3mal 7aga 5asa b audio hena 3a4an me4 ba4of el audio */




        // Return the whole list item layout (containing 2 TextViews and an ImageView)


        // so that it can be shown in the ListView
        return listItemView;
    }
}
