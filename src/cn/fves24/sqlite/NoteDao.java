package cn.fves24.sqlite;

import cn.fves24.vo.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Note dao
 *
 * @author fves
 */
public class NoteDao {

    public void init() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = SqliteUtils.getConnection();
            if (connection != null) {
                statement = connection.createStatement();
                statement.execute("create table if not exists note " +
                        "(" +
                        "id integer primary key autoincrement," +
                        "title text unique," +
                        "content text," +
                        "del integer default 0," +
                        "createTime datetime default (datetime( 'now', 'localtime' ))" +
                        ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveNote(Note note) {
        Connection connection = SqliteUtils.getConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("insert into note(title,content,del) values (?,?,?)");
                ps.setString(1, note.getTitle());
                ps.setString(2, note.getContent());
                ps.setInt(3, 0);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateNote(Note note) {
        Connection connection = SqliteUtils.getConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("update note set title = ?, content=? where id = ?");
                ps.setString(1, note.getTitle());
                ps.setString(2, note.getContent());
                ps.setInt(3, note.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteNote(String title) {
        Connection connection = SqliteUtils.getConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("update note set del = 1 where title = ?");
                ps.setString(1, title);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanNote() {
        Connection connection = SqliteUtils.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("update note set del = 2,title = '$del_' || title where del = 1");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Note> getNotes() {
        Connection connection = SqliteUtils.getConnection();
        List<Note> noteList = new ArrayList<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id,title,content,del,createTime from note");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    int del = resultSet.getInt("del");
                    String createTime = resultSet.getString("createTime");

                    Note note = new Note();
                    note.setId(id);
                    note.setTitle(title);
                    note.setContent(content);
                    note.setDel(del);
                    note.setCreateTime(createTime);

                    noteList.add(note);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return noteList;
    }
}
