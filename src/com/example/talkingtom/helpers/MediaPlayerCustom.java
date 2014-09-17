package com.example.talkingtom.helpers;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.talkingtom.R;
import com.example.talkingtom.concur.SeekBarProgress;

public class MediaPlayerCustom extends MediaPlayer{

	private List<Mp3Helper> mMp3List;
	private int mSongCounter;
	private SeekBarProgress mSeekBarRunnable;
	private Thread mSeekBarThread;
	private boolean isSeekBarThreadSet = false;
	
	public MediaPlayerCustom(){
		mSongCounter = 0;
	}
	
	public MediaPlayerCustom(List<Mp3Helper> mp3List, Context context){
		mMp3List = mp3List;
		setMediaPlayerSource(context);
		mSongCounter = 0;
	}
	
	
	public void setSongList(List<Mp3Helper> mp3List, Context context){
		mMp3List = mp3List;
		setMediaPlayerSource(context);
	}
	
	public void setNewSongList(List<Mp3Helper> songList){
		mMp3List = songList;
	}

	
	public void playPause(Button button, Resources resource){
		
		Drawable resourcePlayButton =  resource.getDrawable(R.drawable.button_play);
		Drawable resourcePauseButton = resource.getDrawable(R.drawable.button_pause);
		
		if(isPlaying() && mSeekBarRunnable.isPlaying()){
			pause();
			mSeekBarRunnable.pausePlaying();
			
			button.setBackground(resourcePlayButton);
		}else{
			startSong();
			button.setBackground(resourcePauseButton);
		}
	}
	
	public List<Mp3Helper> getCurrentPlaylist(){
		return mMp3List;
	}
	
	public Mp3Helper getCurrentSong(){
		return mMp3List.get(mSongCounter);
	}
	
	
	public void nextSong(){
		mSongCounter++;
		
		if(mSongCounter == mMp3List.size()){
			mSongCounter = 0;
		}
		
		if(isPlaying()){
			changeAndStartSong();
		}else{
			changeSong();
		}
	}
	
	public void nextSongAuto(){
		mSongCounter++;
		
		if(mSongCounter == mMp3List.size()){
			mSongCounter = 0;
		}
		
		changeAndStartSong();
	}
	
	public void previousSong(){
		mSongCounter--;
		
		if(mSongCounter < 0){
			mSongCounter = mMp3List.size() - 1;
		}
		
		if(isPlaying()){
			changeAndStartSong();
		}else{
			changeSong();
		}
	}
	
	public void setSeekBarRunnable(SeekBarProgress seekBarRunnable){
		mSeekBarRunnable = seekBarRunnable;
		mSeekBarThread = new Thread(mSeekBarRunnable);
		
		if(mSeekBarThread != null && mSeekBarRunnable != null){
			isSeekBarThreadSet = true;
		}
	}
	
	public void startSong(String filePath){
		stop();
		reset();
		
		try {
			
			setDataSource(filePath);
			prepare();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(mSeekBarRunnable.isRunning() && isSeekBarThreadSet){
			if(!mSeekBarRunnable.isPlaying()){
				mSeekBarRunnable.resumePlaying();
			}
		}else if(isSeekBarThreadSet){
			mSeekBarThread = new Thread(mSeekBarRunnable);
			mSeekBarRunnable.start();
			mSeekBarRunnable.resumePlaying();
			mSeekBarThread.start();
		}
		
		start();
	}
	
	//-------------------------------Private Methods------------------------------------\\
	private void startSong(){
		try {
			setDataSource(mMp3List.get(mSongCounter).getFilePath());
			prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(mSeekBarRunnable.isRunning() && isSeekBarThreadSet){
			if(!mSeekBarRunnable.isPlaying()){
				mSeekBarRunnable.resumePlaying();
			}
		}else if(isSeekBarThreadSet){
			mSeekBarThread = new Thread(mSeekBarRunnable);
			mSeekBarRunnable.start();
			mSeekBarRunnable.resumePlaying();
			mSeekBarThread.start();
		}
		
		start();
	}
	
	
	private void changeAndStartSong(){
		stop();
		reset();
		
		try {
			
			setDataSource(mMp3List.get(mSongCounter).getFilePath());
			prepare();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isSeekBarThreadSet){
			mSeekBarRunnable.terminate();
			mSeekBarThread = new Thread(mSeekBarRunnable);
			mSeekBarRunnable.start();
			mSeekBarThread.start();
		}
		
		start();
	}
	
	private void changeSong(){
		
		try {
			
			reset();
			setDataSource(mMp3List.get(mSongCounter).getFilePath());
			prepare();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(mSeekBarRunnable.isRunning() == true && isSeekBarThreadSet == true){
			mSeekBarRunnable.terminate();
		}
	}
	

	private void setMediaPlayerSource(Context context){
		Uri uri = Uri.parse(mMp3List.get(0).getFilePath());
		super.create(context, uri);
	}
}
