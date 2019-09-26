package com.rockstar.bubblemeetapplication.singup;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;

public class Fragment9 extends Fragment implements BaseContract.BaseView, SignUpView {

    EditText mEditTextHobbies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment9, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEditTextHobbies = (EditText) view.findViewById(R.id.editTextHobbies);
        initViews();
    }

    @Override
    public void initViews() {
        mEditTextHobbies.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextHobbies, colorStateList);
                return false;
            }
        });
    }

    @Override
    public boolean isCorrect() {
        boolean isCorrect = true;
        if(mEditTextHobbies.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextHobbies, colorStateList);
            isCorrect = false;
        }
        return isCorrect;
    }

    public String getHobbies(){
        return mEditTextHobbies.getText().toString();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}