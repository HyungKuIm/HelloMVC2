package com.oraclejava.hellomvc2.services;

import com.oraclejava.hellomvc2.pojos.Music;

import java.util.List;

public interface MusicService {

    List<Music> getMusicList();
    void addMusic(Music music);
    Music getMusicByMid(Long mid);
    void delMusic(Long mid);
    void updateMusic(Music music);
}
