package com.rockstar.bubblemeetapplication.singup;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.Coordinates;

import java.io.File;

public class DialogSignUpFragment extends DialogFragment implements BaseContract.BaseView {

    private Button mButtonNext;
    private ImageView mImageViewClose;
    private SignUpFragmentPagerAdapter mSignUpFragmentAdapter;
    private ViewPager mViewPagerSignUp;
    private TabLayout mTabLayoutSignUp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_sign_up, null);
        mButtonNext = (Button) v.findViewById(R.id.buttonNext);
        mImageViewClose = (ImageView) v.findViewById(R.id.imageViewClose);
        mViewPagerSignUp = (ViewPager) v.findViewById(R.id.profile_viewpager);
        mTabLayoutSignUp = (TabLayout) v.findViewById(R.id.tab_layout);

        initViews();
        return v;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.height = 1400;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Override
    public void initViews() {
        mSignUpFragmentAdapter = new SignUpFragmentPagerAdapter(getChildFragmentManager());
        mViewPagerSignUp.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        mViewPagerSignUp.setAdapter(mSignUpFragmentAdapter);
        mViewPagerSignUp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Do nothing
            }

            @Override
            public void onPageSelected(final int position) {
                if(position == 3 || position == 9){
                    mButtonNext.setText(getString(R.string.letsBubble));
                    mButtonNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            //startActivity(intent);
                            //dismiss();
                            //getActivity().finish();
                            SignUpActivity activity = (SignUpActivity) getActivity();
                            if(position == 3) {
                                Coordinates coordinates = getCoordinates();
                                activity.setLocation(coordinates.latitude + "," + coordinates.longitude);
                                activity.signUpNewUser();
                            }
                            if(position == 9){
                                Coordinates coordinates = getCoordinates();
                                activity.setLocation(coordinates.latitude + "," + coordinates.longitude);
                                activity.signUpNewUserFull();
                            }
                        }
                    });
                }else{
                    mButtonNext.setText(getString(R.string.next));
                    mButtonNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showNextPage();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Do nothing
            }
        });
        mTabLayoutSignUp.setupWithViewPager(mViewPagerSignUp);
        mImageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                getActivity().finish();
            }
        });
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextPage();
            }
        });
    }



    public void addPhoto(int number, File photo){
        mSignUpFragmentAdapter.addPhoto(number, photo);
    }

    public void showNextPage(){
        int currentItem = mViewPagerSignUp.getCurrentItem();
        if(mSignUpFragmentAdapter.isCorrect(mViewPagerSignUp.getCurrentItem())) {
            SignUpActivity activity = (SignUpActivity) getActivity();
            switch (currentItem+1){
                case 1:
                    activity.setName(((Fragment1)mSignUpFragmentAdapter.getItem(currentItem)).getName());
                    break;
                case 2:
                    activity.setGender(((Fragment2)mSignUpFragmentAdapter.getItem(currentItem)).getGender());
                    activity.setYearsOld(((Fragment2)mSignUpFragmentAdapter.getItem(currentItem)).getYearsOld());
                    break;
                case 3:
                    activity.setEmail(((Fragment3)mSignUpFragmentAdapter.getItem(currentItem)).getEmail());
                    activity.setPassword(((Fragment3)mSignUpFragmentAdapter.getItem(currentItem)).getPassword());
                    activity.setConfirmPassword(((Fragment3)mSignUpFragmentAdapter.getItem(currentItem)).getConfirmPassword());
                    break;
                case 5:
                    activity.setHeight(((Fragment5)mSignUpFragmentAdapter.getItem(currentItem)).getHeight());
                    activity.setSmoking(((Fragment5)mSignUpFragmentAdapter.getItem(currentItem)).getSmoking());
                    break;
                case 6:
                    activity.setMarried(((Fragment6)mSignUpFragmentAdapter.getItem(currentItem)).getMarried());
                    activity.setChildren(((Fragment6)mSignUpFragmentAdapter.getItem(currentItem)).getChildren());
                    break;
                case 7:
                    activity.setCooking(((Fragment7)mSignUpFragmentAdapter.getItem(currentItem)).getCooking());
                    activity.setCity(((Fragment7)mSignUpFragmentAdapter.getItem(currentItem)).getCity());
                    break;
                case 8:
                    activity.setLookingFor(((Fragment8)mSignUpFragmentAdapter.getItem(currentItem)).getLookingFor());
                    break;
                case 9:
                    activity.setHobbies(((Fragment9)mSignUpFragmentAdapter.getItem(currentItem)).getHobbies());
                    break;
            }
            mViewPagerSignUp.setCurrentItem(currentItem + 1);
        }
    }

    public Coordinates getCoordinates() {
        Coordinates coord;
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!=null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            coord = new Coordinates(latitude, longitude);
            return coord;
        }else{
            return null;
        }
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().finish();
    }
}
