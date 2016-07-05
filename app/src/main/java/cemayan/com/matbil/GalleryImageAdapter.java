package cemayan.com.matbil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class GalleryImageAdapter extends BaseAdapter
{
    private Context mContext;



    public GalleryImageAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);

        i.setMaxHeight(400);
        i.setMaxWidth(400);
        i.setLayoutParams(new Gallery.LayoutParams(400, 400));

        i.setScaleType(ImageView.ScaleType.FIT_XY);


        return i;
    }

    public Integer[] mImageIds = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,

    };

}
