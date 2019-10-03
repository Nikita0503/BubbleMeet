package com.rockstar.bubblemeetapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.CircleTransform;
import com.rockstar.bubblemeetapplication.Inbox.InboxFragment;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.bubble.BubbleFragment;
import com.rockstar.bubblemeetapplication.bubble.*;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.likes.LikesFragment;
import com.rockstar.bubblemeetapplication.matches.MatchesFragment;
import com.rockstar.bubblemeetapplication.model.data.Filter;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.my_profile.MyProfileActivity;
import com.rockstar.bubblemeetapplication.watchers.WatchersFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseContract.BaseView {

    private FragmentManager mFragmentManager;
    private MainPresenter mPresenter;

    private ImageView mImageViewBack;
    private ImageView mImageViewFilters;
    private ImageView mImageViewProfile;
    private ImageView mImageViewMatches;
    private ImageView mImageViewLikes;
    private ImageView mImageViewBubble;
    private ImageView mImageViewWatchers;
    private ImageView mImageViewInbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void initViews() {
        mImageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
            }
        });
        mImageViewFilters = (ImageView) findViewById(R.id.imageViewFilters);
        mImageViewFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FiltersActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        mImageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
        mImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
            }
        });

        mFragmentManager = getSupportFragmentManager();
        mImageViewMatches = (ImageView) findViewById(R.id.imageViewMatches);
        mImageViewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButtonFilters();
                resetMenuIcons();
                mImageViewMatches.setImageDrawable(getResources().getDrawable(R.drawable.ic_selected_profile_icon));
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                MatchesFragment matchesFragment = new MatchesFragment();
                transaction.replace(R.id.root_fragment, matchesFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mImageViewLikes = (ImageView) findViewById(R.id.imageViewLikes);
        mImageViewLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButtonFilters();
                resetMenuIcons();
                mImageViewLikes.setImageDrawable(getResources().getDrawable(R.drawable.ic_selected_likes));
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                LikesFragment likesFragment = new LikesFragment();
                transaction.replace(R.id.root_fragment, likesFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mImageViewBubble = (ImageView) findViewById(R.id.imageViewBubble);
        mImageViewBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showButtonFilters();
                resetMenuIcons();
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                BubbleFragment2 fragmentBubbles = new BubbleFragment2();
                fragmentBubbles.setFilter(null);
                fragmentBubbles.setUsers(mPresenter.getUsers());
                transaction.replace(R.id.root_fragment, fragmentBubbles);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mImageViewWatchers = (ImageView) findViewById(R.id.imageViewWatchers);
        mImageViewWatchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButtonFilters();
                resetMenuIcons();
                mImageViewWatchers.setImageDrawable(getResources().getDrawable(R.drawable.ic_selected_views));
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                WatchersFragment fragmentWatchers = new WatchersFragment();
                transaction.replace(R.id.root_fragment, fragmentWatchers);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mImageViewInbox = (ImageView) findViewById(R.id.imageViewInbox);
        mImageViewInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButtonFilters();
                resetMenuIcons();
                mImageViewInbox.setImageDrawable(getResources().getDrawable(R.drawable.ic_selected_messages));
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                InboxFragment fragmentInbox = new InboxFragment();
                transaction.replace(R.id.root_fragment, fragmentInbox);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mImageViewMatches.setEnabled(false);
        mImageViewLikes.setEnabled(false);
        mImageViewBubble.setEnabled(false);
        mImageViewWatchers.setEnabled(false);
        mImageViewInbox.setEnabled(false);
        mImageViewProfile.setEnabled(false);
    }

    public void showButtonBack(){
        mImageViewBack.setVisibility(View.VISIBLE);
        mImageViewFilters.setVisibility(View.GONE);
    }

    public void hideButtonBack(){
        mImageViewBack.setVisibility(View.GONE);
    }

    public void showButtonFilters(){
        mImageViewBack.setVisibility(View.GONE);
        mImageViewFilters.setVisibility(View.VISIBLE);
    }

    public void hideButtonFilters(){
        mImageViewFilters.setVisibility(View.GONE);
    }

    public void resetMenuIcons() {
        mImageViewMatches.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile_icon));
        mImageViewLikes.setImageDrawable(getResources().getDrawable(R.drawable.ic_likes));
        mImageViewWatchers.setImageDrawable(getResources().getDrawable(R.drawable.ic_views));
        mImageViewInbox.setImageDrawable(getResources().getDrawable(R.drawable.ic_messages));
    }

    public void setData(UserDataFull userData){
        Picasso.with(getApplicationContext())
                .load("http://185.25.116.211:11000/image/" + userData.avatarSmall)
                .placeholder(R.drawable.background_loading)
                .transform(new CircleTransform(0))
                .into(mImageViewProfile);
    }

    public void setUsers(ArrayList<UserDataFull> users){
        mImageViewMatches.setEnabled(true);
        mImageViewLikes.setEnabled(true);
        mImageViewBubble.setEnabled(true);
        mImageViewWatchers.setEnabled(true);
        mImageViewInbox.setEnabled(true);
        mImageViewProfile.setEnabled(true);
        mImageViewFilters.setVisibility(View.VISIBLE);
        showButtonFilters();
        resetMenuIcons();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        BubbleFragment2 fragmentBubbles = new BubbleFragment2();
        fragmentBubbles.setFilter(null);
        fragmentBubbles.setUsers(users);
        transaction.replace(R.id.root_fragment, fragmentBubbles);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            BubbleFragment2 fragmentBubbles = new BubbleFragment2();
            fragmentBubbles.setFilter(null);
            fragmentBubbles.setUsers(mPresenter.getUsers());
            transaction.replace(R.id.root_fragment, fragmentBubbles);
            //transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
            return;
        }
        Log.d("Filter", "OK");
        Filter filter = new Filter();
        filter.gender = data.getStringExtra("gender");
        filter.age = data.getStringExtra("age");
        filter.distance = data.getStringExtra("distance");
        filter.eyeColor = data.getStringExtra("eyeColor");
        filter.height = data.getStringExtra("height");
        filter.smoking = data.getStringExtra("smoking");
        filter.married = data.getStringExtra("married");
        filter.children = data.getStringExtra("children");
        filter.lookingFor = data.getStringExtra("lookingFor");
        filter.loveToCook = data.getStringExtra("loveToCook");
        showButtonFilters();
        resetMenuIcons();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        BubbleFragment2 fragmentBubbles = new BubbleFragment2();
        fragmentBubbles.setFilter(filter);
        fragmentBubbles.setUsers(mPresenter.getUsers());
        transaction.replace(R.id.root_fragment, fragmentBubbles);
        //transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}