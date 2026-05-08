public abstract class Koleksi {
    private int id;
    private String judul;
    private int tahunTerbit;
    private int jumlahStok;

    public Koleksi(String judul, int tahunTerbit, int jumlahStok)
            throws DataKoleksiTidakValidException {
        setJudul(judul);
        setTahunTerbit(tahunTerbit);
        setJumlahStok(jumlahStok);
    }

    public Koleksi(int id, String judul, int tahunTerbit, int jumlahStok)
            throws DataKoleksiTidakValidException {
        this.id = id;
        setJudul(judul);
        setTahunTerbit(tahunTerbit);
        setJumlahStok(jumlahStok);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) throws DataKoleksiTidakValidException {
        if (judul == null || judul.trim().isEmpty()) {
            throw new DataKoleksiTidakValidException("Judul koleksi tidak boleh kosong.");
        }

        this.judul = judul;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) throws DataKoleksiTidakValidException {
        if (tahunTerbit <= 0) {
            throw new DataKoleksiTidakValidException("Tahun terbit harus lebih dari nol.");
        }

        this.tahunTerbit = tahunTerbit;
    }

    public int getJumlahStok() {
        return jumlahStok;
    }

    public void setJumlahStok(int jumlahStok) throws DataKoleksiTidakValidException {
        if (jumlahStok < 0) {
            throw new DataKoleksiTidakValidException("Jumlah stok tidak boleh bernilai negatif.");
        }

        this.jumlahStok = jumlahStok;
    }

    public abstract String getJenis();

    public abstract void tampilkanInfo();
}