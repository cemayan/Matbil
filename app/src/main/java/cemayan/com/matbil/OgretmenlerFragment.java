package cemayan.com.matbil;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

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

/**
 * Created by Cem on 3.07.2016.
 */
public class OgretmenlerFragment extends Fragment {
    ProgressDialog pdia;
    ImageView img;
    TextView a1;
    TextView a2;
    TextView a3;
    TextView a4;
    TextView a5;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.ogretmenler_layout,container,false);
        return myView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        img = (ImageView)view.findViewById(R.id.imageView11);
         a1 = (TextView)view.findViewById(R.id.textView13);
         a2 = (TextView)view.findViewById(R.id.textView16);
         a3 = (TextView)view.findViewById(R.id.textView18);
         a4 = (TextView)view.findViewById(R.id.textView21);
        a5 = (TextView)view.findViewById(R.id.textView23);
        Bundle args = getArguments();

        new JSONTask().execute("http://cemayan.azurewebsites.net/WebService1.asmx/AkademikPGetirr?id="+args.getString("ID")+"");



    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



    public class JSONTask extends AsyncTask<String,String,String> {


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
                    JSONArray parentArray = new JSONArray(finalJSON);
                    JSONObject parentObject = parentArray.getJSONObject(0);
                    String a1 = parentObject.getString("Adi");
                    String a2 = parentObject.getString("Soyadi");
                    String a3 = parentObject.getString("Email");
                    String a4 = parentObject.getString("Telefon");
                    String a5 = parentObject.getString("abd");
                    String a6 = parentObject.getString("resim");
                     String a7 = parentObject.getString("unvan");
                    return  a1+"--"+a2+"--"+a3+"--"+a4+"--"+a5+"--"+a6+"--"+a7;

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
            String[] dizi =result.split("--");
            pdia.dismiss();
            a1.setText(dizi[0]+" "+dizi[1]);
            a2.setText(dizi[2]);
            a3.setText(dizi[3]);
            a4.setText(dizi[4]);
            a5.setText(dizi[6]);
            new DownloadImageTask(img).execute("http://fef.ogu.edu.tr/matbil/servisler/images/"+dizi[5]);

        }

    }

}
