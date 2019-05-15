package com.manishpreet.babamatiji.bani;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.PlaybackQuality;
import com.jaedongchicken.ytplayer.model.YTParams;
import com.manishpreet.babamatiji.R;
import com.manishpreet.babamatiji.home.HomeFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaniFragment extends Fragment {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView tvHeading;
//    private ImageView image;
//    YoutubePlayerView youtubePlayerView;
    VideoView videoView1;
    Button playvideo;

    ProgressDialog dialog;



    public static BaniFragment instance;

    public static BaniFragment getInstance()
    {
        if (instance == null)
        {
            instance = new BaniFragment();
        }
        return instance;
    }


    public BaniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bani, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // youtubePlayerView = view.findViewById(R.id.youtubePlayerView);
       // youtubePlayerView.setAutoPlayerHeight(getActivity());
        drawerLayout = view.findViewById(R.id.bani_drawer_layout);
//        image=view.findViewById(R.id.pic);
        navigationView = view.findViewById(R.id.bani_navigation_view);
        navigationView.bringToFront();
        tvHeading = view.findViewById(R.id.tv_heading);
        videoView1=view.findViewById(R.id.videoview1);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        tvHeading.setText("Jaap Sahib");
        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback.mp4?alt=media&token=73f00dfe-47a9-4fcc-abc5-60488ad07782");
        //        playvideo=view.findViewById(R.id.playvideo);
//        baniTextView.setText(getActivity().getResources().getString(R.string.bani_one));





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                menuItem.setChecked(true);

                switch (menuItem.getItemId())
                {
                    case R.id.bani_1:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback.mp4?alt=media&token=73f00dfe-47a9-4fcc-abc5-60488ad07782");
                        drawerLayout.closeDrawers();
                        break;
                    }


                    case R.id.bani_2:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(1).mp4?alt=media&token=4a042e52-0e86-4ddf-9c32-8e65f8518265");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_3:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(3).mp4?alt=media&token=820a43a9-ef9e-43bc-8819-ee667c073830");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_4:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(2).mp4?alt=media&token=7adc2703-1658-49af-ae13-9943137f48f5");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_5:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(4).mp4?alt=media&token=f05298b9-b2a9-490f-bbf4-3b1d15168f4b");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_6:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(5).mp4?alt=media&token=a88e3ce1-742b-41a7-825f-66f992f1a5df");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_7:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/videoplayback%20(6).mp4?alt=media&token=8b115761-7f2f-4b4c-8c2f-a1b90e4bef8d");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    case R.id.bani_8:
                    {
                        tvHeading.setText(menuItem.getTitle());
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        playBanni("https://firebasestorage.googleapis.com/v0/b/babamatiji-22f9e.appspot.com/o/guruvedio.mp4?alt=media&token=14d50eba-721d-4912-ad43-d05a76d0fd1a");
                        drawerLayout.closeDrawers();
                        break;
                    }

                    default:
                    {
                        return false;
                    }

                }




                return true;
            }
        });
    }
void playBanni(String url)
{


    Log.e("url",url);
    videoView1.setVideoURI(null);
    videoView1.setVisibility(View.VISIBLE);
    MediaController m=new MediaController(getContext());
    m.setAnchorView(videoView1);
    videoView1.setMediaController(m);
    Uri u=Uri.parse(url);
    videoView1.setVideoURI(u);
    videoView1.requestFocus();
    videoView1.start();

}
//    private void playVideo(String videoId) {
//
//        YTParams params = new YTParams();
//        params.setControls(0);
//        params.setVolume(100);
//        params.setPlaybackQuality(PlaybackQuality.small);
//
//        youtubePlayerView.initializeWithCustomURL(videoId,params, new YoutubePlayerView.YouTubeListener() {
//            @Override
//            public void onReady() {
//
//            }
//
//            @Override
//            public void onStateChange(YoutubePlayerView.STATE state) {
//
//            }
//
//            @Override
//            public void onPlaybackQualityChange(String arg) {
//
//            }
//
//            @Override
//            public void onPlaybackRateChange(String arg) {
//
//            }
//
//            @Override
//            public void onError(String arg) {
//
//            }
//
//            @Override
//            public void onApiChange(String arg) {
//
//            }
//
//            @Override
//            public void onCurrentSecond(double second) {
//
//            }
//
//            @Override
//            public void onDuration(double duration) {
//
//            }
//
//            @Override
//            public void logs(String log) {
//
//            }
//        });
//        youtubePlayerView.play();
//
//    }

    @Override
    public void onPause() {
        super.onPause();
        // pause video when on the background mode.
//        youtubePlayerView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // this is optional but you need.
  //      youtubePlayerView.destroy();
    }
}
