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

public class Fragment2 extends Fragment implements BaseContract.BaseView, SignUpView {

    private EditText mEditTextGender;
    private EditText mEditTextAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEditTextGender = (EditText) view.findViewById(R.id.editTextIsSmoking);
        mEditTextAge = (EditText) view.findViewById(R.id.editTextHeight);
        initViews();
    }

    @Override
    public void initViews() {
        mEditTextGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        mEditTextAge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextAge, colorStateList);
                return false;
            }
        });
        mEditTextGender.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextGender, colorStateList);
                return false;
            }
        });
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.list);
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        String[] values = getResources().getStringArray(R.array.gender);
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
                mEditTextGender.setText(itemValue);
                dialog.dismiss();

            }

        });
    }

    @Override
    public boolean isCorrect() {
        boolean isCorrect = true;
        if(mEditTextGender.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextGender, colorStateList);
            isCorrect = false;
        }
        if(mEditTextAge.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextAge, colorStateList);
            isCorrect = false;
        }
        return isCorrect;
    }

    public String getGender(){
        return mEditTextGender.getText().toString();
    }

    public String getYearsOld(){
        return mEditTextAge.getText().toString();
    }
}