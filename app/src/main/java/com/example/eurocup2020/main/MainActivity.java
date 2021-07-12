package com.example.eurocup2020.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.eurocup2020.main.Fragments.BetFragment;
import com.example.eurocup2020.main.Fragments.FriendFragment;
import com.example.eurocup2020.main.Fragments.StartFragment;
import com.example.eurocup2020.main.Fragments.TableFragment;
import com.google.android.material.tabs.TabLayout;

import com.example.eurocup2020.R;
import com.example.eurocup2020.log_reg.SessionManager;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity{

    public SessionManager sessionManager;
    String sessionName1;

    private ShareActionProvider shareActionProvider; //do ikony udostepniania

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //sprawdzamy czy zalogowany
        sessionManager= new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        sessionName1=user.get(sessionManager.NAME);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dolaczamy adapter do kontrolki viewpager
        SectionsPagerAdapter pagerAdapter= new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager= findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //dolaczamy viewpager do tablayout
        TabLayout tabLayout= findViewById(R.id.tabs);
        tabLayout.setBackground(new ColorDrawable(Color.TRANSPARENT));
        tabLayout.setupWithViewPager(pager);
    }

    @Override //metoda do ikony udostepniania na pasku narzedzi
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem=menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent();
        return super.onCreateOptionsMenu(menu);
    }

    @Override //ta metoda jest wywolywana po klikniecie na ikone opcje z paska aplikacji
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.opcjeIcona) {
            Intent intent = new Intent(this, Options.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //do ikony udostepniania na pasku narzedzi
    private void setShareActionIntent(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.play1)+"\n"+getString(R.string.play2)+"\n"+getString(R.string.play3)+" "+sessionName1 );
        shareActionProvider.setShareIntent(intent);
    }

    //adapter przekazuje informacje kontrolce viewpager
    private class SectionsPagerAdapter extends FragmentPagerAdapter {


        SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        //sluzy do przesuwania sie po kartach
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new StartFragment();
                case 1:
                    return new TableFragment();
                case 2:
                    return new BetFragment();
                case 3:
                    return new FriendFragment();
            }
            return null;
        }

        //zwraca liczbe "kart"
        @Override
        public int getCount() {
            return 4;
        }

        //ustawia tytul kart
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.start_tab);
                case 1:
                    return getResources().getText(R.string.standings_tab);
                case 2:
                    return getResources().getText(R.string.bet_tab);
                case 3:
                    return getResources().getText(R.string.friends_tab);
            }
            return null;
        }
    }



}
