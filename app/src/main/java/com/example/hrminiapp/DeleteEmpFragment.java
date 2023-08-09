package com.example.hrminiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteEmpFragment extends Fragment {

    View v;
    EditText edtEmpId;
    Button btnDelete;
    DBHelper dbh;
    public DeleteEmpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_delete_emp, container, false);
        // Assign widget to variable
        edtEmpId = v.findViewById(R.id.edtEmpId);
        btnDelete = v.findViewById(R.id.btnDelete);
        dbh = new DBHelper(getActivity());

        // Show the result of click event ( Delete from Employee DB)
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedRows = dbh.DeleteEmployee(Integer.parseInt(edtEmpId.getText().toString().trim()));
                if(deletedRows > 0)
                {
                    Toast.makeText(getActivity(),"Record successfully deleted", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getActivity(),"Error: Employee Record does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}