package com.shaohong.cureandnurse.activities;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.shaohong.cureandnurse.BaseActivity;
import com.shaohong.cureandnurse.R;
import com.shaohong.cureandnurse.modules.course.CourseFragment;
import com.shaohong.cureandnurse.modules.exam.ExamFragment;
import com.shaohong.cureandnurse.modules.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottomNavigation;
    @BindView(R.id.fl_bottom_navigation)
    FrameLayout flBottomNavigation;


    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private Fragment homeFragment;
    private Fragment courseFragment;
    private Fragment examFragment;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fragmentManager=getSupportFragmentManager();

        bottomSheetBehavior = BottomSheetBehavior.from(flBottomNavigation);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        initNavigation();
        initSideView();
        initView();
    }

    private void initSideView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        homeFragment=new HomeFragment();
        courseFragment=new CourseFragment();
        examFragment=new ExamFragment();

        switchContent(homeFragment);
    }

    private void initNavigation() {
        bottomNavigation
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, getString(R.string.nav_title_home)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, getString(R.string.nav_title_course)).setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_assignment_turned_in_black_24dp, getString(R.string.nav_title_exam)).setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchContent(homeFragment);
                break;
            case 1:
                switchContent(courseFragment);
                break;
            case 2:
                switchContent(examFragment);
                break;
            default:
                break;
        }
    }

    public void switchContent(Fragment to) {
        if (currentFragment != to) {
            if (!to.isAdded()) {
                if (currentFragment != null) {
                    fragmentManager.beginTransaction().hide(currentFragment).commit();
                }
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, to)
                        .commit();
            } else {
                fragmentManager.beginTransaction().hide(currentFragment).show(to).commit();
            }
            currentFragment = to;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
