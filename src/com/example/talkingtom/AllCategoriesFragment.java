package com.example.talkingtom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talkingtom.adapters.CollectionPagerAddapter;

public class AllCategoriesFragment extends Fragment{
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private View mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.all_categories_fragment, container, false);
		mView = rootView;
		initializeVariables();
		
		mPager.setAdapter(mPagerAdapter);
		return rootView;
	}
	
	private void initializeVariables(){
		mPager = (ViewPager) mView.findViewById(R.id.viewpager);
		mPagerAdapter = new CollectionPagerAddapter(getActivity().getSupportFragmentManager());
	}
}
