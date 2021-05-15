package com.example.medicine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class AddFragment extends Fragment implements View.OnClickListener{

    EditText medicineName, medicineColor, kapsulNumber, kacdefa;
    Button addMedicine;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressDialog prgdialog;
    RadioGroup radioGroup;
    RadioButton ac;
    RadioButton tok;
    String username="";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public AddFragment() {

    }

    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        View v = inflater.inflate(R.layout.fragment_add, container,false);
        radioGroup = v.findViewById(R.id.rdgroup);
        addMedicine = v.findViewById(R.id.btn_add);
        medicineName = v.findViewById(R.id.input_name);
        medicineColor = v.findViewById(R.id.input_color);
        kapsulNumber = v.findViewById(R.id.input_number);
        kacdefa = v.findViewById(R.id.input_kacdefa);
        ac = v.findViewById(R.id.radio_ac);
        tok = v.findViewById(R.id.radio_tok);


        TimePicker picker=(TimePicker) v.findViewById(R.id.datePicker1);
        picker.setIs24HourView(true);

        username = getArguments().getString("username");
        addMedicine.setOnClickListener(this);
        //username = getArguments().getString("username");

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//
//                switch(checkedId) {
//                    case R.id.radio_ac:
//                        //newlyAdded.durum = 0;
//                        break;
//                    case R.id.radio_tok:
//                        //newlyAdded.durum = 1;
//                        break;
//                }
//            }
//        });



        return v;
    }

    @Override
    public void onClick(View v) {
            Medicine newlyAdded = new Medicine();
            final long[] count = new long[1];
            //Log.println(Log.ERROR, "onclicklistener", username);
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Medicines");
            //reference.setValue(null);
            newlyAdded.name = medicineName.getText().toString();
            newlyAdded.color = medicineColor.getText().toString();
            newlyAdded.kapsul = kapsulNumber.getText().toString();
            newlyAdded.kacdefa = kacdefa.getText().toString();
            reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count[0] = dataSnapshot.getChildrenCount();
                Log.println(Log.ERROR, "onclicklistener", String.valueOf(count[0]));
                reference.child(username).child("med" + count[0]).setValue(newlyAdded);
            }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //reference.child(username).child("med" + count[0]).setValue(newlyAdded);
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio_ac:
//                if (checked)
//                    newlyAdded.durum = 0;
//                    break;
//            case R.id.radio_tok:
//                if (checked)
//                    newlyAdded.durum = 1;
//                    break;
//        }
//    }



}