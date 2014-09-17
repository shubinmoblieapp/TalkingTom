package com.example.talkingtom;

import com.example.talkingtom.helpers.MediaPlayerCustom;

import android.app.Application;

public class MyApplication extends Application {
	public MediaPlayerCustom mediaPlayer = new MediaPlayerCustom();
}
