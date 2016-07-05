package cemayan.com.matbil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DuyurularAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Duyurular> mDuyurular;

    public DuyurularAdapter(Activity activity, List<Duyurular> duyurulars) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.mDuyurular = duyurulars;
    }

    @Override
    public int getCount() {
        return mDuyurular.size();
    }

    @Override
    public Duyurular getItem(int position) {
        return mDuyurular.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.satir_layout, null);
        TextView textView =
                (TextView) satirView.findViewById(R.id.duyuruu);
        TextView textView2 =
                (TextView) satirView.findViewById(R.id.ek);
        TextView textView3 =
                (TextView) satirView.findViewById(R.id.tarih);
        ImageView imageView =
                (ImageView) satirView.findViewById(R.id.simge);

        Duyurular abc = mDuyurular.get(position);

        textView.setText(abc.getBaslik());
        textView2.setText(abc.getEk());
        textView3.setText(abc.gettarih());
        imageView.setImageResource(R.drawable.ok);

        return satirView;
    }
}