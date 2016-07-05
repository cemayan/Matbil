package cemayan.com.matbil;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

public class AlbumFragment extends Fragment {
    RoundedImageView selectedImage;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.album_layout,container,false);
        return myView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        Gallery gallery = (Gallery)view.findViewById(R.id.gallery);
        selectedImage=(RoundedImageView)view.findViewById(R.id.imageView);
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(getActivity());
        gallery.setAdapter(galleryImageAdapter);


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });

    }




}
