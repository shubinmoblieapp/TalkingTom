package com.example.talkingtom.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.talkingtom.R;
import com.example.talkingtom.adapters.ArtistsListViewAdapter;
import com.example.talkingtom.helpers.Songs;

public class ArtistsFragment extends Fragment{

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
		
		ArtistsListViewAdapter arrayAdapter = new ArtistsListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, mSongsHelper.getAllArtists(), getActivity().getFragmentManager());
		
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
