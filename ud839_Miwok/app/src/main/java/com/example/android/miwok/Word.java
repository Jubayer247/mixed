package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageResourceID=-1;
    private int audioResourceId;

    public Word(String defaultTranslation,String miwokTranslation, int imageResourceID,int audioResourceId){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        this.imageResourceID=imageResourceID;
        this.audioResourceId=audioResourceId;
    }

    public Word(String defaultTranslation,String miwokTranslation,int audioResourceId){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        this.audioResourceId=audioResourceId;
    }


    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public int getAudioResourceId(){ return audioResourceId; }

    public boolean hasImage(){
        return imageResourceID!=-1;
    }
}
