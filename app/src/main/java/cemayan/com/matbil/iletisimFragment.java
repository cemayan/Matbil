package cemayan.com.matbil;



import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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

public class iletisimFragment extends Fragment  {
    private ProgressDialog pdia;
    private View myView;

    private  TextView txt1;
    private  TextView txt2;
    private  TextView txt3;
    private  TextView txt4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.iletisim_layout, container, false);
        return myView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        new JSONTask().execute("http://cemayan.azurewebsites.net/WebService1.asmx/BolumGetir");

        txt1=(TextView)view.findViewById(R.id.textView8);
        txt2=(TextView)view.findViewById(R.id.textView10);
        txt3=(TextView)view.findViewById(R.id.textView12);
        txt4=(TextView)view.findViewById(R.id.textView14);






        Button btn4 = (Button)view.findViewById(R.id.button5);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=39.7505661,30.487653"));
                startActivity(intent);
            }
        });



    }


    public class JSONTask extends  AsyncTask <String,String,String>{


        @Override
        protected void onPreExecute(){
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

                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray("bolumm");
                JSONObject finalObject =parentArray.getJSONObject(0);
                String a1 = finalObject.getString("Adres");
                String a2 = finalObject.getString("Telefon");
                String a3 = finalObject.getString("Email");
                String a4 = finalObject.getString("Web");
                return  a1+"--"+a2+"--"+a3+"--"+a4;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
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
            String[] abc = result.split("--");
            txt1.setText(abc[0]);
            txt2.setText(abc[1]);
            txt3.setText(abc[2]);
            txt4.setText(abc[3]);
            pdia.dismiss();

        }

    }




}