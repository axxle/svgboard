package ru.axxle.svgboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.axxle.svgboard.pojo.Board;

import javax.sql.DataSource;

/**
 * Реализация DAO на основе Spring JdbcTemplate
 */
public class BoardDaoJdbcImpl implements BoardDao {
    private static final String UPDATE_QUERY = "update boards set width = ?, height = ?, viewbox = ? where id = ?";
    private static final String BY_ID_QUERY = "select id, width, height, viewbox from boards where id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Board board) {
        jdbcTemplate.update(UPDATE_QUERY, board.getWidth(), board.getHeight(), board.getViewbox(), board.getId());
    }

    @Override
    public Board getById(Long id) {
        //пока заглушка, нужно будет написать запрос
        Board board = (Board)jdbcTemplate.queryForObject(
                BY_ID_QUERY,  new Object[] { id },
                (rs, rowNum) -> new Board(
                        rs.getLong("id"),
                        rs.getString("width"),
                        rs.getString("height"),
                        rs.getString("viewbox")
                )
        );
        //return new Board(1L, "1920", "938", "0 0 1920 938");
        return board;
    }
}
