package com.example.hrminiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmpFragment extends Fragment {
    View v;
    EditText edtName, edtDesig, edtDepart, edtEmail, edtSalary;
    Button btnSubmit;
    DBHelper dbh; //Instance of the Database helper class 'DBH'
    Boolean insertStatus;

    public AddEmpFragment(){

    }

    // Create Employee Obj composed by the user's input
    public Employee CreateEmployee()
    {
        Employee objEmp1 = new Employee();
        int empId = 0;
        objEmp1.setId(empId);
        objEmp1.setName(edtName.getText().toString().trim());
        objEmp1.setDesig(edtDesig.getText().toString().trim());
        objEmp1.setDept(edtDepart.getText().toString().trim());
        objEmp1.setEmailid(edtEmail.getText().toString().trim());
        objEmp1.setSalary(Integer.parseInt(edtSalary.getText().toString().trim()));
        return objEmp1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_emp,container, false);
        // Receive User's inputs
        edtName = v.findViewById(R.id.edtName);
        edtDesig = v.findViewById(R.id.edtDesig);
        edtDepart = v.findViewById(R.id.edtDepartment);
        edtEmail = v.findViewById(R.id.edtEmailId);
        edtSalary = v.findViewById(R.id.edtSalary);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        dbh = new DBHelper(getActivity());

        // Show the result of click event ( Insert into Employee DB)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee objEmp = CreateEmployee();
                insertStatus = dbh.InsertEmployee(objEmp);
                if(insertStatus)
                {
                    Toast.makeText(getActivity(), "Record added successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Record insertion successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}