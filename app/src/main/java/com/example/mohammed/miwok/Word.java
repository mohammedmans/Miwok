package com.example.mohammed.miwok;

/**
 * Created by M.M.M.M on 5/25/2017.
 */

public class Word {

    /* Default translation for the word */
    private String mDefaultTranslation;

    /* Miwok translation for the word */
    private String mMiwokTranslation;


    /**
     * this for using word adapter for all category with or no image
     */
    private static final int NO_IMAGE_PROVIDED = -1 ;

    // assume we haven`t image
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    private int mAudioResourceId;



    // note the phrases category not include image only only two text views
    // so create a new constructor with two text view and image

    /* this constructor for phrases category */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
    }
    /* this constructor for rest category */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId=mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;

    }


    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage(){
        // assume we have value for imageresource in this case have image and return true
        // else return flase
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}
