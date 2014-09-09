package com.example.talkingtom.concur;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.talkingtom.helpers.MediaPlayerCustom;

public class SeekBarProgress implements Runnable {
	
	private SeekBar mSeekBar;
	private MediaPlayerCustom mMediaPlayer;
	private volatile boolean running;
	private volatile boolean isPlaying;
	private int mSetProgress;
	
	public SeekBarProgress(SeekBar seekBar, MediaPlayerCustom mediaPlayer){
		mSeekBar = seekBar;
		mMediaPlayer = mediaPlayer;
		running = false;
		isPlaying = true;
	}

    public void terminate() {
    	Log.d("Test Insert", "Thread Treminated!");
        running = false;
    }
    
    public void start(){
		running = true;
    }
    
    public boolean isRunning(){
    	return running;
    }
    
    public void pausePlaying(){
    	isPlaying = false;
    }
	
    public void resumePlaying(){
    	isPlaying = true;
    }
    
    public boolean isPlaying(){
    	return isPlaying;
    }
    
	@Override
	public void run() {
		
		while (running) {
	    	
	    	if(isPlaying){
	    		mSetProgress = mMediaPlayer.getCurrentPosition();
	    		Log.d("Test Insert", String.valueOf(mSetProgress));
	    		mSeekBar.setMax(mMediaPlayer.getDuration());
		    	mSeekBar.setProgress(mSetProgress);
	    	}
	    	
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			    running = false;
	            return;
			}
	        
	        if(running == false){
	            return;
			}
        }
	}

}
