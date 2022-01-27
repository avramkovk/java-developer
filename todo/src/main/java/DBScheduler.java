import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBScheduler implements Closeable {
    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=12345";
    private static final String IN_PROGRESS = "in progress";
    private static final String DONE = "done";

    private Connection conn;

    public DBScheduler() {
        try {
            conn = DriverManager.getConnection(URL);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new IOException("close fail", e);
        }
    }

    public int createTask(String name) {
        final String QUERY = "insert into todo(name) values(?) RETURNING id;";
        try (PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int num = rs.getInt(1);
            rs.close();
            return num;
        } catch (SQLException e) {
            throw new IllegalStateException("Fail to insert values to table", e);
        }
    }

    public List<Task> getTasksInProgress() {
        return getTasks(IN_PROGRESS);
    }

    public List<Task> getTasksDone() {
        return getTasks(DONE);
    }

    private List<Task> getTasks(String status) {
        final String QUERY = "select id,name,status from todo where status=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            List<Task> taskInProgress = new ArrayList<>();
            while (rs.next()) {
                taskInProgress.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            return taskInProgress;
        } catch (SQLException e) {
            throw new IllegalStateException("fail to get task", e);
        }
    }

    public boolean markAsDone(int taskId) {
        final String QUERY = "UPDATE todo SET status = 'done' WHERE id = ? AND status = 'in progress';";
        try (PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setInt(1, taskId);
            int countUpdate = pstmt.executeUpdate();
            return countUpdate == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("fail to mark task in done", e);
        }
    }

    private void createTableIfNotExists() {
        String query = "create table if not exists todo(id SERIAL primary key, name TEXT NOT NULL,status TEXT NOT NULL DEFAULT 'in progress');";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            throw new IllegalStateException("Fail to create table", e);
        }
    }
}