package com.app.books_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrievePDF extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<putPDF> uploadedPDF;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_p_d_f);

        PDFView pdfView = findViewById(R.id.pdfView);

        listView = findViewById(R.id.listView);
        uploadedPDF = new ArrayList<>();

        retrievePdfFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                putPDF putPDF = uploadedPDF.get(i);

                Intent intent = new Intent(RetrievePDF.this, render_pdf.getInstance(putPDF.url).getClass());
                /*intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));*/
                startActivity(intent);
            }
        });
    }

    private void retrievePdfFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("pdf");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    putPDF putPDF = ds.getValue(com.app.books_app.putPDF.class);
                    uploadedPDF.add(putPDF);
                }

                String[] uploadName = new String[uploadedPDF.size()];
                for (int i = 0; i < uploadName.length; i++) {
                    uploadName[i] = uploadedPDF.get(i).getName();
                }

                String[] uploadUrl = new String[uploadedPDF.size()];
                for (int i = 0; i < uploadUrl.length; i++) {
                    uploadUrl[i] = uploadedPDF.get(i).getUrl();
                }


                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploadName){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);

                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(20);
                        return view;
                    }
                };

                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RetrievePDF.this, "Fail to get PDF url.", Toast.LENGTH_SHORT).show();
            }
        }
        );
    }

}
