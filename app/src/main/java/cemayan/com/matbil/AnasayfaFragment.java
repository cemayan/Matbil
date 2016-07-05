package cemayan.com.matbil;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;

public class AnasayfaFragment extends Fragment {

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        myView = inflater.inflate(R.layout.anasayfa_layout,container,false);
        return myView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
       final FragmentManager fragmentManager = getFragmentManager();
        ImageButton btn1 =(ImageButton)view.findViewById(R.id.imageButton);
        ImageButton btn2 =(ImageButton)view.findViewById(R.id.imageButton2);
        ImageButton btn3 =(ImageButton)view.findViewById(R.id.imageButton3);
        ImageButton btn4 =(ImageButton)view.findViewById(R.id.imageButton4);
        ImageButton btn5 =(ImageButton)view.findViewById(R.id.imageButton5);

        ImageButton btn6 =(ImageButton)view.findViewById(R.id.imageButton33);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.content_frame, new DuyurularFragment()).commit();



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction().replace(R.id.content_frame, new DersProgramiFragment()).commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction().replace(R.id.content_frame, new AkademikPersFragment()).commit();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.content_frame, new YemekhaneFragment()).commit();

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction().replace(R.id.content_frame, new SinavProgFragment()).commit();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction().replace(R.id.content_frame, new OBSFragment()).commit();
            }
        });



    }




}
