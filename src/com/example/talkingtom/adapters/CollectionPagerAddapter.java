package com.example.talkingtom.adapters;

import com.example.talkingtom.AllSongsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CollectionPagerAddapter extends FragmentPagerAdapter {

	public CollectionPagerAddapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int item) {
		return new AllSongsFragment();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
