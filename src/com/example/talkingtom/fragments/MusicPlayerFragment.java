package com.example.talkingtom.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.talkingtom.MainActivity;
import com.example.talkingtom.MyApplication;
import com.example.talkingtom.R;
import com.example.talkingtom.adapters.QueueListViewAdapter;
import com.example.talkingtom.adapters.SongListViewAdapter;
import com.example.talkingtom.concur.SeekBarProgress;
import com.example.talkingtom.helpers.MediaPlayerCustom;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.PlaylistOptions;
import com.example.talkingtom.interfaces.IUpdateMediaPlayerUI;

public class MusicPlayerFragment extends Fragment implements IUpdateMediaPlayerUI {
	private Button mPlayPauseSongButton;
	private Button mNextSongButton;
	private Button mPreviousSongButton;
	private SeekBar mSeekBar;
	private TextView mSongNameTextView;
	private View mView;
	private SeekBarProgress seekBarRunnable;
	private ListView mSongsListView;
	
	private PlaylistOptions playlistOptions;
	private MediaPlayerCustom mMediaPlayer;
	private List<Mp3Helper> mp3List;	
	private QueueListViewAdapter mSongListViewAdapter;
	private String mSongNameAndComposer;
	private int mSeekBarProgress;
	


	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
         mView = inflater.inflate(R.layout.media_player_fragment, container, false);
         
        initializeVariables();
        
        SongListViewAdapter.updateView = this;
        QueueListViewAdapter.updateView = this;
        
		//TODO Make a listview with all of the playlists available 

        mSongsListView.setAdapter(mSongListViewAdapter);
        
        mSongNameAndComposer  =  mMediaPlayer.getCurrentSong().getAuthor() + " - " + mMediaPlayer.getCurrentSong().getTitle();
        mSongNameTextView.setText(mSongNameAndComposer);
        
		mPlayPauseSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMediaPlayer.playPause(mPlayPauseSongButton, getResources());
			}
		});
	
		mNextSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextSongButtonClick();
			}
		} );
		
		mPreviousSongButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previosSongButtonClick();
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
		
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mediaPlayerOnCompletion();
			}
		});
		
        return mView;
    }
	
	public MediaPlayerCustom getMediaPlayer(){
		
		return mMediaPlayer;
	}
	
	private void nextSongButtonClick(){
		mMediaPlayer.nextSong();
		mSeekBar.setProgress(0);
	}
	
	private void previosSongButtonClick(){
		mMediaPlayer.previousSong();
		mSeekBar.setProgress(0);
	}
	
	private void mediaPlayerOnCompletion(){
		mMediaPlayer.nextSongAuto();
	}
	
	private void initializeVariables(){
		mPlayPauseSongButton = (Button) mView.findViewById(R.id.play_button);
		mNextSongButton = (Button) mView.findViewById(R.id.forward_button);
		mPreviousSongButton = (Button) mView.findViewById(R.id.backward_button);
		mSeekBar = (SeekBar) mView.findViewById(R.id.player_seekbar);
		mSongsListView = (ListView) mView.findViewById(R.id.queue_lsitview);
		
		playlistOptions = new PlaylistOptions(getActivity().getContentResolver());
		
		mSeekBarProgress = 0;
		mp3List = new ArrayList<Mp3Helper>();
		mp3List = playlistOptions.getPlaylist("relax");
		
		mMediaPlayer = ((MyApplication)((MainActivity)getActivity()).getApplicationContext()).mediaPlayer;
		mMediaPlayer.setSongList(mp3List, getActivity());
		
		seekBarRunnable = new SeekBarProgress(mSeekBar, mMediaPlayer);
		mMediaPlayer.setSeekBarRunnable(seekBarRunnable);
		
		mSongNameTextView = (TextView) mView.findViewById(R.id.song_title_textview);
		
		mSongListViewAdapter = new QueueListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, mp3List);
	}

	@Override
	public void updateView(String title, int position, List<Mp3Helper> songList) {
		mSongNameTextView.setText(title);
		mSongsListView.setAdapter(new QueueListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, songList));
		mPlayPauseSongButton.setBackground(getResources().getDrawable(R.drawable.button_pause));
	}

	@Override
	public void updateTitle(String title) {
		mSongNameTextView.setText(title);
		mPlayPauseSongButton.setBackground(getResources().getDrawable(R.drawable.button_pause));
	}
}
