package pl.edu.zut.mad.hackathon2016.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

import pl.edu.zut.mad.hackathon2016.ChooseOrliksLocation;
import pl.edu.zut.mad.hackathon2016.OrliksListFragment;
import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.SaveManager;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.pager)
    ViewPager mPager;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Bind(R.id.tabs_wrapper)
    View mTabsWrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SaveManager saveManager = new SaveManager(this);

        if (!saveManager.isLocalizationChoose()) {
            ChooseOrliksLocation chooseOrliksLocation = new ChooseOrliksLocation();
            fragmentManager.beginTransaction()
                .add(R.id.main_activity_container, chooseOrliksLocation, ChooseOrliksLocation.TAG)
                .commit();

            mTabsWrapper.setVisibility(View.GONE);
        } else {
            Fragment oldChooseLocationFragment = fragmentManager.findFragmentByTag(ChooseOrliksLocation.TAG);
            if (oldChooseLocationFragment != null) {
                fragmentManager.beginTransaction().remove(oldChooseLocationFragment).commit();
            }

            mPager.setAdapter(new TabsAdapter(fragmentManager));
            mTabLayout.setupWithViewPager(mPager);
            mTabsWrapper.setVisibility(View.VISIBLE);
        }
    }

    private class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) return getString(R.string.tab_my_reservations);
            if (position == 1) return getString(R.string.tab_favorites);
            if (position == 2) return getString(R.string.tab_all);
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt(OrliksListFragment.ARG_MODE, position + 1);
            OrliksListFragment fragment = new OrliksListFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
