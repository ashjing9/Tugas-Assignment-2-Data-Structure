/*
 * TUGAS KELOMPOK 2
 * Mata Kuliah: COSC6025036 - Data Structures and Algorithm Analysis
 *
 * Anggota Kelompok:
 * 1. Krisna Setiyawan                  - 2902829641
 * 2. Azril Tsani                       - 2902807312
 * 3. Bambang Priyanto                  - 2902819073
 * 4. Haiefa Agasy Aprilya Sari         - 2602311275
 * 5. Muhammad Ramadan Abdul Khalik     - 3002933264
 * Program Studi: Computer Science
 */

import java.util.Scanner;

// Class Lagu menyimpan data dari satu lagu.
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public double getDurasi() {
        return durasi;
    }

    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method ini menampilkan seluruh informasi lagu.
    public void tampilkanInfo() {
        int menit = (int) durasi / 60;
        int detik = (int) durasi % 60;

        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + menit + " menit " + detik + " detik");
    }
}

// Class Playlist mengelola kumpulan objek Lagu di dalam array.
class Playlist {
    private Lagu[] daftarLagu;
    private int jumlahLagu;

    public Playlist(int kapasitas) {
        daftarLagu = new Lagu[kapasitas];
        jumlahLagu = 0;
    }

    //tambah lagu versi biasa
    // Kompleksitas waktu: O(1)
    // Karena lagu langsung dimasukkan ke indeks kosong berikutnya
    // tanpa perlu menggeser elemen lain.
    public boolean tambahLagu(Lagu lagu) {
        if (jumlahLagu >= daftarLagu.length) {
            return false;
        }

        daftarLagu[jumlahLagu] = lagu;
        jumlahLagu++;
        return true;
    }

    // Tambah lagu ke playlist.
    // Insertion
    //komplexitas waktu: O(n) worse case
    public boolean tambahLagu(Lagu lagu, int lokasi) {
    // Validasi: playlist penuh atau lokasi di luar jangkauan yang valid
    if (jumlahLagu >= daftarLagu.length || lokasi < 0 || lokasi > jumlahLagu) {
        return false;
    }

    // Geser semua elemen ke kanan mulai dari elemen paling belakang
    for (int i = jumlahLagu; i > lokasi; i--) {
        daftarLagu[i] = daftarLagu[i - 1];
    }

    // Masukkan lagu baru ke posisi/lokasi yang sudah dikosongkan
    daftarLagu[lokasi] = lagu;
    jumlahLagu++;
    return true;
}

    // Tampilkan semua lagu dengan menelusuri array dari awal hingga jumlahLagu.
    // Traversal
    // Kompleksitas waktu: O(n)
    // Karena setiap lagu dalam array yang terisi dikunjungi satu kali.
    public void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.println("\nDaftar Lagu");
        System.out.println("------------------------------");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println("Lagu ke-" + (i + 1));
            daftarLagu[i].tampilkanInfo();
            System.out.println("------------------------------");
        }
    }

    // Lagu dihapus dengan menggeser semua lagu setelahnya ke kiri.
    // Deletion
    // Kompleksitas waktu: O(n) pada worst case.
    // Karena program harus mencari lagu terlebih dahulu,
    // kemudian menggeser elemen-elemen setelahnya ke kiri.
    public void hapusLagu(String judul) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (daftarLagu[i].getJudul().equalsIgnoreCase(judul)) {
                for (int j = i; j < jumlahLagu - 1; j++) {
                    daftarLagu[j] = daftarLagu[j + 1];
                }
                daftarLagu[jumlahLagu - 1] = null;
                jumlahLagu--;
                System.out.println("Lagu '" + judul + "' berhasil dihapus.");
                return;
            }
        }
        System.out.println("Lagu dengan judul '" + judul + "' tidak ditemukan.");
    }

    // Searching menggunakan Linear Search.
    // Kompleksitas waktu:
    // Best Case : O(1), jika lagu ditemukan pada elemen pertama.
    // Worst Case : O(n), jika lagu berada di elemen terakhir atau tidak ditemukan.
    public Lagu cariLagu(String judul) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (daftarLagu[i].getJudul().equalsIgnoreCase(judul)) {
                return daftarLagu[i];
            }
        }
        return null;
    }

    // Mengurutkan lagu berdasarkan durasi menggunakan algoritma bubble sort.
    // Sorting
    // Kompleksitas waktu:
    // Best Case : O(n), jika lagu sudah terurut dari awal.
    // Worst Case : O(n^2), jika lagu berada dalam urutan terbalik.
    // Karena program harus membandingkan dan menukar posisi lagu berulang kali.
    public void urutkanLaguBerdasarkanDurasi() {
        for (int i = 0; i < jumlahLagu - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < jumlahLagu - i - 1; j++) {
                if (daftarLagu[j].getDurasi() > daftarLagu[j + 1].getDurasi()) {
                    Lagu temp = daftarLagu[j];
                    daftarLagu[j] = daftarLagu[j + 1];
                    daftarLagu[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // Rata-rata diperoleh dari total durasi dibagi jumlah lagu.
    public double hitungRataRataDurasi() {
        if (jumlahLagu == 0) {
            return 0;
        }

        double totalDurasi = 0;
        for (int i = 0; i < jumlahLagu; i++) {
            totalDurasi += daftarLagu[i].getDurasi();
        }
        return totalDurasi / jumlahLagu;
    }
}

// User menjadi parent class untuk Admin dan Member.
abstract class User {
    private String nama;
    protected Playlist playlist;

    public User(String nama, Playlist playlist) {
        this.nama = nama;
        this.playlist = playlist;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method ini akan dioverride oleh setiap child class.
    public abstract void tampilkanAkses();
}

// Admin mewarisi atribut dan method dari User.
class Admin extends User {
    public Admin(String nama, Playlist playlist) {
        super(nama, playlist);
    }

    // Isi method berbeda dengan Member sehingga menunjukkan polymorphism.
    @Override
    public void tampilkanAkses() {
        System.out.println("\nLogin sebagai Admin: " + getNama());
        System.out.println("Akses: melihat daftar lagu dan menambahkan lagu.");
    }

    // Admin membuat objek Lagu lalu memasukkannya ke playlist.
    public void tambahLagu(Lagu lagu) {
        if (playlist.tambahLagu(lagu)) {
            System.out.println("Lagu '" + lagu.getJudul() + "' berhasil ditambahkan.");
        } else {
            System.out.println("Playlist sudah penuh. Lagu tidak dapat ditambahkan.");
        }
    }
    // 2. Overloading: Tambah lagu berdasarkan lokasi / insertion
    public void tambahLagu(Lagu lagu, int lokasi) {
        if (playlist.tambahLagu(lagu, lokasi)) {
            System.out.println("Lagu '" + lagu.getJudul() + "' berhasil disisipkan pada urutan ke-" + (lokasi + 1) + " (indeks " + lokasi + ").");
        } else {
            System.out.println("Gagal menambahkan lagu. Playlist penuh atau indeks lokasi tidak valid.");
        }
    }

    // Admin menghapus lagu berdasarkan judulnya.
    public void hapusLagu(String judul) {
        playlist.hapusLagu(judul);
    }

    // Admin juga dapat melihat daftar lagu.
    public void lihatDaftarLagu() {
        playlist.tampilkanSemuaLagu();
    }

    // Admin juga dapat mengurutkan daftar lagu berdasarkan durasi.
    public void urutkanLagu() {
        playlist.urutkanLaguBerdasarkanDurasi();
    }
}

// Member juga merupakan turunan dari User, tetapi hak aksesnya berbeda.
class Member extends User {
    public Member(String nama, Playlist playlist) {
        super(nama, playlist);
    }

    // Method yang sama menghasilkan tampilan akses sesuai jenis objeknya.
    @Override
    public void tampilkanAkses() {
        System.out.println("\nLogin sebagai Member: " + getNama());
        System.out.println("Akses: melihat, mencari, dan menghitung rata-rata durasi lagu.");
    }

    public void lihatDaftarLagu() {
        playlist.tampilkanSemuaLagu();
    }

    // Member mencari judul lagu melalui method cariLagu milik Playlist.
    public void cariLagu(String judul) {
        Lagu laguDitemukan = playlist.cariLagu(judul);

        if (laguDitemukan != null) {
            System.out.println("\nLagu ditemukan:");
            laguDitemukan.tampilkanInfo();
        } else {
            System.out.println("Lagu dengan judul '" + judul + "' tidak ditemukan.");
        }
    }

    // Hasil rata-rata ditampilkan dalam detik agar sama dengan input durasi.
    public void tampilkanRataRataDurasi() {
        double rataRata = playlist.hitungRataRataDurasi();
        System.out.printf("Rata-rata durasi lagu: %.2f detik%n", rataRata);
    }
}

public class PlaylistArray {
    // Fungsi utama membuat data awal, menerima login, lalu menjalankan menu.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist(10);

        playlist.tambahLagu(new Lagu("Secukupnya", "Hindia", 214));
        playlist.tambahLagu(new Lagu("Monokrom", "Tulus", 214));
        playlist.tambahLagu(new Lagu("Evaluasi", "Hindia", 204));

        boolean programBerjalan = true;

        System.out.println("====================================");
        System.out.println("  SISTEM MANAJEMEN PLAYLIST MUSIK");
        System.out.println("====================================");

        while (programBerjalan) {
            System.out.print("\nMasukkan nama pengguna: ");
            String nama = scanner.nextLine();

            System.out.print("Apakah Anda admin? (ya/tidak): ");
            String jawaban = scanner.nextLine();

            User pengguna;
            if (jawaban.equalsIgnoreCase("ya")) {
                pengguna = new Admin(nama, playlist);
            } else {
                pengguna = new Member(nama, playlist);
            }

            // Java memilih tampilkanAkses() berdasarkan objek Admin atau Member.
            pengguna.tampilkanAkses();

            boolean gantiPengguna = false;
            while (!gantiPengguna && programBerjalan) {
                tampilkanMenu();
                int pilihan = bacaAngka(scanner, "Pilih menu (1-8): ");

                clearScreen();
                System.out.println();
                System.out.println();
                switch (pilihan) {
                    case 1:
                        pengguna.tampilkanAkses();
                        break;
                    case 2:
                        tampilkanDaftarBerdasarkanUser(pengguna);
                        break;
                    case 3:
                        if (pengguna instanceof Admin) {
                            tambahLaguBaru(scanner, (Admin) pengguna);
                        } else {
                            System.out.println("Member tidak memiliki akses untuk menambahkan lagu.");
                        }
                        break;
                    case 4:
                        if (pengguna instanceof Admin) {
                            System.out.print("Masukkan judul lagu yang ingin dihapus: ");
                            String judulHapus = scanner.nextLine();
                            ((Admin) pengguna).hapusLagu(judulHapus);
                        } else {
                            System.out.println("Member tidak memiliki akses untuk menghapus lagu.");
                        }
                        break;
                    case 5:
                        if (pengguna instanceof Member) {
                            System.out.print("Masukkan judul lagu yang dicari: ");
                            String judul = scanner.nextLine();
                            ((Member) pengguna).cariLagu(judul);
                        } else {
                            System.out.println("Menu pencarian digunakan oleh Member.");
                        }
                        break;
                    case 6:
                        if (pengguna instanceof Admin) {
                            ((Admin) pengguna).urutkanLagu();
                            System.out.println("Daftar lagu telah diurutkan berdasarkan durasi.");
                        } else {
                            System.out.println("Member tidak memiliki akses untuk mengurutkan lagu.");
                        }
                        break;
                    case 7:
                        if (pengguna instanceof Member) {
                            ((Member) pengguna).tampilkanRataRataDurasi();
                        } else {
                            System.out.println("Menu rata-rata durasi digunakan oleh Member.");
                        }
                        break;
                    case 8:
                        gantiPengguna = true;
                        System.out.println("Silakan login dengan pengguna lain.");
                        break;
                    case 9:
                        programBerjalan = false;
                        System.out.println("Program selesai. Terima kasih.");
                        break;
                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }
            }
        }

        scanner.close();
    }

    // Menu dibuat terpisah supaya fungsi main lebih mudah dibaca.
    private static void tampilkanMenu() {
        System.out.println("\nMenu");
        System.out.println("1. Tampilkan nama dan akses pengguna");
        System.out.println("2. Tampilkan semua lagu");
        System.out.println("3. Tambah lagu baru");
        System.out.println("4. Hapus lagu berdasarkan judul");
        System.out.println("5. Cari lagu berdasarkan judul");
        System.out.println("6. Urutkan lagu berdasarkan durasi");
        System.out.println("7. Hitung rata-rata durasi lagu");
        System.out.println("8. Ganti pengguna");
        System.out.println("9. Keluar");
    }

    // Admin dan Member sama-sama dapat melihat daftar lagu.
    private static void tampilkanDaftarBerdasarkanUser(User pengguna) {
        if (pengguna instanceof Admin) {
            ((Admin) pengguna).lihatDaftarLagu();
        } else {
            ((Member) pengguna).lihatDaftarLagu();
        }
    }

    // Data dari Admin diubah menjadi objek Lagu sebelum dimasukkan ke array.
    private static void tambahLaguBaru(Scanner scanner, Admin admin) {
    System.out.print("Masukkan judul lagu: ");
    String judul = scanner.nextLine();

    System.out.print("Masukkan nama artis: ");
    String artis = scanner.nextLine();

    double durasi = bacaDesimal(scanner, "Masukkan durasi lagu dalam detik: ");
    Lagu laguBaru = new Lagu(judul, artis, durasi);

    // Submenu pilihan metode penambahan
    System.out.println("\nPilih metode penambahan lagu:");
    System.out.println("1. Tambah biasa (di akhir playlist)");
    System.out.println("2. Menyisipkan di posisi tertentu (Insertion)");
    int pilihanMetode = bacaAngka(scanner, "Pilihan (1/2): ");

    if (pilihanMetode == 2) {
        // Minta input posisi/indeks dari pengguna
        int posisi = bacaAngka(scanner, "Masukkan posisi urutan lagu (dimulai dari 1): ");
        int indeks = posisi - 1; // Konversi dari urutan manusia (1-based) ke indeks array (0-based)
        
        admin.tambahLagu(laguBaru, indeks);
    } else {
        // Default ke jalur biasa (paling belakang)
        admin.tambahLagu(laguBaru);
    }
}

    // Input angka dibaca sebagai String dulu supaya program tidak error saat salah
    // ketik.
    private static int bacaAngka(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // Fungsi ini memastikan nilai durasi berupa angka positif.
    private static double bacaDesimal(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                double nilai = Double.parseDouble(scanner.nextLine());
                if (nilai > 0) {
                    return nilai;
                }
                System.out.println("Durasi harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input durasi harus berupa angka.");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
