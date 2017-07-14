package com.wang.neteasemusic.ui.local;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.neteasemusic.R;
import com.wang.neteasemusic.data.Song;
import com.wang.neteasemusic.model.LocalIview;
import com.wang.neteasemusic.music.MusicPlaylist;
import com.wang.neteasemusic.presenter.LocalLibraryPresenter;
import com.wang.neteasemusic.service.MusicPlayerManager;
import com.wang.neteasemusic.ui.adapter.LocalRecyclerViewAdapter;
import com.wang.neteasemusic.ui.music.BaseFragment;

import java.util.List;


/**
 * vv本地音乐
 */
public class LocalMusicFragment extends BaseFragment implements LocalIview.LocalMusic {

    View view;
    private LocalLibraryPresenter libraryPresenter;
    private RecyclerView mRecyclerView;
    private LocalRecyclerViewAdapter adapter;
    private MusicPlaylist musicPlaylist;

    public LocalMusicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryPresenter = new LocalLibraryPresenter(this, getActivity());
        musicPlaylist = new MusicPlaylist();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_localmusic, container, false);

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.local_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LocalRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
        libraryPresenter.requestMusic();
        adapter.setOnItemClickListener(new LocalRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MusicPlayerManager.get().playQueue(musicPlaylist,position);
                //好多地方都需要跳转到播放界面  抽离出来
                gotoSongPlayerActivity();

            }
        });


    }


    @Override
    public void getLocalMusic(List<Song> songs) {
        musicPlaylist.addQueue(songs, true);
        musicPlaylist.setTitle("本地音乐");
        adapter.setData(songs);
    }


}
