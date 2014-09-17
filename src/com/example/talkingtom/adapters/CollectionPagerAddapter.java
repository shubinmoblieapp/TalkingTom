package com.example.talkingtom.adapters;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.talkingtom.fragments.AlbumsFragment;

public class CollectionPagerAddapter extends FragmentPagerAdapter {

	private List<Fragment> mListOfFragments;
	
	public CollectionPagerAddapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		mListOfFragments = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return mListOfFragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListOfFragments.size();
	}

}
