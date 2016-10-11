package com.example.cant.goingtoplace;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
    private int currentPosition = 0;
    private String[] title;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ActionBar actionBar;
    private Integer[] imgid={
            R.drawable.icon1,
            R.drawable.icon2,
            R.drawable.icon3,
            R.drawable.icon4,
    };

    private  class listener implements  ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // do something
            selectItem(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //tieu de cac item trong menu
        title = getResources().getStringArray(R.array.title);
        //tham chieu toi danh sach cua ngan keo
        drawerList = (ListView)findViewById(R.id.drawer);
        //tham chieu toi main_layout
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);

        CustomListAdapter adapter = new CustomListAdapter(this, title, imgid);
        drawerList.setAdapter(adapter);

//        list.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String Slecteditem= itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
//
//
//        drawerList.setAdapter(new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_activated_1,
//                title));

        drawerList.setOnItemClickListener(new listener());
        // xu ly khi xay ra configuration change
        if(savedInstanceState != null){
            selectItem(savedInstanceState.getInt("position"));
        }
        else {
            selectItem(0);
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                // re-create menu items ( makes them invisible)
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                //re-create menu items ( makes them visible)
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

    }
    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putInt("position", currentPosition);
    }

    @Override
// Ngay sau khi activity duoc tao ra, ta muon icon cua drawerToggle dien ta dung trang thai
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.share_action_provider);
        ShareActionProvider shareActionProvider= (ShareActionProvider)menuItem.getActionProvider();
        // thiet lap intent cua ShareActionProvider
        Intent intentShare= new Intent();
        intentShare.setAction(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT, "Hello share share share share");
        shareActionProvider.setShareIntent(intentShare);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen= drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_create_order).setVisible(!drawerOpen);
        menu.findItem(R.id.action_search_web).setVisible(!drawerOpen);
        menu.findItem(R.id.share_action_provider).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    // xu li khi cac action bar item duoc click
    public boolean onOptionsItemSelected(MenuItem item){
        //Neu item tren action bar la drawerToggle thi kemeno
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        //Neu item tren action bar khong phai drawerToggle thi xu li tuy theo item tuong ung
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_create_order:
                intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search_web:
                intent = new Intent().setAction(Intent.ACTION_WEB_SEARCH);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void selectItem(int position){
        currentPosition = position;
        Fragment fragment;
        switch (position){
            case 1 : fragment = new PizzaFragment();
                break;
            case 2 : fragment = new PastaFragment();
                break;
            case 3 : fragment = new StoreFragment();
                break;
            default: fragment = new TopFragment();
        }
        // listener cua 2 su kien: khi them fragment transaction vao back stack va khi nguoi dung an nut back
        // cung co nghia la khi fragment transaction duoc them vao hoac lay ra tu back stack
       FragmentManager.OnBackStackChangedListener l = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragM = getFragmentManager();
                Fragment fragment= fragM.findFragmentByTag("visible_fragment");
                if(fragment instanceof TopFragment){
                    currentPosition = 0;
                }
                if(fragment instanceof PizzaFragment){
                    currentPosition = 1;
                }
                if(fragment instanceof PastaFragment){
                    currentPosition = 2;
                }
                if(fragment instanceof StoreFragment){
                    currentPosition = 2;
                }
                setActionBarTittle(currentPosition);
                drawerList.setItemChecked(currentPosition, true);
            }
        };
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(ft.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        getFragmentManager().addOnBackStackChangedListener(l);
        drawerLayout.closeDrawer(drawerList);
        setActionBarTittle(position);

    }

    private void setActionBarTittle(int position){
        String tit;
        if(position == 0)
            tit = getResources().getString(R.string.app_name);
        else {
            tit = title[position];
        }
        if(tit != null) {
            getActionBar().setTitle(tit);
        }
    }
}