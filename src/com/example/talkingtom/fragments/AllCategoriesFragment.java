package com.example.talkingtom.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talkingtom.R;
import com.example.talkingtom.R.id;
import com.example.talkingtom.R.layout;
import com.example.talkingtom.adapters.CollectionPagerAddapter;
import com.example.talkingtom.utils.ArtistsFragment;

public class AllCategoriesFragment extends Fragment{
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private View mView;
	private List<Fragment> mFragmentList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.all_categories_fragment, container, false);
		mView = rootView;
		initializeVariables();
		
		mPager.setAdapter(mPagerAdapter);
		Log.d("Test Insert", String.valueOf(mPager == null));
		return rootView;
	}
	
	private void initializeVariables(){
		mPager = (ViewPager) mView.findViewById(R.id.viewpager);
		mFragmentList = new ArrayList<Fragment>();
		listOfFragments();
		mPagerAdapter = new CollectionPagerAddapter(getActivity().getSupportFragmentManager(), mFragmentList);
	}
	
	private void listOfFragments(){
		mFragmentList.add(new SongsFragment());
		mFragmentList.add(new AlbumsFragment());
		mFragmentList.add(new ArtistsFragment());
		mFragmentList.add(new GenresFragment());
	}
}
