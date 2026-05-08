public interface CrudService {
    void tambah(Koleksi koleksi) throws KoneksiDatabaseException;

    void tampilkan() throws KoneksiDatabaseException;

    void ubah(int id, Koleksi koleksi)
            throws KoneksiDatabaseException, KoleksiTidakDitemukanException;

    void hapus(int id)
            throws KoneksiDatabaseException, KoleksiTidakDitemukanException;
}