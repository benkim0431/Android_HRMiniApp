package com.example.hrminiapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewEmpFragment extends Fragment {
    View v;
    EditText edtName, edtDesig, edtDepart, edtEmail, edtSalary;
    Button btnSubmit;

    //creating a variable for our Database Reference for Firebase
    DatabaseReference databaseReference;

    public AddNewEmpFragment() {
        // Required empty public constructor
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

    private void AddDatatoFirebase(Employee objEmp){
        // The add value event listener is set to the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Employee");
        databaseReference.push().setValue(objEmp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(),"data added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Fail to add data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_new_emp, container, false);

        // Receive User's inputs
        edtName = v.findViewById(R.id.edtName);
        edtDesig = v.findViewById(R.id.edtDesig);
        edtDepart = v.findViewById(R.id.edtDepartment);
        edtEmail = v.findViewById(R.id.edtEmailId);
        edtSalary = v.findViewById(R.id.edtSalary);
        btnSubmit = v.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employeeInfo = CreateEmployee();
                AddDatatoFirebase(employeeInfo);
            }
        });
        return v;
    }
}