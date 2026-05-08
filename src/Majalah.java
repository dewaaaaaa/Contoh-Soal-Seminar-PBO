public class Majalah extends Koleksi {
    private String edisi;
    private String bulanTerbit;
    private String kategori;

    public Majalah(String judul, int tahunTerbit, int jumlahStok,
                   String edisi, String bulanTerbit, String kategori)
            throws DataKoleksiTidakValidException {
        super(judul, tahunTerbit, jumlahStok);
        setDetailMajalah(edisi, bulanTerbit, kategori);
    }

    public Majalah(int id, String judul, int tahunTerbit, int jumlahStok,
                   String edisi, String bulanTerbit, String kategori)
            throws DataKoleksiTidakValidException {
        super(id, judul, tahunTerbit, jumlahStok);
        setDetailMajalah(edisi, bulanTerbit, kategori);
    }

    public void setDetailMajalah(String edisi, String bulanTerbit, String kategori)
            throws DataKoleksiTidakValidException {

        boolean edisiKosong = edisi == null || edisi.trim().isEmpty();
        boolean bulanKosong = bulanTerbit == null || bulanTerbit.trim().isEmpty();
        boolean kategoriKosong = kategori == null || kategori.trim().isEmpty();

        if (edisiKosong && bulanKosong && kategoriKosong) {
            throw new DataKoleksiTidakValidException(
                    "Majalah harus memiliki informasi edisi, bulan terbit, atau kategori."
            );
        }

        if (edisiKosong) {
            throw new DataKoleksiTidakValidException("Edisi majalah tidak boleh kosong.");
        }

        this.edisi = edisi;
        this.bulanTerbit = bulanTerbit;
        this.kategori = kategori;
    }

    public String getEdisi() {
        return edisi;
    }

    public String getBulanTerbit() {
        return bulanTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    @Override
    public String getJenis() {
        return "MAJALAH";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("ID           : " + getId());
        System.out.println("Judul        : " + getJudul());
        System.out.println("Tahun Terbit : " + getTahunTerbit());
        System.out.println("Jumlah Stok  : " + getJumlahStok());
        System.out.println("Jenis        : " + getJenis());
        System.out.println("Edisi        : " + edisi);
        System.out.println("Bulan Terbit : " + bulanTerbit);
        System.out.println("Kategori     : " + kategori);
        System.out.println("--------------------------------");
    }
}