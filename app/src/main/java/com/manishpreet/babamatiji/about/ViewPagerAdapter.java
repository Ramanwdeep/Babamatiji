package com.manishpreet.babamatiji.about;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i)
        {
            case 0:
            {
                return GalleryFragment.getInstance();
            }

            case 1:
            {
                return LocationFragment.getInstance();
            }

            case 2:
            {
                return HistoryFragment.getInstance();
            }
            case 3:
            {
                return ContactsFragment.getInstance();
            }


            default:
            {
                return null;
            }
        }



    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
            {
                return "Gallery";
            }

            case 1:
            {
                return "Address";
            }
            case 2:
            {
                return "History";
            }

            case 3:
            {
                return "Contact Us";
            }
            default:
            {
                return null;
            }
        }
    }
}
