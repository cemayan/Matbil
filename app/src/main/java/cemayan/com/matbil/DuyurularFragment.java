package cemayan.com.matbil;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Date;
import java.util.List;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;



public class DuyurularFragment extends ListFragment {



    private ProgressDialog pdia;
    final List<Duyurular> duyurular=new ArrayList<Duyurular>();
    ListView list;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.duyurular_layout,container,false);
        return myView;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("matbilg")
                .maxItemsPerRequest(25)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)

                .build();

        setListAdapter(adapter);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            getActivity().setTitle("Duyurular");

    }
    
//    public class JSONTask extends AsyncTask<String,String,String> {
//
//       @Override
//        protected void onPreExecute() {
//           super.onPreExecute();
//
//           pdia = new ProgressDialog(getActivity());
//           pdia.setMessage("Yükleniyor");
//           pdia.show();
//       }
//
//
//        @Override
//        protected String doInBackground(String...params){
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//            try{
//                URL url = new URL(params[0]);
//                connection=(HttpURLConnection)url.openConnection();
//                connection.connect();
//
//                InputStream stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuffer buffer = new StringBuffer();
//                String line="";
//                while ((line= reader.readLine())!= null){
//                    buffer.append(line);
//                }
//
//                String finalJSON = buffer.toString();
//
//                JSONObject parentObject = new JSONObject(finalJSON);
//                JSONArray parentArray = parentObject.getJSONArray("duyurular");
//
//                StringBuffer finalBufferedData = new StringBuffer();
//
//                for (int i =0;i<parentArray.length();i++){
//                    JSONObject finalObject = parentArray.getJSONObject(i);
//                    String a1 = finalObject.getString("Baslik");
//                    String a2 = finalObject.getString("icerik");
//                    String a3 = finalObject.getString("Tarih");
//                    String a4 = finalObject.getString("Ek");
//                    duyurular.add(new Duyurular(a1,a2,a3,a4));
//
//                }
//
//                return  finalBufferedData.toString();
//            }
//            catch (MalformedURLException e){
//                e.printStackTrace();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if(connection!=null){
//                    connection.disconnect();
//                }
//                try{
//                    if(reader !=null){
//                        reader.close();
//                    }
//                }
//                catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//            return null;
//
//        }
//
//
//
//
//        @Override
//        protected  void onPostExecute(String result){
//            super.onPostExecute(result);
//            list=(ListView)myView.findViewById(R.id.liste);
//            DuyurularAdapter adaptorumuz=new DuyurularAdapter(getActivity(), duyurular);
//            list.setAdapter(adaptorumuz);
//            list.setOnTouchListener(new View.OnTouchListener() {
//                // Setting on Touch Listener for handling the touch inside ScrollView
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    // Disallow the touch request for parent scroll on touch of child view
//                    v.getParent().requestDisallowInterceptTouchEvent(true);
//                    return false;
//                }
//            });
//            pdia.dismiss();
//        }
//
//    }







}
