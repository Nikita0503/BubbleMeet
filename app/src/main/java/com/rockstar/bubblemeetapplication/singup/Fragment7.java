package com.rockstar.bubblemeetapplication.singup;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;

public class Fragment7 extends Fragment implements BaseContract.BaseView, SignUpView {

    EditText mEditTextIsCooking;
    EditText mEditTextCity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment7, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEditTextIsCooking = (EditText) view.findViewById(R.id.editTextIsCooking);
        mEditTextCity = (EditText) view.findViewById(R.id.editTextCity);
        initViews();
    }

    @Override
    public void initViews() {
        mEditTextIsCooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        mEditTextIsCooking.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextIsCooking, colorStateList);
                return false;
            }
        });
        mEditTextCity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextCity, colorStateList);
                return false;
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
                mEditTextIsCooking.setText(itemValue);
                dialog.dismiss();

            }

        });
    }

    @Override
    public boolean isCorrect() {
        boolean isCorrect = true;
        if(mEditTextIsCooking.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextIsCooking, colorStateList);
            isCorrect = false;
        }
        if(mEditTextCity.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextCity, colorStateList);
            isCorrect = false;
        }
        return isCorrect;
    }

    public String getCooking(){
        return mEditTextIsCooking.getText().toString();
    }

    public String getCity(){
        return mEditTextCity.getText().toString();
    }

}