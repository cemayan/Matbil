package cemayan.com.matbil;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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


public class DersProgramiFragment extends Fragment {
    private ProgressDialog pdia;
    private  TouchImageView img ;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.dersprog_layout, container, false);
        return myView;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        new JSONTask().execute("http://cemayan.azurewebsites.net/WebService1.asmx/BolumGetir");
        img = (TouchImageView)view.findViewById(R.id.imageView2);
        TouchImageView img = (TouchImageView)view.findViewById(R.id.imageView2);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Ders Programi");

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
                String a1 = finalObject.getString("Dersprogrami");
                return  a1;
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
            new DownloadImageTask(img).execute(result);
            pdia.dismiss();
        }

    }


}


