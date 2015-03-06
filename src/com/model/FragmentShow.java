package com.model;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public class FragmentShow {

	protected DialogFragment mFragment;
	
	protected Fragment fragment;
	
	public void debugFragmentShow() {
		
		int a=1,d=6;
		int b=0,c=0;
		
		
		for(int i=0;i<10;i++) {
			a=b+c;
			b+=c;
		}
		
	}
	
}
