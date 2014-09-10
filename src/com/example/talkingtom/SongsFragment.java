package com.example.talkingtom;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.talkingtom.adapters.SongListViewAdapter;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.PlaylistOptions;
import com.example.talkingtom.helpers.Songs;

public class SongsFragment extends Fragment{

	private ListView songListView;
	private Songs mSongsHelper;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_playlist_activity, container, false);
		view = rootView;
		
		initializeVariables();
		
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
