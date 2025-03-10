package com.oraclejava.hellomvc2.services;

import com.oraclejava.base.BaseDAO;
import com.oraclejava.hellomvc2.pojos.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class MusicDaoImpl extends BaseDAO<Music> implements MusicService{

    private static final Logger logger =
            LoggerFactory.getLogger(MusicDaoImpl.class);

    @Override
    public List<Music> getMusicList(Integer pageNo) {
        int pageSize = 10;
        int offset = (pageNo - 1) * pageSize;

        return executeQuery("select * from tb_music order by id offset ? rows fetch first ? row only", offset, pageSize);
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

    @Override
    public Integer getPageCount() {
        String sql = "select count(*) from tb_music";
        logger.debug(sql);
        Integer musicCount = ((BigDecimal)executeComplexQuery(sql)[0]).intValue();
        logger.info("전체 음악수:" + musicCount);
        int pageSize = 10;
        int pageCount = musicCount / pageSize;
        int mod = musicCount % pageSize;
        if (mod > 0) {
            pageCount++;
        }
        logger.info("페이지 수:" + pageCount);
        return pageCount;
    }
}
