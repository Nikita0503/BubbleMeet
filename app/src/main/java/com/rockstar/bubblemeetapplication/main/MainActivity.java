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
import com.rockstar.bubblemeetapplication.Inbox.InboxFragment;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.bubble.BubbleFragment;
import com.rockstar.bubblemeetapplication.bubble.*;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.likes.LikesFragment;
import com.rockstar.bubblemeetapplication.matches.MatchesFragment;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.my_profile.MyProfileActivity;
import com.rockstar.bubblemeetapplication.watchers.WatchersFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseContract.BaseView {

    FragmentManager mFragmentManager;
    MainPresenter mPresenter;

    ImageView mImageViewBack;
    ImageView mImageViewFilters;
    ImageView mImageViewProfile;
    ImageView mImageViewMatches;
    ImageView mImageViewLikes;
    ImageView mImageViewBubble;
    ImageView mImageViewWatchers;
    ImageView mImageViewInbox;

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
        mPresenter.fetchAllUsers();
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
                startActivity(intent);
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
                //BubbleFragment bubbleFragment = new BubbleFragment();
                //transaction.replace(R.id.root_fragment, bubbleFragment);

                BubbleFragment2 bubbleFragment = new BubbleFragment2();
                ArrayList<UserData> users = new ArrayList<UserData>();
                for(int i = 0; i < 700; i++){
                    if(i % 2 != 0) {
                        users.add(new UserData(i, "Name" + i, "one's city", "https://i.citrus.ua/uploads/content/product-photos/fedenicheva/April/image.jpg"));
                    }else {
                        users.add(new UserData(i, "Name" + i, "one's city", "https://cdn.wccftech.com/wp-content/uploads/2017/10/WCCFgabenewell-740x429.jpg"));
                    }
                }
                bubbleFragment.setUsers(users);
                transaction.replace(R.id.root_fragment, bubbleFragment);

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
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        BubbleFragment2 bubbleFragment = new BubbleFragment2();
        ArrayList<UserData> users = new ArrayList<UserData>();
        for(int i = 0; i < 700; i++){
            if(i % 2 != 0) {
                users.add(new UserData(i, "Vault Boy" + i, "one's city", "https://i.citrus.ua/uploads/content/product-photos/fedenicheva/April/image.jpg"));
            }else {
                users.add(new UserData(i, "Gaben" + i, "one's city", "https://cdn.wccftech.com/wp-content/uploads/2017/10/WCCFgabenewell-740x429.jpg"));
            }
        }
        bubbleFragment.setUsers(users);
        transaction.replace(R.id.root_fragment, bubbleFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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