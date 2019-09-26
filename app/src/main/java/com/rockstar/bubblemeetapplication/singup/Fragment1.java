package com.rockstar.bubblemeetapplication.singup;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Fragment1 extends Fragment implements BaseContract.BaseView, SignUpView {

    public static final int GALLERY_MAIN_PHOTO = 0;
    public static final int GALLERY_ADDITIONAL_PHOTO_1 = 1;
    public static final int GALLERY_ADDITIONAL_PHOTO_2 = 2;
    public static final int GALLERY_ADDITIONAL_PHOTO_3 = 3;

    private File mMainPhoto;
    private File mAdditionalPhoto1;
    private File mAdditionalPhoto2;
    private File mAdditionalPhoto3;

    private TextView mTextViewAddPhoto;
    private EditText mEditTextName;
    private CircleImageView mImageViewMainPhoto;
    private CircleImageView mImageViewAdditionalPhoto1;
    private CircleImageView mImageViewAdditionalPhoto2;
    private CircleImageView mImageViewAdditionalPhoto3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextViewAddPhoto = (TextView) view.findViewById(R.id.textViewAddPhoto);
        mEditTextName = (EditText) view.findViewById(R.id.editTextName);
        mImageViewMainPhoto = (CircleImageView) view.findViewById(R.id.imageViewMainPhoto);
        mImageViewAdditionalPhoto1 = (CircleImageView) view.findViewById(R.id.imageViewAdditionalPhoto1);
        mImageViewAdditionalPhoto2 = (CircleImageView) view.findViewById(R.id.imageViewAdditionalPhoto2);
        mImageViewAdditionalPhoto3 = (CircleImageView) view.findViewById(R.id.imageViewAdditionalPhoto3);
        initViews();
    }

    @Override
    public void initViews() {
        mEditTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorBlack));
                ViewCompat.setBackgroundTintList(mEditTextName, colorStateList);
                return false;
            }
        });
        mImageViewMainPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                getActivity().startActivityForResult(photoPickerIntent, GALLERY_MAIN_PHOTO);
            }
        });
        mImageViewAdditionalPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                getActivity().startActivityForResult(photoPickerIntent, GALLERY_ADDITIONAL_PHOTO_1);
            }
        });
        mImageViewAdditionalPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                getActivity().startActivityForResult(photoPickerIntent, GALLERY_ADDITIONAL_PHOTO_2);
            }
        });
        mImageViewAdditionalPhoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                getActivity().startActivityForResult(photoPickerIntent, GALLERY_ADDITIONAL_PHOTO_3);
            }
        });
    }

    public void setPhoto(int number, File photo){
        switch (number){
            case GALLERY_MAIN_PHOTO:
                mTextViewAddPhoto.setVisibility(View.GONE);
                mMainPhoto = photo;
                setMainPhoto(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_1:
                mAdditionalPhoto1 = photo;
                setAdditionalPhoto1(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_2:
                mAdditionalPhoto2 = photo;
                setAdditionalPhoto2(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_3:
                mAdditionalPhoto3 = photo;
                setAdditionalPhoto3(photo);
                break;
        }
    }

    private void setMainPhoto(File file){
        Glide
                .with(getContext())
                .load(file)
                .into(mImageViewMainPhoto);
    }

    private void setAdditionalPhoto1(File file){
        Glide
                .with(getContext())
                .load(file)
                .into(mImageViewAdditionalPhoto1);
    }

    private void setAdditionalPhoto2(File file){
        Glide
                .with(getContext())
                .load(file)
                .into(mImageViewAdditionalPhoto2);
    }

    private void setAdditionalPhoto3(File file){
        Glide
                .with(getContext())
                .load(file)
                .into(mImageViewAdditionalPhoto3);
    }

    @Override
    public boolean isCorrect() {
        boolean isCorrect = true;
        if(mMainPhoto == null && mAdditionalPhoto1 == null && mAdditionalPhoto2 == null && mAdditionalPhoto3 == null){
            Toast.makeText(getContext(), getString(R.string.select_photo), Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        if(mEditTextName.getText().toString().equals("")){
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorRed));
            ViewCompat.setBackgroundTintList(mEditTextName, colorStateList);
            isCorrect = false;
        }
        return isCorrect;
    }

    public String getName(){
        return mEditTextName.getText().toString();
    }
}