package com.oraclejava.hellomvc2.controllers;

import com.oraclejava.hellomvc2.pojos.Music;
import com.oraclejava.hellomvc2.services.MusicService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class MusicController {

    private MusicService musicService;

    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<Music> musicList = musicService.getMusicList();
        session.setAttribute("musicList", musicList);

        return "music/index";
    }
}
