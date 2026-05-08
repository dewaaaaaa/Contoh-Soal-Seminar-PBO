# Sistem Informasi Pengelolaan Koleksi Perpustakaan Berbasis Java CLI dan MySQL

Sebuah perpustakaan kecil ingin mulai menggunakan sistem sederhana untuk mengelola data koleksinya. Selama ini, pencatatan koleksi masih dilakukan secara manual sehingga sering terjadi kesalahan, seperti informasi utama koleksi tidak diisi, tahun penerbitan bernilai tidak wajar, jumlah ketersediaan tercatat negatif, atau koleksi yang sudah tidak tersedia masih dicoba untuk diperbarui maupun dihapus.

Untuk mengurangi kesalahan tersebut, perpustakaan meminta Tim Anda merancang Sistem Informasi Pengelolaan Koleksi Perpustakaan berbasis Java CLI dan MySQL. Sistem ini hanya berfokus pada proses pengelolaan data koleksi, yaitu menambah, melihat, mengubah, dan menghapus data koleksi. Program tidak perlu dibuat kompleks, tetapi susunan kode harus rapi, mudah dipahami, dan setiap bagian program memiliki tanggung jawab yang jelas.

## Gambaran Sistem

Aplikasi ini digunakan untuk mengelola data koleksi yang tersimpan di database MySQL. Setiap koleksi memiliki informasi dasar yang diperlukan untuk proses pengelolaan, seperti identitas koleksi, informasi utama koleksi, tahun penerbitan, jumlah ketersediaan, dan jenis koleksi.

Dalam sistem ini, koleksi tidak hanya dianggap sebagai satu bentuk yang sama. Beberapa koleksi dapat memiliki informasi tambahan sesuai jenisnya. Misalnya, koleksi buku dapat memiliki informasi yang berkaitan dengan pihak penulis, pihak penerbit, atau kode identitas buku. Koleksi majalah dapat memiliki informasi yang berkaitan dengan edisi, waktu penerbitan, atau kategori majalah.

Detail informasi tambahan tidak perlu dibuat terlalu banyak. Mahasiswa cukup menentukan informasi yang sesuai dengan kebutuhan CRUD sederhana, selama masih sesuai dengan jenis koleksi yang dibuat.

## Rancangan Komponen Program

Dalam program ini, buatlah beberapa komponen utama berikut:

### Koleksi

Komponen ini digunakan sebagai gambaran umum dari data koleksi yang dikelola dalam sistem. Bagian ini memuat karakteristik umum yang dimiliki oleh semua koleksi. Karena data seperti tahun penerbitan dan jumlah ketersediaan sangat berpengaruh terhadap keakuratan sistem, perubahan terhadap data tersebut tidak boleh dilakukan secara sembarangan, tetapi harus melewati pemeriksaan terlebih dahulu.

### Buku

Komponen ini digunakan untuk merepresentasikan koleksi yang termasuk kategori buku. Koleksi jenis ini dapat memiliki informasi tambahan seperti pihak penulis, pihak penerbit, atau kode identitas buku.

### Majalah

Komponen ini digunakan untuk merepresentasikan koleksi majalah. Koleksi jenis ini dapat memiliki informasi tambahan seperti edisi, waktu penerbitan, atau kategori majalah.

### CrudService

Komponen ini digunakan sebagai rancangan umum layanan pengelolaan data. Setiap layanan pengelolaan koleksi harus memiliki kemampuan dasar untuk menambah, menampilkan, mengubah, dan menghapus data.

### KoleksiService

Komponen ini bertanggung jawab menjalankan proses utama pengelolaan koleksi. Bagian ini mengatur alur tambah data, lihat data, ubah data, hapus data, memeriksa kelayakan data sebelum diproses, dan berhubungan dengan database melalui komponen koneksi.

### KoneksiDatabase

Komponen ini bertanggung jawab mengatur hubungan antara program Java dan MySQL. Pada bagian ini, jabarkan variabel atau properti dasar yang dibutuhkan untuk melakukan koneksi database, seperti:

```text
urlDatabase
username
password
namaDatabase
driverDatabase
```

## Penanganan Kesalahan Khusus

Buat maksimal tiga bentuk penanganan kesalahan khusus agar program dapat memberikan pesan yang lebih jelas ketika terjadi masalah, yaitu:

```text
DataKoleksiTidakValidException
KoleksiTidakDitemukanException
KoneksiDatabaseException
```

## Proses Bisnis Utama

Proses utama dalam sistem adalah sebagai berikut:

### Menambah Koleksi

Pengguna memasukkan data koleksi baru ke dalam sistem. Sebelum data disimpan ke database, sistem harus memastikan bahwa data yang dimasukkan sudah sesuai dengan aturan yang berlaku.

### Melihat Koleksi

Sistem menampilkan daftar koleksi yang tersimpan di database. Koleksi yang ditampilkan dapat berupa koleksi buku maupun koleksi majalah.

### Mengubah Koleksi

Pengguna dapat mengubah data koleksi tertentu. Sebelum perubahan disimpan, sistem harus memastikan bahwa koleksi tersebut tersedia dan data baru yang dimasukkan masih valid.

### Menghapus Koleksi

Pengguna dapat menghapus koleksi dari database. Namun, sistem harus memastikan terlebih dahulu bahwa koleksi yang akan dihapus benar-benar tersedia.

## Aturan Bisnis

Aturan bisnis yang harus diterapkan dalam sistem adalah sebagai berikut:

Informasi utama koleksi tidak boleh kosong.

Tahun penerbitan koleksi harus lebih dari nol.

Jumlah ketersediaan koleksi tidak boleh bernilai negatif.

Koleksi buku harus memiliki informasi pihak penulis, pihak penerbit, atau kode identitas buku yang valid.

Informasi pihak penulis buku tidak boleh kosong.

Kode identitas buku dapat menggunakan contoh seperti kode sederhana atau format lain yang dianggap sesuai.

Koleksi majalah harus memiliki informasi edisi, waktu penerbitan, atau kategori majalah yang valid.

Informasi edisi majalah tidak boleh kosong.

Informasi waktu penerbitan majalah dapat menggunakan contoh seperti Januari, Februari, Maret, April, atau keterangan lain yang dianggap sesuai.

Koleksi yang akan diubah harus tersedia di database.

Koleksi yang akan dihapus harus tersedia di database.

Perubahan tahun penerbitan dan jumlah ketersediaan harus melewati proses pengecekan terlebih dahulu sebelum disimpan.

Jika data koleksi tidak sesuai aturan, sistem menampilkan `DataKoleksiTidakValidException`.

Jika koleksi tidak ditemukan saat proses ubah atau hapus, sistem menampilkan `KoleksiTidakDitemukanException`.

Jika koneksi ke database gagal, sistem menampilkan `KoneksiDatabaseException`.

## Ketentuan Program

Program dibuat berbasis Java CLI dan terhubung dengan database MySQL. Program cukup memiliki menu sederhana, misalnya:

```text
Tambah Koleksi.
Lihat Koleksi.
Ubah Koleksi.
Hapus Koleksi.
Keluar.
```

Program tidak perlu memiliki fitur peminjaman koleksi, pengembalian koleksi, denda, laporan perpustakaan, pembayaran, pencarian kompleks, atau tampilan GUI. Fokus utama program adalah pengelolaan data koleksi perpustakaan secara sederhana.

Susunan kode harus dibuat terpisah berdasarkan tanggung jawabnya. Bagian yang menangani data koleksi, proses CRUD, koneksi database, pemeriksaan aturan bisnis, dan penanganan kesalahan tidak boleh ditumpuk seluruhnya dalam satu tempat. Struktur kelas, informasi tambahan, dan method dapat dikembangkan sesuai kebutuhan selama tetap mengikuti gambaran sistem dan aturan bisnis yang telah ditentukan.
