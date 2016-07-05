package cemayan.com.matbil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class Anasayfa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_anasayfa);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,new AnasayfaFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }




    private boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {




        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Çıkmak için bir kere daha basınız.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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

        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.duyurular) {

        fragmentManager.beginTransaction().replace(R.id.content_frame, new DuyurularFragment()).commit();

            setTitle("Duyurular");
        }
        else if (id == R.id.anasayfa) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AnasayfaFragment()).commit();

            setTitle("Anasayfa");
        }
        else if (id == R.id.dersprogrami) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DersProgramiFragment()).commit();
            setTitle("Ders Programı");
        }
        else if (id == R.id.sinavprogrami) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SinavProgFragment()).commit();
            setTitle("Sınav Programı");
        }
        else if (id == R.id.akademikpersonel) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new AkademikPersFragment()).commit();
            setTitle("Akademik Personel");
        } else if (id == R.id.akademiktakvim) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AkademiktakFragment()).commit();
            setTitle("Akademik Takvim");

        } else if (id == R.id.album) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new AlbumFragment()).commit();
            setTitle("Albüm");
        } else if (id == R.id.hakkinda) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new BolumFragment()).commit();
            setTitle("Bölüm Hakkında");
        }
        else if (id == R.id.iletisim) {

          fragmentManager.beginTransaction().replace(R.id.content_frame, new iletisimFragment()).commit();
            setTitle("İletişim");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
