package com.shaohong.cureandnurse.modules.exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.shaohong.cureandnurse.BaseFragment;
import com.shaohong.cureandnurse.R;
import com.shaohong.cureandnurse.utils.ConstantUtils;

/**
 * Created by shaohong on 2017/4/25.
 */

public class ExamFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.exam,container,false);

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager_exam);
        pager.setAdapter(new examPagerAdapter(getActivity().getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs_exam);
        tabs.setViewPager(pager);
        return view;
    }

    class examPagerAdapter extends FragmentPagerAdapter {

        public examPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ExamOptionFragment examOptionFragment=new ExamOptionFragment();
            examOptionFragment.examType=position;
            return examOptionFragment;
        }

        @Override
        public int getCount() {
            return ConstantUtils.EXAM_OPTIONS().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ConstantUtils.EXAM_OPTIONS().get(position);
        }

    }
}
