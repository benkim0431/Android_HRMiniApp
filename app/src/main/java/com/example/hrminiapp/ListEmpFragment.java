package com.example.hrminiapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListEmpFragment extends Fragment {

    View v;
    RecyclerView rcView;
    List<Employee> mList = new ArrayList<>();
    DBHelper dbh;
    ListAdapter mAdapter;
    public ListEmpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_emp, container, false);
        rcView = (RecyclerView) v.findViewById(R.id.rcView);
        dbh = new DBHelper(getActivity());

        Cursor cursor1 = dbh.ListEmployee();
        if(cursor1 == null)
        {
            Toast.makeText(getActivity(),"No Employee records found",Toast.LENGTH_LONG).show();
            return v;
        }
        else
        {
            // Fetch data from cursor and set at employer object
            cursor1.moveToFirst();
            do{
                Employee empObj = new Employee();
                empObj.setId(cursor1.getInt(0));
                empObj.setName(cursor1.getString(1));
                empObj.setDesig(cursor1.getString(2));
                empObj.setDept(cursor1.getString(3));
                empObj.setEmailid(cursor1.getString(4));
                empObj.setSalary(cursor1.getInt(5));

                mList.add(empObj);
            }while (cursor1.moveToNext());
            cursor1.close();
            dbh.close();
            BindAdapter();
            return v;
        }
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