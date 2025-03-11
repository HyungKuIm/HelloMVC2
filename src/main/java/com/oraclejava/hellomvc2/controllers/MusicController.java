package com.oraclejava.hellomvc2.controllers;

import com.oraclejava.hellomvc2.pojos.Music;
import com.oraclejava.hellomvc2.services.MusicService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MusicController {

    private final static Logger logger = LoggerFactory.getLogger(MusicController.class);

    private MusicService musicService;

    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();


        int pageNo;
        try {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        } catch (NumberFormatException e) {
            pageNo = 1;
        }

        session.setAttribute("pageNo", pageNo);


        List<Music> musicList = musicService.getMusicList(pageNo);
        session.setAttribute("musicList", musicList);

        int pageCount = musicService.getPageCount();
        session.setAttribute("pageCount", pageCount);

        return "music/index";
    }

    public String addForm() {
        return "music/addForm";
    }

    public String addMusic(
            HttpServletRequest request) {
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String album = request.getParameter("album");
        Music music = new Music(title, artist, album, genre);
        logger.debug("2:" + music);
        musicService.addMusic(music);
        return "redirect:music.do";
    }

    public String show(HttpServletRequest request) {
        Integer musicId = null;
        try {
            musicId = Integer.parseInt(request.getParameter("musicId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (musicId != null) {
            Music music = musicService.getMusicByMid(Long.valueOf(musicId));
            logger.debug(music.toString());
            request.setAttribute("music", music);
            return "music/show";
        } else {
            return "error";
        }

    }
}
