package com.vjs.complaints.ui.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.vjs.complaints.ComplainStatus;
import com.vjs.complaints.LoadComplains;
import com.vjs.complaints.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_2, R.string.tab_text_3};

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoadComplains();
            case 1:
                return new ComplainStatus();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "LOAD";
            case 1:
                return "STATUS";
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}