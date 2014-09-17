package com.example.talkingtom.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.talkingtom.MainActivity;
import com.example.talkingtom.MyApplication;
import com.example.talkingtom.R;
import com.example.talkingtom.R.id;
import com.example.talkingtom.R.layout;
import com.example.talkingtom.adapters.SongListViewAdapter;
import com.example.talkingtom.helpers.MediaPlayerCustom;
import com.example.talkingtom.helpers.Songs;

public class SongsFragment extends Fragment{

	private ListView songListView;
	private Songs mSongsHelper;
	private View view;
	private MusicPlayerFragment mMusicPlayer;
	private MediaPlayerCustom mMediaPlayer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_playlist_activity, container, false);
		view = rootView;
		
		initializeVariables();
		
		mMediaPlayer = ((MyApplication)((MainActivity)getActivity()).getApplicationContext()).mediaPlayer;
		Log.d("Test Insert", String.valueOf(mMediaPlayer == null));

		SongListViewAdapter arrayAdapter = new SongListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, mSongsHelper.getAllSongs(), getActivity().getFragmentManager());
		
		if(arrayAdapter != null && songListView != null){
			songListView.setAdapter(arrayAdapter);
		}
		
		return view;
	}
	
	
	private void initializeVariables(){
		songListView = (ListView) view.findViewById(R.id.song_container_listView);
		
		mSongsHelper = new Songs(getActivity());
		
	}

}
