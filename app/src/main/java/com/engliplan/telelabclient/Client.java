package com.engliplan.telelabclient;

import androidx.annotation.NonNull;
import androidx.core.view.inputmethod.InputConnectionCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static Client client=null;

    private Client(){
        FirebaseDatabase.getInstance().getReference().child("command").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateOnCommandReceievdListeners(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                updateOnCommandReceievdListeners(databaseError.getMessage());
            }
        });
    }

    public interface OnCommandRecievedListener{
        void onCommandRecieved(String command);
        void onError(String error);
    }

    private List<OnCommandRecievedListener> onCommandRecievedListeners=new ArrayList<>();

    public void addOnCommandReceivedListener(OnCommandRecievedListener onCommandRecievedListener){
        onCommandRecievedListeners.add(onCommandRecievedListener);
    }
    public void removeOnCommandReceivedListener(OnCommandRecievedListener onCommandRecievedListener){
        onCommandRecievedListeners.remove(onCommandRecievedListener);
    }
    private void updateOnCommandReceievdListeners(String command){
        for(int i=0;i<onCommandRecievedListeners.size();i++)
            onCommandRecievedListeners.get(i).onCommandRecieved(command);
    }

    private void errorOnCommandRecievedListeners(String error){
        for(int i=0;i<onCommandRecievedListeners.size();i++)
            onCommandRecievedListeners.get(i).onError(error);
    }

    public static synchronized Client getInstance(){
        if(client==null)
            client = new Client();
        return client;
    }
}
