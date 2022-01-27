import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBCoffeeMachine {
    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=12345";
    private static Connection conn;

    public DBCoffeeMachine() {
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuantityProduct(String productName, int volume) {
        String sql = "UPDATE state SET quantity=quantity+? WHERE name=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, volume);
            pstmt.setString(2, productName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRemainings(Map<String, Integer> coffeeComponents) {
        final String query = "UPDATE state SET quantity=quantity-? WHERE name=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Map.Entry<String, Integer> entry : coffeeComponents.entrySet()) {
                pstmt.setInt(1, entry.getValue());
                pstmt.setString(2, entry.getKey());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int takeCash() {
        final String query = "SELECT quantity from state where name='cash' for update";
        final String sql = "UPDATE state SET quantity=0 WHERE name='cash';";
        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int cash = 0;
            if (rs.next()) {
                cash = rs.getInt(1);
            }
            rs.close();
            stmt.executeUpdate(sql);
            conn.commit();
            return cash;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return 0;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Integer> getRemainings() {
        final String query = "SELECT name, quantity FROM state ORDER BY name";
        Map<String, Integer> data = new HashMap<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                data.put(rs.getString(1), rs.getInt(2));
            }
            return data;
        } catch (SQLException e) {
            throw new IllegalStateException("fail to load remainings", e);
        }
    }

    public Map<String, String> getProductsItem() {
        final String query = "SELECT name,unit FROM unit_product";
        Map<String, String> data = new HashMap<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                data.put(rs.getString(1), rs.getString(2));
            }
            return data;
        } catch (SQLException e) {
            throw new IllegalStateException("fail to load items", e);
        }
    }

    public List<Coffee> getDrinks() {
        final String query = "SELECT name, product, quantity FROM drinks ORDER BY name";
        List<Coffee> drinks = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            String coffeeName = null;
            Map<String, Integer> components = new HashMap<>();
            while (rs.next()) {
                if (coffeeName != null && !rs.getString(1).equals(coffeeName)) {
                    drinks.add(new Coffee(coffeeName, components));
                    components = new HashMap<>();
                }
                coffeeName = rs.getString(1);
                components.put(rs.getString(2), rs.getInt(3));
            }
            drinks.add(new Coffee(coffeeName, components));
            return drinks;
        } catch (SQLException e) {
            throw new IllegalStateException("failed to load drinks", e);
        }
    }

    private void createTableState() {
        String sql = "CREATE TABLE IF NOT EXISTS state(name TEXT NOT NULL, quantity INTEGER NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("failed crate table state", e);
        }
    }

    public void initTestTables() {
        createTableState();
        insertProductsInTableState();
        createTableUnitProduct();
        insertUnitInTableUnitProduct();
        createTableDrinks();
        insertDrinksInTableDrinks();
    }

    private void insertProductsInTableState() {
        String sql = "INSERT INTO state(name,quantity) VALUES(?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "beans");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "cacao");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "cash");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "cups");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "milk");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "sugar");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.setString(1, "water");
            pstmt.setInt(2, 0);
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableDrinks() {
        String sql = "CREATE TABLE IF NOT EXISTS drinks(name TEXT NOT NULL, product TEXT NOT NULL, quantity INTEGER)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("failed create table drinks", e);
        }
    }

    private void insertDrinksInTableDrinks() {
        String sql = "INSERT INTO drinks(name, product, quantity) VALUES (?,?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "water");
            pstmt.setInt(3, 250);
            pstmt.addBatch();
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "milk");
            pstmt.addBatch();
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "beans");
            pstmt.setInt(3, 16);
            pstmt.addBatch();
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "sugar");
            pstmt.setInt(3, 10);
            pstmt.addBatch();
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "cups");
            pstmt.setInt(3, 1);
            pstmt.addBatch();
            pstmt.setString(1, "espresso");
            pstmt.setString(2, "cash");
            pstmt.setInt(3, -4);
            pstmt.addBatch();

            pstmt.setString(1, "latte");
            pstmt.setString(2, "water");
            pstmt.setInt(3, 350);
            pstmt.addBatch();
            pstmt.setString(1, "latte");
            pstmt.setString(2, "milk");
            pstmt.setInt(3, 75);
            pstmt.addBatch();
            pstmt.setString(1, "latte");
            pstmt.setString(2, "beans");
            pstmt.setInt(3, 20);
            pstmt.addBatch();
            pstmt.setString(1, "latte");
            pstmt.setString(2, "sugar");
            pstmt.setInt(3, 10);
            pstmt.addBatch();
            pstmt.setString(1, "latte");
            pstmt.setString(2, "cups");
            pstmt.setInt(3, 1);
            pstmt.addBatch();
            pstmt.setString(1, "latte");
            pstmt.setString(2, "cash");
            pstmt.setInt(3, -7);
            pstmt.addBatch();

            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "water");
            pstmt.setInt(3, 200);
            pstmt.addBatch();
            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "milk");
            pstmt.setInt(3, 100);
            pstmt.addBatch();
            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "beans");
            pstmt.setInt(3, 12);
            pstmt.addBatch();
            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "sugar");
            pstmt.setInt(3, 10);
            pstmt.addBatch();
            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "cups");
            pstmt.setInt(3, 1);
            pstmt.addBatch();
            pstmt.setString(1, "cappuccino");
            pstmt.setString(2, "cash");
            pstmt.setInt(3, -6);
            pstmt.addBatch();

            pstmt.setString(1, "chocolate");
            pstmt.setString(2, "milk");
            pstmt.setInt(3, 100);
            pstmt.addBatch();
            pstmt.setString(1, "chocolate");
            pstmt.setString(2, "sugar");
            pstmt.setInt(3, 10);
            pstmt.addBatch();
            pstmt.setString(1, "chocolate");
            pstmt.setString(2, "cacao");
            pstmt.setInt(3, 10);
            pstmt.addBatch();
            pstmt.setString(1, "chocolate");
            pstmt.setString(2, "cups");
            pstmt.setInt(3, 1);
            pstmt.addBatch();
            pstmt.setString(1, "chocolate");
            pstmt.setString(2, "cash");
            pstmt.setInt(3, -3);
            pstmt.addBatch();

            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableUnitProduct() {
        String sql = "CREATE TABLE IF NOT EXISTS unit_product(name TEXT NOT NULL, unit TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("failed crate table unit_product", e);
        }
    }

    private void insertUnitInTableUnitProduct() {
        String sql = "INSERT INTO unit_product(name,unit) values(?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "water");
            pstmt.setString(2, "ml");
            pstmt.addBatch();
            pstmt.setString(1, "milk");
            pstmt.setString(2, "ml");
            pstmt.addBatch();
            pstmt.setString(1, "beans");
            pstmt.setString(2, "gr");
            pstmt.addBatch();
            pstmt.setString(1, "cups");
            pstmt.setString(2, "piece");
            pstmt.addBatch();
            pstmt.setString(1, "sugar");
            pstmt.setString(2, "gr");
            pstmt.addBatch();
            pstmt.setString(1, "cash");
            pstmt.setString(2, "usd");
            pstmt.addBatch();
            pstmt.setString(1, "cacao");
            pstmt.setString(2, "gr");
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}