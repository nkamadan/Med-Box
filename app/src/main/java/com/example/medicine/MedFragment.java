package com.example.medicine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedFragment extends Fragment {

    private View medView;
    private RecyclerView medList;
    private DatabaseReference dbref;
    private String username;
    FirebaseDatabase rootNode;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MedFragment() {
        // Required empty public constructor
    }

    public static MedFragment newInstance(String param1, String param2) {
        MedFragment fragment = new MedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        medView = inflater.inflate(R.layout.fragment_med, container, false);
        medList = (RecyclerView) medView.findViewById(R.id.recView);
        medList.setLayoutManager(new LinearLayoutManager(getContext()));
        username = getArguments().getString("username");
        dbref = FirebaseDatabase.getInstance().getReference("Medicines").child(username);
        return medView;
    }

    @Override
    public void onStart() {
        super.onStart();
        
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Medicine>().setQuery(dbref,Medicine.class)
                        .build();
        FirebaseRecyclerAdapter<Medicine,MedicineViewHolder> adapter = new FirebaseRecyclerAdapter<Medicine, MedicineViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MedicineViewHolder holder, int position, @NonNull Medicine model) {
                String med_index = getRef(position).getKey();
                dbref.child(med_index).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String medicine_name = snapshot.child("name").getValue().toString();
                        String medicine_color = snapshot.child("color").getValue().toString();
                        String medicine_capsule = snapshot.child("kapsul").getValue().toString();
                        String situation = snapshot.child("durum").getValue().toString();
                        String kacdefa = snapshot.child("kacdefa").getValue().toString();
                        Log.println(Log.ERROR, "KACDEFA : ", kacdefa);
                        String alarm_situation = snapshot.child("alarm_durum").getValue().toString();
                        holder.med_name.setText(medicine_name);
                        holder.med_color.setText(medicine_color);
                        holder.med_count.setText(kacdefa);
                        holder.situation.setText(situation);
                        //holder.capsule.setText(medicine_capsule);
                        if(alarm_situation.equals("0")){ //Alarm is off
                            holder.on_off.setChecked(false); //Set the switch to off
                        }
                        else{
                            holder.on_off.setChecked(true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deneme, parent, false);
                MedicineViewHolder viewHolder = new MedicineViewHolder(view);
                return viewHolder;
            }
        };
        medList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder{

        TextView med_name, med_color, med_count, capsule, situation;
        Switch on_off;
        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            med_name = itemView.findViewById(R.id.med_name);
            med_color = itemView.findViewById(R.id.med_color);
            med_count = itemView.findViewById(R.id.med_count);
            //capsule = itemView.findViewById(R.id.capsule);
            situation = itemView.findViewById(R.id.situation);
            on_off = itemView.findViewById(R.id.on_off);

        }
    }
}
