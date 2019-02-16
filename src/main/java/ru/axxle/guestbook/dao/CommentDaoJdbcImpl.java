package ru.axxle.guestbook.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.axxle.guestbook.pojo.Comment;

import javax.sql.DataSource;
import java.util.List;

/**
 * Реализация DAO на основе Spring JdbcTemplate
 */
public class CommentDaoJdbcImpl implements CommentDao{
    private static final String CREATE_QUERY = "insert into COMMENTS (NAME, COMMENT) values (?, ?)";
    private static final String GET_ALL_QUERY = "select ID, NAME, COMMENT from COMMENTS";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Comment comment) {
        jdbcTemplate.update(CREATE_QUERY, comment.getName(), comment.getText());
    }

    @Override
    public List<Comment> getAll() {
        return jdbcTemplate.query(
                GET_ALL_QUERY, new Object[] {},
                (rs, rowNum) -> new Comment(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("COMMENT"))
        );
    }

}
