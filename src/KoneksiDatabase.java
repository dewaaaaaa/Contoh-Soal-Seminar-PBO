import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {
    public static String urlDatabase = "jdbc:mysql://localhost:3306/";
    public static String username = "root";
    public static String password = "";
    public static String namaDatabase = "perpustakaan_db";
    public static String driverDatabase = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws KoneksiDatabaseException {
        try {
            Class.forName(driverDatabase);

            return DriverManager.getConnection(
                    urlDatabase + namaDatabase,
                    username,
                    password
            );

        } catch (ClassNotFoundException e) {
            throw new KoneksiDatabaseException("Driver database tidak ditemukan.");
        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Koneksi database gagal: " + e.getMessage());
        }
    }
}