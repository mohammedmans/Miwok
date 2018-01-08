package com.example.mohammed.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager am;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    // sys call backs = methods of states ex: onCreate, onStart, onStop,...
    // can override any of theses call backs

    @Override
    // this is a call back that the sys excute once it has create your activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // when define our arraylist as a final we can reference in clicklistener
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father", "Ap", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("Mother", "Om", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("Daughter", "Bent", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("Son", "Ibn", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("Grand Father", "Ged", R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new Word("Grand Mother", "Geda", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("Older Brother", "Akh Keber", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("Older Sister", "Okht Kebera", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("Younger Brother", "Akh Soghayer", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("Younger Sister", "Okht Soghayara", R.drawable.family_younger_sister, R.raw.family_younger_sister));

        /* view recycler method */

        // Create an {@link ArrayAdapter},
        // whose data source is a list of Strings.
        // The adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter =
                new WordAdapter(this, words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_li st.xml file.

        // search through the view hierarchy for the list view was defined in the XML layout.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        // associating the array adapter with list view
        listView.setAdapter(adapter);

        /* new AdapterView.OnItemClickListener() -> anonymous class and its methods can only reference local vars
        if they declared As a Final
        Or it can reference global in the same class
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if we didn`t declare arraylist final we can`t reference


                // position attribute can get corresponding word object from the arraylist of words
                // check the words array list to get the word that was clicked using position
                // words.get(position) will return a single word var
                // position lead us to the word object within arraylist  and from word object we can retrieve audio res ID
                // and which word object click
                Word word = words.get(position);

                // Release the media player if the currently exists because we are about to play a different file
                // release the media player res before the media player is initialized  to play another audio file
                // ex: if user click on different songs and app has not enough time to finish the prev song and play new
                // so the app should stop the old and play a new file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                // Request audio focus for playback
                int result = am.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Now we have Audio Focus
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());

                    mMediaPlayer.start();

                    // Async callbacks
                    // Doing something while waiting another something to finish

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                            // release the res after the file has finished playing
                            releaseMediaPlayer();

                            //Toast.makeText(FamilyActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });




        /* Linear Layout method

        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        for (int i = 0; i < words.size(); ++i) {

            // create new TextView
            TextView wordView = new TextView(this);
            // add text to TextView
            wordView.setText(words.get(i));
            // add textView to layout
            rootView.addView(wordView);
        }
        */

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /*
         * Clean Up the media player by releasing its resources
         */
    private void releaseMediaPlayer() {
        // if the media player is not null, then it may be currently palying audio file
        if (mMediaPlayer != null) {
            // Regardless of the current state of the  media player, release its resources
            // because we no longer need it
            mMediaPlayer.release();

            // Set the media player back to null. For our code we decided that.
            // Setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

}
