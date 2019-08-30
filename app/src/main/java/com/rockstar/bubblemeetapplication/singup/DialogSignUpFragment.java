package com.rockstar.bubblemeetapplication.singup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;

public class DialogSignUpFragment extends DialogFragment implements BaseContract.BaseView {

    Button mButtonNext;
    ImageView mImageViewClose;
    SignUpFragmentPagerAdapter mSignUpFragmentAdapter;
    ViewPager mViewPagerSignUp;
    TabLayout mTabLayoutSignUp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_sign_up, null);
        mButtonNext = (Button) v.findViewById(R.id.buttonNext);
        mImageViewClose = (ImageView) v.findViewById(R.id.imageViewClose);
        mViewPagerSignUp = (ViewPager) v.findViewById(R.id.sign_up_viewpager);
        mTabLayoutSignUp = (TabLayout) v.findViewById(R.id.tab_layout);
        initViews();
        return v;
    }

    @Override
    public void initViews() {
        mSignUpFragmentAdapter = new SignUpFragmentPagerAdapter(getChildFragmentManager());
        mViewPagerSignUp.setAdapter(mSignUpFragmentAdapter);
        mTabLayoutSignUp.setupWithViewPager(mViewPagerSignUp);
        mImageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
