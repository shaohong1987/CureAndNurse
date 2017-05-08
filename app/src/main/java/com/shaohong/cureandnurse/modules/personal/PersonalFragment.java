package com.shaohong.cureandnurse.modules.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaohong.cureandnurse.BaseFragment;
import com.shaohong.cureandnurse.R;

/**
 * Created by shaohong on 2017/5/3.
 */

public class PersonalFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view=inflater.inflate(R.layout.personal,container,false);
        return view;
    }
}
