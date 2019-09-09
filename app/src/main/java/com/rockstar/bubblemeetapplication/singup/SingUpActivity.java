package com.rockstar.bubblemeetapplication.singup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.singup.DialogSignUpFragment;
import com.squareup.picasso.Picasso;

import java.io.File;

public class SingUpActivity extends AppCompatActivity {

    DialogSignUpFragment mDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        mDialogFragment = new DialogSignUpFragment();
        mDialogFragment.show(getSupportFragmentManager(), "123");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {
            Uri selectedImage = imageReturnedIntent.getData();
            File file = new File(getRealPathFromUri(getApplicationContext(), selectedImage));
            mDialogFragment.addPhoto(requestCode, file);
        }
    }

    private String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
