package com.example.hrminiapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListNewEmpFragment extends Fragment {

    View v;
    RecyclerView rcView;
    List<Employee> mList = new ArrayList<>();
    ListAdapter mAdapter;

    //creating a variable for our Database Reference for Firebase
    DatabaseReference databaseReference;


    public ListNewEmpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_new_emp, container, false);
        rcView = (RecyclerView) v.findViewById(R.id.rcView);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Employee");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Employee objEmp = snapshot.getValue(Employee.class);
                mList.add(objEmp);
                BindAdapter();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Employee objEmp = snapshot.getValue(Employee.class);
                mList.add(objEmp);
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


            return v;

    }

    // controller (MVC Pattern) of recycler view
    private void BindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcView.setLayoutManager(layoutManager);
        //Calls the constructor of ListAdapter and passes the list consisting of all employee records
        mAdapter = new ListAdapter(mList,getContext());
        rcView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}