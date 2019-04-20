package com.example.regischarles.sometodo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Todo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {




    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    DatePicker datePicker;
    SessionManage sessionManage;
    RecyclerView recyclerView;
    ArrayList<Task> getAllTask;
    RecyclerViewAdapterMain recyclerViewAdapterMain;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManage= new SessionManage(getApplicationContext());



        FloatingActionButton fab =  findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                DailogFragment dailogFragment=new DailogFragment(getApplicationContext(),sessionManage);
                dailogFragment.show(fragmentManager,"dialog");
                Toast.makeText(getApplicationContext(),"hello ",Toast.LENGTH_LONG).show();

                Snackbar.make(view, "Adding a new task...please wait", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
       int  month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        boolean status=sessionManage.isLogedIn();
        Log.v("TODO","status "+status);
        drawerLayout=findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_navigation, R.string.close_navigation);



        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView=findViewById(R.id.navView);
       navigationView.setNavigationItemSelectedListener(this);
        mViewPager = findViewById(R.id.container);

        setupViewPager(mViewPager);
        tabLayout=findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch(id){

           case R.id.logout:
               Toast.makeText(getApplicationContext(),"logout",Toast.LENGTH_LONG).show();
               break;
           case R.id.profile:
               Toast.makeText(getApplicationContext(),"profile",Toast.LENGTH_LONG).show();
               break;
           case R.id.report:
               Toast.makeText(getApplicationContext(),"report",Toast.LENGTH_LONG).show();
               break;
       }

        return super.onOptionsItemSelected(item);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new todoFrag(this), "TO Do ");
        adapter.addFragment(new finisedFrag(this), "Finised");

        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.logout:
                sessionManage.Logout();


            }
        DrawerLayout drawer =  findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

return true;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}




