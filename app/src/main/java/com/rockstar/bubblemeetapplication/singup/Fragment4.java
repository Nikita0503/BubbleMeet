package com.rockstar.bubblemeetapplication.singup;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;

public class Fragment4 extends Fragment implements BaseContract.BaseView {

    EditText mEditTextIsSmoking;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEditTextIsSmoking = (EditText) view.findViewById(R.id.editTextIsSmoking);
        initViews();
    }

    @Override
    public void initViews() {
        mEditTextIsSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.list);
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        String[] values = getResources().getStringArray(R.array.yesNo);
        dialog.show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView
                        .getItemAtPosition(position);
                mEditTextIsSmoking.setText(itemValue);
                dialog.dismiss();

            }

        });
    }
}