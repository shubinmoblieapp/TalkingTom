package com.example.talkingtom.fragments;

import com.example.talkingtom.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AvailablePlaylistsFragment extends Fragment {

	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_playlist_activity, container, false);
		mView = rootView;
		
		
		return mView;
	}
}
