import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KoleksiService implements CrudService {

    @Override
    public void tambah(Koleksi koleksi) throws KoneksiDatabaseException {
        String sql = "INSERT INTO koleksi " +
                "(judul, tahun_terbit, jumlah_stok, jenis, penulis, penerbit, isbn, edisi, bulan_terbit, kategori) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = KoneksiDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            isiPreparedStatement(ps, koleksi);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Gagal menambah koleksi: " + e.getMessage());
        }
    }

    @Override
    public void tampilkan() throws KoneksiDatabaseException {
        String sql = "SELECT * FROM koleksi";

        try {
            Connection conn = KoneksiDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean adaData = false;

            while (rs.next()) {
                adaData = true;

                Koleksi koleksi = buatKoleksiDariDatabase(rs);
                koleksi.tampilkanInfo();
            }

            if (!adaData) {
                System.out.println("Belum ada koleksi di database.");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Gagal menampilkan koleksi: " + e.getMessage());
        } catch (DataKoleksiTidakValidException e) {
            throw new KoneksiDatabaseException(
                    "Ada data koleksi di database yang tidak valid: " + e.getMessage()
            );
        }
    }

    @Override
    public void ubah(int id, Koleksi koleksi)
            throws KoneksiDatabaseException, KoleksiTidakDitemukanException {

        if (!koleksiAda(id)) {
            throw new KoleksiTidakDitemukanException(
                    "Koleksi dengan ID " + id + " tidak ditemukan."
            );
        }

        String sql = "UPDATE koleksi SET judul=?, tahun_terbit=?, jumlah_stok=?, jenis=?, " +
                "penulis=?, penerbit=?, isbn=?, edisi=?, bulan_terbit=?, kategori=? WHERE id=?";

        try {
            Connection conn = KoneksiDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            isiPreparedStatement(ps, koleksi);
            ps.setInt(11, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Gagal mengubah koleksi: " + e.getMessage());
        }
    }

    @Override
    public void hapus(int id)
            throws KoneksiDatabaseException, KoleksiTidakDitemukanException {

        if (!koleksiAda(id)) {
            throw new KoleksiTidakDitemukanException(
                    "Koleksi dengan ID " + id + " tidak ditemukan."
            );
        }

        String sql = "DELETE FROM koleksi WHERE id=?";

        try {
            Connection conn = KoneksiDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Gagal menghapus koleksi: " + e.getMessage());
        }
    }

    public boolean koleksiAda(int id) throws KoneksiDatabaseException {
        String sql = "SELECT id FROM koleksi WHERE id=?";

        try {
            Connection conn = KoneksiDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            boolean ada = rs.next();

            rs.close();
            ps.close();
            conn.close();

            return ada;

        } catch (SQLException e) {
            throw new KoneksiDatabaseException("Gagal memeriksa koleksi: " + e.getMessage());
        }
    }

    private void isiPreparedStatement(PreparedStatement ps, Koleksi koleksi)
            throws SQLException {

        ps.setString(1, koleksi.getJudul());
        ps.setInt(2, koleksi.getTahunTerbit());
        ps.setInt(3, koleksi.getJumlahStok());
        ps.setString(4, koleksi.getJenis());

        if (koleksi instanceof Buku) {
            Buku buku = (Buku) koleksi;

            ps.setString(5, buku.getPenulis());
            ps.setString(6, buku.getPenerbit());
            ps.setString(7, buku.getIsbn());
            ps.setString(8, null);
            ps.setString(9, null);
            ps.setString(10, null);

        } else if (koleksi instanceof Majalah) {
            Majalah majalah = (Majalah) koleksi;

            ps.setString(5, null);
            ps.setString(6, null);
            ps.setString(7, null);
            ps.setString(8, majalah.getEdisi());
            ps.setString(9, majalah.getBulanTerbit());
            ps.setString(10, majalah.getKategori());
        }
    }

    private Koleksi buatKoleksiDariDatabase(ResultSet rs)
            throws SQLException, DataKoleksiTidakValidException {

        int id = rs.getInt("id");
        String judul = rs.getString("judul");
        int tahunTerbit = rs.getInt("tahun_terbit");
        int jumlahStok = rs.getInt("jumlah_stok");
        String jenis = rs.getString("jenis");

        if (jenis.equalsIgnoreCase("BUKU")) {
            return new Buku(
                    id,
                    judul,
                    tahunTerbit,
                    jumlahStok,
                    rs.getString("penulis"),
                    rs.getString("penerbit"),
                    rs.getString("isbn")
            );
        } else if (jenis.equalsIgnoreCase("MAJALAH")) {
            return new Majalah(
                    id,
                    judul,
                    tahunTerbit,
                    jumlahStok,
                    rs.getString("edisi"),
                    rs.getString("bulan_terbit"),
                    rs.getString("kategori")
            );
        } else {
            throw new DataKoleksiTidakValidException("Jenis koleksi di database tidak valid.");
        }
    }
}