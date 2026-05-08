import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    static KoleksiService koleksiService = new KoleksiService();

    public static void main(String[] args) {
        int pilihan;

        do {
            tampilkanMenu();
            pilihan = inputInt("Pilih menu: ");

            if (pilihan == 1) {
                tambahKoleksi();
            } else if (pilihan == 2) {
                lihatKoleksi();
            } else if (pilihan == 3) {
                ubahKoleksi();
            } else if (pilihan == 4) {
                hapusKoleksi();
            } else if (pilihan == 5) {
                System.out.println("Program selesai.");
            } else {
                System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 5);
    }

    static void tampilkanMenu() {
        System.out.println();
        System.out.println("=== SISTEM PENGELOLAAN KOLEKSI PERPUSTAKAAN ===");
        System.out.println("1. Tambah Koleksi");
        System.out.println("2. Lihat Koleksi");
        System.out.println("3. Ubah Koleksi");
        System.out.println("4. Hapus Koleksi");
        System.out.println("5. Keluar");
    }

    static void tambahKoleksi() {
        try {
            Koleksi koleksi = inputKoleksi();

            koleksiService.tambah(koleksi);

            System.out.println("Koleksi berhasil ditambahkan.");

        } catch (DataKoleksiTidakValidException e) {
            System.out.println("Data koleksi tidak valid: " + e.getMessage());
        } catch (KoneksiDatabaseException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }
    }

    static void lihatKoleksi() {
        try {
            System.out.println();
            System.out.println("=== DAFTAR KOLEKSI ===");

            koleksiService.tampilkan();

        } catch (KoneksiDatabaseException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }
    }

    static void ubahKoleksi() {
        try {
            int id = inputInt("Masukkan ID koleksi yang akan diubah: ");

            if (!koleksiService.koleksiAda(id)) {
                throw new KoleksiTidakDitemukanException(
                        "Koleksi dengan ID " + id + " tidak ditemukan."
                );
            }

            Koleksi koleksiBaru = inputKoleksi();

            koleksiService.ubah(id, koleksiBaru);

            System.out.println("Koleksi berhasil diubah.");

        } catch (KoleksiTidakDitemukanException e) {
            System.out.println("Koleksi tidak ditemukan: " + e.getMessage());
        } catch (DataKoleksiTidakValidException e) {
            System.out.println("Data koleksi tidak valid: " + e.getMessage());
        } catch (KoneksiDatabaseException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }
    }

    static void hapusKoleksi() {
        try {
            int id = inputInt("Masukkan ID koleksi yang akan dihapus: ");

            koleksiService.hapus(id);

            System.out.println("Koleksi berhasil dihapus.");

        } catch (KoleksiTidakDitemukanException e) {
            System.out.println("Koleksi tidak ditemukan: " + e.getMessage());
        } catch (KoneksiDatabaseException e) {
            System.out.println("Kesalahan database: " + e.getMessage());
        }
    }

    static Koleksi inputKoleksi() throws DataKoleksiTidakValidException {
        System.out.println();
        System.out.println("Pilih Jenis Koleksi");
        System.out.println("1. Buku");
        System.out.println("2. Majalah");

        int jenis = inputInt("Pilih jenis: ");

        System.out.print("Judul koleksi: ");
        String judul = input.nextLine();

        int tahunTerbit = inputInt("Tahun terbit: ");
        int jumlahStok = inputInt("Jumlah stok: ");

        if (jenis == 1) {
            System.out.print("Penulis buku: ");
            String penulis = input.nextLine();

            System.out.print("Penerbit buku: ");
            String penerbit = input.nextLine();

            System.out.print("ISBN buku: ");
            String isbn = input.nextLine();

            return new Buku(judul, tahunTerbit, jumlahStok, penulis, penerbit, isbn);

        } else if (jenis == 2) {
            System.out.print("Edisi majalah: ");
            String edisi = input.nextLine();

            System.out.print("Bulan terbit majalah: ");
            String bulanTerbit = input.nextLine();

            System.out.print("Kategori majalah: ");
            String kategori = input.nextLine();

            return new Majalah(judul, tahunTerbit, jumlahStok, edisi, bulanTerbit, kategori);

        } else {
            throw new DataKoleksiTidakValidException(
                    "Jenis koleksi harus dipilih antara 1 atau 2."
            );
        }
    }

    static int inputInt(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }
}