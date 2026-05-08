public class Buku extends Koleksi {
    private String penulis;
    private String penerbit;
    private String isbn;

    public Buku(String judul, int tahunTerbit, int jumlahStok,
                String penulis, String penerbit, String isbn)
            throws DataKoleksiTidakValidException {
        super(judul, tahunTerbit, jumlahStok);
        setDetailBuku(penulis, penerbit, isbn);
    }

    public Buku(int id, String judul, int tahunTerbit, int jumlahStok,
                String penulis, String penerbit, String isbn)
            throws DataKoleksiTidakValidException {
        super(id, judul, tahunTerbit, jumlahStok);
        setDetailBuku(penulis, penerbit, isbn);
    }

    public void setDetailBuku(String penulis, String penerbit, String isbn)
            throws DataKoleksiTidakValidException {

        boolean penulisKosong = penulis == null || penulis.trim().isEmpty();
        boolean penerbitKosong = penerbit == null || penerbit.trim().isEmpty();
        boolean isbnKosong = isbn == null || isbn.trim().isEmpty();

        if (penulisKosong && penerbitKosong && isbnKosong) {
            throw new DataKoleksiTidakValidException(
                    "Buku harus memiliki informasi penulis, penerbit, atau ISBN."
            );
        }

        if (penulisKosong) {
            throw new DataKoleksiTidakValidException("Penulis buku tidak boleh kosong.");
        }

        this.penulis = penulis;
        this.penerbit = penerbit;
        this.isbn = isbn;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String getJenis() {
        return "BUKU";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("ID           : " + getId());
        System.out.println("Judul        : " + getJudul());
        System.out.println("Tahun Terbit : " + getTahunTerbit());
        System.out.println("Jumlah Stok  : " + getJumlahStok());
        System.out.println("Jenis        : " + getJenis());
        System.out.println("Penulis      : " + penulis);
        System.out.println("Penerbit     : " + penerbit);
        System.out.println("ISBN         : " + isbn);
        System.out.println("--------------------------------");
    }
}