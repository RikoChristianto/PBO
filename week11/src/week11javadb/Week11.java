package week11javadb;

import java.sql.*;
import java.util.*;

public class Week11 {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/perpus";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static PreparedStatement pstmt;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize connection
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Call choice method
            choice();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void choice() {
        try {
            // Create a menu for user choices
            int choice = 0;
            while (choice != 8) {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Tambah data buku");
                System.out.println("2. Edit data buku");
                System.out.println("3. Hapus data buku");
                System.out.println("4. Tambah data penulis");
                System.out.println("5. Edit data penulis");
                System.out.println("6. Hapus data penulis");
                System.out.println("7. Tampilkan semua tabel");
                System.out.println("8. Keluar");
                System.out.print("Pilihan Anda: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                } else {
                    System.out.println("Input tidak valid. Masukkan angka dari 1 hingga 8.");
                    scanner.next(); // Consume invalid input
                    continue;
                }

                // Process user choice
                switch (choice) {
                    case 1:
                        insert_buku();
                        break;
                    case 2:
                        edit_buku();
                        break;
                    case 3:
                        delete_buku();
                        break;
                    case 4:
                        insert_penulis();
                        break;
                    case 5:
                        edit_penulis();
                        break;
                    case 6:
                        delete_penulis();
                        break;
                    case 7:
                        show();
                        break;
                    case 8:
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Masukkan angka dari 1 hingga 8.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        try {
            // Show contents of table 'buku'
            System.out.println("=== Tabel Buku ===");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM buku");

            boolean bukuIsEmpty = true;
            while (rs.next()) {
                bukuIsEmpty = false;
                int id = rs.getInt("id");
                String judul = rs.getString("judul_buku");
                int tahun = rs.getInt("tahun_terbit");
                int stok = rs.getInt("stok");
                int penulis_id = rs.getInt("penulis_id");
                System.out.println("ID: " + id + ", Judul: " + judul + ", Tahun Terbit: " + tahun + ", Stok: " + stok + ", Penulis ID: " + penulis_id);
            }

            if (bukuIsEmpty) {
                System.out.println("Tabel buku kosong");
            }

            // Show contents of table 'penulis'
            System.out.println("\n=== Tabel Penulis ===");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM penulis");

            boolean penulisIsEmpty = true;
            while (rs.next()) {
                penulisIsEmpty = false;
                int id = rs.getInt("id");
                String nama = rs.getString("nama_penulis");
                System.out.println("ID: " + id + ", Nama Penulis: " + nama);
            }

            if (penulisIsEmpty) {
                System.out.println("Tabel penulis kosong");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    public static void insert_buku() {
        try {
            // Meminta input dari pengguna
            System.out.print("Masukkan judul buku: ");
            String judul = scanner.nextLine();

            System.out.print("Masukkan tahun terbit: ");
            int tahun = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            System.out.print("Masukkan stok: ");
            int stok = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            System.out.print("Masukkan ID penulis: ");
            int penulis_id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // SQL query untuk insert data buku
            String sql = "INSERT INTO buku (judul_buku, tahun_terbit, stok, penulis_id) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Set parameter
            pstmt.setString(1, judul);
            pstmt.setInt(2, tahun);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, penulis_id);

            // Execute query
            pstmt.executeUpdate();

            System.out.println("Data buku berhasil ditambahkan.");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void edit_buku() {
        try {
            // Meminta input dari pengguna untuk ID buku yang akan diedit
            System.out.print("Masukkan ID buku yang akan diedit: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // Meminta input data baru dari pengguna
            System.out.print("Masukkan judul buku baru: ");
            String judul = scanner.nextLine();

            System.out.print("Masukkan tahun terbit baru: ");
            int tahun = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            System.out.print("Masukkan stok baru: ");
            int stok = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            System.out.print("Masukkan ID penulis baru: ");
            int penulis_id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // SQL query untuk update data buku
            String sql = "UPDATE buku SET judul_buku = ?, tahun_terbit = ?, stok = ?, penulis_id = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);

            // Set parameter
            pstmt.setString(1, judul);
            pstmt.setInt(2, tahun);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, penulis_id);
            pstmt.setInt(5, id);

            // Execute query
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data buku berhasil diperbarui.");
            } else {
                System.out.println("Data buku tidak ditemukan.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void delete_buku() {
        try {
            // Meminta input dari pengguna untuk ID buku yang akan dihapus
            System.out.print("Masukkan ID buku yang akan dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // SQL query untuk delete data buku
            String sql = "DELETE FROM buku WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            // Execute query
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data buku berhasil dihapus.");
            } else {
                System.out.println("Data buku tidak ditemukan.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void insert_penulis() {
        try {
            // Meminta input dari pengguna
            System.out.print("Masukkan nama penulis: ");
            String nama = scanner.nextLine();

            // SQL query untuk insert data penulis
            String sql = "INSERT INTO penulis (nama_penulis) VALUES (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nama);

            // Execute query
            pstmt.executeUpdate();

            System.out.println("Data penulis berhasil ditambahkan.");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void edit_penulis() {
        try {
            // Meminta input dari pengguna untuk ID penulis yang akan diedit
            System.out.print("Masukkan ID penulis yang akan diedit: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // Meminta input data baru dari pengguna
            System.out.print("Masukkan nama penulis baru: ");
            String nama = scanner.nextLine();

            // SQL query untuk update data penulis
            String sql = "UPDATE penulis SET nama_penulis = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nama);
            pstmt.setInt(2, id);

            // Execute query
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data penulis berhasil diperbarui.");
            } else {
                System.out.println("Data penulis tidak ditemukan.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void delete_penulis() {
        try {
            // Meminta input dari pengguna untuk ID penulis yang akan dihapus
            System.out.print("Masukkan ID penulis yang akan dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            // SQL query untuk delete data penulis
            String sql = "DELETE FROM penulis WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            // Execute query
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data penulis berhasil dihapus.");
            } else {
                System.out.println("Data penulis tidak ditemukan.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}