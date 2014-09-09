package com.example.talkingtom;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.talkingtom.concur.SeekBarProgress;
import com.example.talkingtom.helpers.MediaPlayerCustom;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.PlaylistOptions;

public class MusicPlayerFragment extends Fragment {
	private Button mPlayPauseSongButton;
	private Button mNextSongButton;
	private Button mPreviousSongButton;
	private SeekBar mSeekBar;
	
	private PlaylistOptions playlistOptions;
	private MediaPlayerCustom mMediaPlayer;
	
	private List<Mp3Helper> mp3List;
	private int mSeekBarProgress;
	private SeekBarProgress seekBarRunnable;
	private View mView;
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
         mView = inflater.inflate(R.layout.media_player_fragment, container, false);
         
        initializeVariables();
		
		//TODO Make a listview with all of the playlists available 
		
		mPlayPauseSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMediaPlayer.playPause(mPlayPauseSongButton, getResources());
			}
		});
	
		mNextSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMediaPlayer.nextSong();
				mSeekBar.setProgress(0);
			}
		} );
		
		mPreviousSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMediaPlayer.previousSong();
				mSeekBar.setProgress(0);
			}
		});
		
		
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				mMediaPlayer.seekTo(mSeekBarProgress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mSeekBarProgress = progress;
			}
		});
        return mView;
    }
	
	private void initializeVariables(){
		mPlayPauseSongButton = (Button) mView.findViewById(R.id.play_button);
		mNextSongButton = (Button) mView.findViewById(R.id.forward_button);
		mPreviousSongButton = (Button) mView.findViewById(R.id.backward_button);
		mSeekBar = (SeekBar) mView.findViewById(R.id.player_seekbar);
		
		playlistOptions = new PlaylistOptions(getActivity().getContentResolver());
		
		mSeekBarProgress = 0;
		mp3List = new ArrayList<Mp3Helper>();
		mp3List = playlistOptions.getPlaylist("relax");
		
		mMediaPlayer = new MediaPlayerCustom(mp3List, getActivity());
		
		seekBarRunnable = new SeekBarProgress(mSeekBar, mMediaPlayer);
		mMediaPlayer.setSeekBarRunnable(seekBarRunnable);
	}
}
