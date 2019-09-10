package com.rockstar.bubblemeetapplication.singup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;

public class Fragment4 extends Fragment implements BaseContract.BaseView, SignUpView {

    Button mButtonAddMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mButtonAddMore = (Button) view.findViewById(R.id.buttonAddMore);
        initViews();
    }

    @Override
    public void initViews() {
        mButtonAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSignUpFragment parentFragment = (DialogSignUpFragment) getParentFragment();
                parentFragment.showNextPage();
            }
        });
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}