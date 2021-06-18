package com.app.books_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.books_app.R;
import com.app.books_app.putPDF;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<putPDF> {


    // constructor for our list view adapter.
    public MyListAdapter(@NonNull Context context, ArrayList<putPDF> putPDFArrayList) {
        super(context, 0, putPDFArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        putPDF putPDF = getItem(position);

        // initializing our UI components of list view item.
        TextView text = listitemView.findViewById(R.id.custom_list_text);
        ImageView image = listitemView.findViewById(R.id.image_view);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        text.setText(putPDF.getName());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Intent intent = new Intent(getContext(), render_pdf.getInstance(putPDF.getUrl()).getClass());
                getContext().startActivity(intent);
            }
        });
        return listitemView;
    }

}
