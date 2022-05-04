package com.example.chat_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    private ImageView photo_profile;
    private TextView user_name;
    private RecyclerView rv_messages;
    private EditText txt_message;
    private Button btn_send;
    private ImageButton btn_send_photo;
    //Create adapter
    private AdapterMessages adapter;
    //Create objects for the database
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo_profile = (ImageView) findViewById(R.id.photo_profile);
        user_name = (TextView) findViewById(R.id.user_name);
        rv_messages = (RecyclerView) findViewById(R.id.rv_messages);
        txt_message = (EditText) findViewById(R.id.txt_message);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send_photo = (ImageButton) findViewById(R.id.btn_send_photo);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");
        storage = FirebaseStorage.getInstance();

        adapter = new AdapterMessages(this);
        LinearLayoutManager l = new LinearLayoutManager(this);

        rv_messages.setLayoutManager(l);
        rv_messages.setAdapter(adapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new Message(txt_message.getText().toString(),
                        user_name.getText().toString(), "","1","00:00"));
                txt_message.setText("");
                /*adapter.addMessage(new Message(txt_message.getText().toString(),
                        user_name.getText().toString(), "","1","00:00"));*/
            }
        });
        btn_send_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Select a photo"),PHOTO_SEND);
            }
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message m = snapshot.getValue(Message.class);
                adapter.addMessage(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScrollbar() {
        rv_messages.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("Images chat");
            final StorageReference photo_reference = storageReference.child(u.getLastPathSegment());
            photo_reference.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    /*Task<Uri> u = taskSnapshot.getStorage().getDownloadUrl();
                    Toast.makeText(MainActivity.this, "Imagen enviada "+u, Toast.LENGTH_SHORT).show();
                    Message m = new Message("Has recibido una imagen",u.toString(),user_name.getText().toString(),"","2","00:00");
                    databaseReference.push().setValue(m);*/
                    photo_reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            Message m = new Message("Has recibido una imagen",downloadUrl.toString(),user_name.getText().toString(),"","2","00:00");
                            databaseReference.push().setValue(m);
                            Toast.makeText(MainActivity.this, "Imagen enviada "+u, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}