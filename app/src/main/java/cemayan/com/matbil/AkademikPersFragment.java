package cemayan.com.matbil;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AkademikPersFragment extends Fragment {

    AkademikPersonelAdapter listAdapter;
    ExpandableListView expListView;
    List<AnaBilimDali> listDataHeader=new ArrayList<AnaBilimDali>();
    HashMap<AnaBilimDali, ArrayList<AkademikPersonel> > listDataChild = new HashMap<>();
    ProgressDialog pdia;
    TextView txt;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.akademikpers_layout,container,false);
        return myView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        new JSONTask().execute("http://cemayan.azurewebsites.net/WebService1.asmx/AbdGetir");

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Akademik Personel");

    }


    public class JSONTask extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia = new ProgressDialog(getActivity());
            pdia.setMessage("Yükleniyor");
            pdia.show();
        }


        @Override
        protected String doInBackground(String...params){
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(params[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line="";
                while ((line= reader.readLine())!= null){
                    buffer.append(line);
                }

                String finalJSON = buffer.toString();
                JSONArray parentArray = new JSONArray(finalJSON);
                ArrayList<AkademikPersonel> listData1 = new ArrayList<AkademikPersonel>();

                for (int i =0;i<parentArray.length();i++){
                    JSONObject parentObject = parentArray.getJSONObject(i);

                    String a1 = parentObject.getString("ABD_Adi");
                    int id = parentObject.getInt("ABD_ID");
                    listDataHeader.add(new AnaBilimDali(a1));
                    JSONArray parentArray2 = parentObject.getJSONArray("Liste");


                    for (int j =0;j<parentArray2.length();j++) {
                        JSONObject finalObject2 = parentArray2.getJSONObject(j);
                        String a4= finalObject2.getString("ID");
                        String a2 = finalObject2.getString("Adi");
                        String a3 = finalObject2.getString("Soyadi");
                        listData1.add(new AkademikPersonel(a4,a2,a3));
                        listDataChild.put(listDataHeader.get(i),listData1);
                   }

                    ArrayList abc =(ArrayList)listData1.clone();
                    listData1.clear();
                    listDataChild.put(listDataHeader.get(i),abc);

                }

            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }  finally {
                if(connection!=null){
                    connection.disconnect();
                }
                try{
                    if(reader !=null){
                        reader.close();
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected  void onPostExecute(String result){
            super.onPostExecute(result);




            expListView = (ExpandableListView)myView.findViewById(R.id.lvExp);

            listAdapter = new AkademikPersonelAdapter(getActivity(), listDataHeader, listDataChild);

            expListView.setAdapter(listAdapter);

            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {

                    return false;
                }
            });

            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {


                    OgretmenlerFragment f = new OgretmenlerFragment();
                    Bundle args = new Bundle();
                    args.putString("ID",listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getId());
                    f.setArguments(args);

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, f,"tag").commit();

                    return false;
                }
            });



            pdia.dismiss();
        }

    }



//    private void prepareListData() {
//
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//        listDataHeader.add("Bilgisayar Bilimleri A.B.D");
//        listDataHeader.add("Cebir ve Sayılar Teorisi A.B.D.");
//        listDataHeader.add("Geometri A.B.D.");
//        listDataHeader.add("Matematiğin Temelleri ve Lojik A.B.D.");
//        listDataHeader.add("Topoloji");
//        listDataHeader.add("Uygulamalı Matematik A.B.D.");
//
//
//        List<String> bb = new ArrayList<String>();
//        bb.add("Yrd. Doç. Dr. Alper Odabaş ");
//        bb.add("Yrd. Doç. Dr. Ahmet Faruk Aslan");
//        bb.add("Bil. Müh. Özer Çelik");
//        bb.add("Arş. Gör. Zühal Kurt ");
//        bb.add("Arş. Gör. Elis Soylu");
//        bb.add("Arş. Gör. Gürkan Kaplan ");
//
//        List<String> cebir = new ArrayList<String>();
//        cebir.add("Prof. Dr. Zekeriya Arvasi ");
//        cebir.add("Yrd. Doç. Dr. Ummahan Ege Arslan ");
//        cebir.add("Arş. Gör. Dr. Kadir Emir ");
//        cebir.add("Arş. Gör. Selim Çetin");
//
//        List<String> geo = new ArrayList<String>();
//        geo.add("Prof. Dr. Ziya Akça");
//        geo.add("Prof. Dr. Ali Görgülü");
//        geo.add("Prof. Dr. İsmail Kocayusufoğlu");
//       geo.add("Prof. Dr. Münevver Özcan");
//        geo.add("Prof. Dr. Nevin Gürbüz");
//        geo.add("Prof. Dr. Ayşe Bayar Korkmazoğlu");
//        geo.add("Prof. Dr. Süheyla Ekmekçi ");
//       geo.add("Prof. Dr. Cumali Ekici ");
//       geo.add("Prof. Dr. İbrahim Günaltılı");
//
//     List<String> mat = new ArrayList<String>();
//     mat.add("Prof. Dr. Şükrü Olgun");
//        mat.add("Doç. Dr. Özcan Gelişgen ");
//        mat.add("Yrd. Doç. Dr. Temel Ermiş");
//
//        List<String> top = new ArrayList<String>();
//        top.add("Prof. Dr. Mahmut Koçak ");
//        top.add("Doç. Dr. Enver Önder Uslu ");
//        top.add("Doç. Dr. İlker Akça ");
//        top.add("Arş. Gör. Hatice Gülsün");
//        top.add("Arş. Gör. Elif Ilgaz");
//
//        List<String> uyg = new ArrayList<String>();
//        uyg.add("Prof. Dr. M. Naci Özer");
//        uyg.add("Prof. Dr. İdris Dağ");
//        uyg.add("Prof. Dr. Dursun Eser");
//        uyg.add("Prof. Dr. Bülent Saka");
//        uyg.add("Prof. Dr. Ahmet Bekir");
//        uyg.add("Doç. Dr. Filiz Taşcan");
//        uyg.add("Doç. Dr. Dursun Irk");
//        uyg.add("Yrd. Doç. Dr. Sait San ");
//       uyg.add("Öğr. Gör. Melis Zorşahin");
//        uyg.add("Arş. Gör. Dr. Özlem Ersoy");
//        uyg.add("Arş. Gör. Dr. Ömer Ünsal");
//        uyg.add("Arş. Gör. Melike Kaplan");
//
//
//        listDataChild.put(listDataHeader.get(0), bb);// Header, Child data
//        listDataChild.put(listDataHeader.get(1), cebir);
//        listDataChild.put(listDataHeader.get(2), geo);
//       listDataChild.put(listDataHeader.get(3), mat);
//        listDataChild.put(listDataHeader.get(4), top);
//        listDataChild.put(listDataHeader.get(5), uyg);
//    }
}
