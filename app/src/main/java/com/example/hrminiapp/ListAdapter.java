package com.example.hrminiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Employee> mList;

    // To show the list of employee using recycler view, use this adapter
    // Controller (MVC) for Recyclerview
    public ListAdapter(List<Employee> listEmp, Context context)
    {
        super();
        mList=listEmp;
    }

    // Each row data of recyclerview
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(v);
        return viewHolder1;
    }

    // Bind holder and data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Employee empObj = mList.get(position);
        ((ViewHolder) holder).mId.setText(String.valueOf(empObj.getId()));
        ((ViewHolder) holder).mName.setText(empObj.getName());
        ((ViewHolder) holder).mDesig.setText(empObj.getDesig());
        ((ViewHolder) holder).mDept.setText(empObj.getDept());
        ((ViewHolder) holder).mEmail.setText(empObj.getEmailid());
        ((ViewHolder) holder).mSalary.setText(String.valueOf(empObj.getSalary()));
    }

    @Override
    public int getItemCount() {
//        return 0;
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mId, mName, mDesig, mDept, mEmail, mSalary;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = (TextView) itemView.findViewById(R.id.txtId);
            mName = (TextView) itemView.findViewById(R.id.txtName);
            mDesig = (TextView) itemView.findViewById(R.id.txtDesig);
            mDept = (TextView) itemView.findViewById(R.id.txtDept);
            mEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            mSalary = (TextView) itemView.findViewById(R.id.txtSalary);
        }
    }
}
