package com.example.talkingtom;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.talkingtom.adapters.AlbumsListViewAdapter;
import com.example.talkingtom.adapters.SongListViewAdapter;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.Songs;

public class AlbumsFragment extends Fragment{

	private ListView songListView;
	private Mp3Helper mMp3;
	private Songs mSongsHelper;
	private List<String> listOf;
	private EditText mPlaylistNameEditText;
	private Context mContext;
	private View view;
	private Songs mSongs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_playlist_activity, container, false);
		view = rootView;
		
		initializeVariables();
		
		AlbumsListViewAdapter arrayAdapter = new AlbumsListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, mSongs.getAllAlbums(), getActivity().getFragmentManager());
		
//		for(String name : mSongsHelper.getAllAlbums()){
//			Log.d("Test Insert", name);
//		}
		
		if(arrayAdapter != null && songListView != null){
			songListView.setAdapter(arrayAdapter);
		}
		
		
		return view;
	}
	
	
	private void initializeVariables(){
		songListView = (ListView) view.findViewById(R.id.song_container_listView);
		
		mContext = getActivity();
		
		mSongsHelper = new Songs(getActivity());
		mSongs = new Songs(getActivity());
	}

}