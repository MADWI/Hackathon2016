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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;


import butterknife.Bind;
import butterknife.ButterKnife;
import pl.edu.zut.mad.hackathon2016.ChooseOrliksLocation;
import pl.edu.zut.mad.hackathon2016.DataProvider;
import pl.edu.zut.mad.hackathon2016.OrliksListFragment;
import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.SaveManager;
import pl.edu.zut.mad.hackathon2016.SearchHelper;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_SELECTED_TAB = "S_TAB";

    private FragmentManager fragmentManager;

    @Bind(R.id.pager)
    ViewPager mPager;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Bind(R.id.tabs_wrapper)
    View mTabsWrapper;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    SearchHelper searchHelper;

    private int mCurrentTab = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentTab = savedInstanceState.getInt(STATE_SELECTED_TAB, -1);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        checkLocationChoose();
    }

    public void checkLocationChoose() {
        SaveManager saveManager = new SaveManager(this);

        Fragment oldChooseLocationFragment = fragmentManager.findFragmentByTag(ChooseOrliksLocation.TAG);
        if (!saveManager.isLocalizationChoose()) {
            if (oldChooseLocationFragment == null) {
                ChooseOrliksLocation chooseOrliksLocation = new ChooseOrliksLocation();
                fragmentManager.beginTransaction()
                        .add(R.id.main_activity_container, chooseOrliksLocation, ChooseOrliksLocation.TAG)
                        .commit();
            }

            mTabsWrapper.setVisibility(View.GONE);
        } else {
            if (oldChooseLocationFragment != null) {
                fragmentManager.beginTransaction().remove(oldChooseLocationFragment).commit();
            }

            mPager.setAdapter(new TabsAdapter(fragmentManager));
            mTabLayout.setupWithViewPager(mPager);
            mTabsWrapper.setVisibility(View.VISIBLE);
            if (mCurrentTab < 0) {
                mCurrentTab = DataProvider.getFavouritesOrliks().size() == 0 ? 2 : 1;
            }
            mPager.setCurrentItem(mCurrentTab, false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTabsWrapper.getVisibility() == View.VISIBLE) {
            outState.putInt(STATE_SELECTED_TAB, mPager.getCurrentItem());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        searchHelper = new SearchHelper();
        searchHelper.setSearchView(this, menu);

        return true;
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
