package com.example.tugas2_notes;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements TextToSpeech.OnInitListener {

    Context context;

    RealmResults<Note> notesList;

    // Make Text to Speech Instances
    TextToSpeech tts;

    public MyAdapter(Context context, RealmResults<Note> notesList) {
        this.context = context;
        this.notesList = notesList;

        // Register setting form tts
        this.tts = new TextToSpeech(this.context,this);
        this.tts.setSpeechRate((float)0.8);
        this.tts.setPitch((float) 5.8);
        this.tts.setLanguage((Locale.ENGLISH));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.titleOutput.setText(note.getTitle());
        holder.descriptionOutput.setText(note.getDescription());

        String formattedTime = DateFormat.getDateTimeInstance().format(note.getCreatedAt());
        holder.createdAtOutput.setText(formattedTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speakAloud = note.getDescription().toString();
                tts.speak(speakAloud,TextToSpeech.QUEUE_FLUSH,null);
                //Toast.makeText(context, "Selesai", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    // if menu long clicked
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("DELETE")) {
                            // delete note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public void onInit(int status) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView createdAtOutput;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleOutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionOutput);
            createdAtOutput = itemView.findViewById(R.id.createdatOutput);

        }
    }

}
