package com.oraclejava.hellomvc2.services;

import ServletStudy.BaseDAO;
import com.oraclejava.hellomvc2.pojos.Music;

import java.util.List;

public class MusicDaoImpl extends BaseDAO<Music> implements MusicService{
    @Override
    public List<Music> getMusicList() {
        return executeQuery("select * from tb_music");
    }

    @Override
    public void addMusic(Music music) {
        String sql = "insert into tb_music(id, title, artist, album, genre) " +
                "values (seq_music.nextval, ?, ?, ?, ?)";
        executeUpdate(sql, music.getTitle(), music.getArtist(), music.getAlbum(), music.getGenre());
    }

    @Override
    public Music getMusicByMid(Long mid) {
        String sql = "select * from tb_music where id = ?";
        return load(sql, mid);
    }

    @Override
    public void delMusic(Long mid) {
        String sql = "delete from tb_music where id = ?";
        executeUpdate(sql, mid);
    }

    @Override
    public void updateMusic(Music music) {
        String sql = "update tb_music set title = ?, artist = ?, album = ?, genre = ? where id = ?";
        executeUpdate(sql, music.getTitle(), music.getArtist(), music.getAlbum(), music.getGenre(), music.getId());
    }
}
