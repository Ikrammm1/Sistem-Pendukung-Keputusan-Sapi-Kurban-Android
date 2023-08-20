-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 20 Agu 2023 pada 15.38
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sawsapi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_about`
--

CREATE TABLE `tb_about` (
  `id_about` int(11) NOT NULL,
  `about` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_about`
--

INSERT INTO `tb_about` (`id_about`, `about`) VALUES
(1, 'Aplikasi ini merupakan aplikasi Sistem Pendukung Keputusan pemilihan Sapi Kurban terbaik dengan metode SAW (Simple Additive Weighting). Aplikasi ini dapat membantu dalam penentuan sapi kurban terbaik dengan perhitungan khusus dan beberapa kriteria.Kriteria dalam menentukan sapi kurban terbaik diantaranya berat sapi, kecacatan sapi, perilaku sapi, umur sapi dan jenis kelamin sapi.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_datasapi`
--

CREATE TABLE `tb_datasapi` (
  `id_datasapi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `alternatif` varchar(10) NOT NULL,
  `berat` int(11) NOT NULL,
  `C1` double DEFAULT NULL,
  `kecacatan` enum('Cacat','Tidak Cacat') NOT NULL,
  `C2` double DEFAULT NULL,
  `perilaku` enum('Normal','Tidak Normal') NOT NULL,
  `C3` double DEFAULT NULL,
  `umur` float NOT NULL,
  `C4` double DEFAULT NULL,
  `jenis_kelamin` enum('Jantan','Betina') NOT NULL,
  `C5` double DEFAULT NULL,
  `status_hitung` enum('Selesai','Belum') NOT NULL DEFAULT 'Belum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_datasapi`
--

INSERT INTO `tb_datasapi` (`id_datasapi`, `id_user`, `alternatif`, `berat`, `C1`, `kecacatan`, `C2`, `perilaku`, `C3`, `umur`, `C4`, `jenis_kelamin`, `C5`, `status_hitung`) VALUES
(3, 1, 'A3', 399, 1, 'Tidak Cacat', 1, '', 1, 2.6, 1, 'Jantan', 1, 'Selesai'),
(4, 1, 'A4', 236, 0.7, 'Tidak Cacat', 1, '', 1, 3, 1, 'Betina', 0.5, 'Selesai'),
(8, 1, 'A8', 329, 1, 'Tidak Cacat', 1, '', 1, 2.8, 1, 'Jantan', 1, 'Selesai'),
(9, 1, 'A9', 287, 0.7, 'Tidak Cacat', 1, '', 1, 2.7, 1, 'Jantan', 1, 'Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_hasil`
--

CREATE TABLE `tb_hasil` (
  `id_hasil` int(11) NOT NULL,
  `id_datasapi` int(11) NOT NULL,
  `nilai` double NOT NULL,
  `kesimpulan` enum('Sapi Sah dan Sangat Layak','Sapi Sah dan Layak','Sapi Tidak Layak') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_hasil`
--

INSERT INTO `tb_hasil` (`id_hasil`, `id_datasapi`, `nilai`, `kesimpulan`) VALUES
(113, 3, 0.98, 'Sapi Sah dan Sangat Layak'),
(114, 4, 0.94, 'Sapi Sah dan Sangat Layak'),
(118, 8, 0.98, 'Sapi Sah dan Sangat Layak'),
(119, 9, 0.92, 'Sapi Sah dan Sangat Layak');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kriteria`
--

CREATE TABLE `tb_kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `kriteria` varchar(50) NOT NULL,
  `sifat` enum('benefit','cost') NOT NULL,
  `bobot` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_kriteria`
--

INSERT INTO `tb_kriteria` (`id_kriteria`, `kriteria`, `sifat`, `bobot`) VALUES
(1, 'Berat', 'benefit', 20),
(2, 'Kecacatan', 'benefit', 20),
(3, 'Perilaku', 'benefit', 15),
(4, 'Umur', 'benefit', 40),
(5, 'Jenis Kelamin', 'cost', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nilaikriteria`
--

CREATE TABLE `tb_nilaikriteria` (
  `id_nilaikriteria` int(11) NOT NULL,
  `id_kriteria` int(11) NOT NULL,
  `nilai_kriteria` float NOT NULL,
  `keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_nilaikriteria`
--

INSERT INTO `tb_nilaikriteria` (`id_nilaikriteria`, `id_kriteria`, `nilai_kriteria`, `keterangan`) VALUES
(1, 1, 0.2, '<200 Kg'),
(2, 1, 0.7, '200 Kg-300 Kg'),
(3, 1, 1, '>300 Kg'),
(4, 2, 0.01, 'Cacat'),
(5, 2, 1, 'Tidak Cacat'),
(6, 3, 1, 'Baik'),
(7, 3, 0.01, 'Tidak Baik'),
(8, 4, 0.01, '<2 Tahun'),
(9, 4, 1, '>=2Tahun <=3 Tahun'),
(10, 4, 0.3, '> 3 Tahun'),
(11, 5, 1, 'Jantan'),
(12, 5, 0.5, 'Betina');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(11) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `level` enum('user','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `nama_user`, `username`, `password`, `level`) VALUES
(1, 'Ababil', 'Ababil', 'Ababil123', 'user'),
(9, 'Admin1', 'Admin', 'Admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_about`
--
ALTER TABLE `tb_about`
  ADD PRIMARY KEY (`id_about`);

--
-- Indeks untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  ADD PRIMARY KEY (`id_datasapi`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  ADD PRIMARY KEY (`id_hasil`),
  ADD KEY `id_sapi` (`id_datasapi`);

--
-- Indeks untuk tabel `tb_kriteria`
--
ALTER TABLE `tb_kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indeks untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  ADD PRIMARY KEY (`id_nilaikriteria`),
  ADD KEY `id_kriteria` (`id_kriteria`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_about`
--
ALTER TABLE `tb_about`
  MODIFY `id_about` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  MODIFY `id_datasapi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  MODIFY `id_hasil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT untuk tabel `tb_kriteria`
--
ALTER TABLE `tb_kriteria`
  MODIFY `id_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  MODIFY `id_nilaikriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  ADD CONSTRAINT `id_sapi` FOREIGN KEY (`id_datasapi`) REFERENCES `tb_datasapi` (`id_datasapi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  ADD CONSTRAINT `id_kriteria` FOREIGN KEY (`id_kriteria`) REFERENCES `tb_kriteria` (`id_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
