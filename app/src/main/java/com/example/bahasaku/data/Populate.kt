package com.example.bahasaku.data

import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word

object Populate {
    //TODO: Chapter
    val populateChapter = listOf(
        Chapter(
            id = "00",
            title = "Salam",
            cardNumber = 8,
            exerciseNumber = 8,
            imageUrl = "salam/terima_kasih.png"
        ),
        Chapter(
            id = "01",
            title = "Panggilan",
            cardNumber = 9,
            exerciseNumber = 9,
            imageUrl = "panggilan/nenek.png"
        ),
        Chapter(
            id = "02",
            title = "Hewan",
            cardNumber = 12,
            exerciseNumber = 24,
            imageUrl = "hewan/anak_bebek.png",
            chapterChild = "12"
        ),
        Chapter(
            id = "03",
            title = "Angka",
            cardNumber = 46,
            exerciseNumber = 46,
            imageUrl = "angka/1.png"
        ),
        Chapter(
            id = "04",
            title = "Perabotan",
            cardNumber = 16,
            exerciseNumber = 16,
            imageUrl = "perabotan/meja.png"
        ),
        Chapter(
            id = "05",
            title = "Makanan dan Minuman",
            cardNumber = 14,
            exerciseNumber = 14,
            imageUrl = "makanan_dan_minuman/daging_ayam.png"
        ),
        Chapter(
            id = "06",
            title = "Amggota Badan",
            cardNumber = 23,
            exerciseNumber = 23,
            imageUrl = "anggota_badan/mata.png"
        ),
        Chapter(
            id = "07",
            title = "Aktivitas",
            cardNumber = 14,
            exerciseNumber = 14,
            imageUrl = "aktivitas/berjalan.png"
        ),

        //TODO: Child
        Chapter(
            id = "12",
            title = "Anak Hewan",
            cardNumber = 0,
            exerciseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
    )

    val populateWord = listOf(
        //TODO: Salam
        Word(
            id = "0000",
            chapterId = "00",
            indonesian = "Selamat Pagi",
            balinese = "Rahajeng Semeng",
            imageUrl = "salam/selamat_pagi.png",
            option = "Rahajeng|Semeng|Rahajeng Wengi|Rahajeng Pagi",
            wordChild = ""
        ),
        Word(
            id = "0001",
            chapterId = "00",
            indonesian = "Selamat Siang",
            balinese = "Rahajeng Siang",
            imageUrl = "salam/selamat_siang.png",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Memargi|Semeng",
            wordChild = ""
        ),
        Word(
            id = "0002",
            chapterId = "00",
            indonesian = "Selamat Malam",
            balinese = "Rahajeng Wengi",
            imageUrl = "salam/selamat_malam.png",
            option = "Rahajeng Semeng|Rahajeng Siang|Semeng|Rahajeng",
            wordChild = ""
        ),
        Word(
            id = "0003",
            chapterId = "00",
            indonesian = "Selamat Makan",
            balinese = "Rahajeng Majengan",
            imageUrl = "salam/selamat_makan.png",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = ""
        ),
        Word(
            id = "0004",
            chapterId = "00",
            indonesian = "Selamat Tahun Baru",
            balinese = "Rahajeng Nyanggra Warsa Anyar",
            imageUrl = "salam/selamat_tahun_baru.png",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = ""
        ),
        Word(
            id = "0005",
            chapterId = "00",
            indonesian = "Selamat Ulang Tahun",
            balinese = "Rahajeng Wanti Warsa",
            imageUrl = "salam/selamat_ulang_tahun.png",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wengi|Rahajeng Memargi",
            wordChild = ""
        ),
        Word(
            id = "0006",
            chapterId = "00",
            indonesian = "Selamat Tinggal",
            balinese = "Rahajeng Memargi",
            imageUrl = "salam/selamat_tinggal.png",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Wengi",
            wordChild = ""
        ),
        Word(
            id = "0007",
            chapterId = "00",
            indonesian = "Terima Kasih",
            balinese = "Matur Suksma",
            imageUrl = "salam/terima_kasih.png",
            option = "Rahajeng Semeng|Rahajeng Wengi|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = ""
        ),

        //TODO: Panggilan
        Word(
            id = "0100",
            chapterId = "01",
            indonesian = "Saya",
            balinese = "Tiang/Titiang/Irage",
            imageUrl = "panggilan/saya.png",
            option = "Awak|Semeng|Bli|Pekak|Mbok|Dadong",
            wordChild = ""
        ),
        Word(
            id = "0101",
            chapterId = "01",
            indonesian = "Kamu",
            balinese = "Awak",
            imageUrl = "panggilan/kamu.png",
            option = "Rahajeng|Tiang|Irage|Pekak|Mbok|Dadong",
            wordChild = ""
        ),
        Word(
            id = "0102",
            chapterId = "01",
            indonesian = "Kakak Laki-laki",
            balinese = "Bli",
            imageUrl = "panggilan/kakak_laki_laki.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),
        Word(
            id = "0103",
            chapterId = "01",
            indonesian = "Kakak Perempuan",
            balinese = "Mbok",
            imageUrl = "panggilan/kakak_perempuan.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),
        Word(
            id = "0104",
            chapterId = "01",
            indonesian = "Ayah",
            balinese = "Bapa",
            imageUrl = "panggilan/ayah.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),
        Word(
            id = "0105",
            chapterId = "01",
            indonesian = "Ibu",
            balinese = "Meme",
            imageUrl = "panggilan/ibu.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),
        Word(
            id = "0106",
            chapterId = "01",
            indonesian = "Kakek",
            balinese = "Pekak",
            imageUrl = "panggilan/kakek.png",
            option = "Awak|Tiang|Irage|Dadong",
            wordChild = ""
        ),
        Word(
            id = "0107",
            chapterId = "01",
            indonesian = "Nenek",
            balinese = "Dadong/Niang",
            imageUrl = "panggilan/nenek.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),
        Word(
            id = "0108",
            chapterId = "01",
            indonesian = "Adik",
            balinese = "Adi/Adin",
            imageUrl = "panggilan/adik.png",
            option = "Awak|Tiang|Irage|Pekak",
            wordChild = ""
        ),

        //TODO: Hewan
        Word(
            id = "0200",
            chapterId = "02",
            indonesian = "Kuda",
            balinese = "Jaran",
            imageUrl = "hewan/kuda.png",
            option = "Sampi|Kebo|Tiang|Semeng",
            wordChild = "1200"
        ),
        Word(
            id = "0201",
            chapterId = "02",
            indonesian = "Kambing",
            balinese = "Kambing",
            imageUrl = "hewan/kambing.png",
            option = "",
            wordChild = "1201"
        ),
        Word(
            id = "0202",
            chapterId = "02",
            indonesian = "Kucing",
            balinese = "Meong",
            imageUrl = "hewan/kucing.png",
            option = "",
            wordChild = "1202"
        ),
        Word(
            id = "0203",
            chapterId = "02",
            indonesian = "Bebek",
            balinese = "Bebek",
            imageUrl = "hewan/bebek.png",
            option = "",
            wordChild = "1203"
        ),
        Word(
            id = "0204",
            chapterId = "02",
            indonesian = "Anjing",
            balinese = "Cicing",
            imageUrl = "hewan/anjing.png",
            option = "",
            wordChild = "1204"
        ),
        Word(
            id = "0205",
            chapterId = "02",
            indonesian = "Kerbau",
            balinese = "Kebo",
            imageUrl = "hewan/kerbau.png",
            option = "",
            wordChild = "1205"
        ),
        Word(
            id = "0206",
            chapterId = "02",
            indonesian = "Tikus",
            balinese = "Bikul",
            imageUrl = "hewan/tikus.png",
            option = "",
            wordChild = "1206"
        ),
        Word(
            id = "0207",
            chapterId = "02",
            indonesian = "Babi",
            balinese = "Bangkung",
            imageUrl = "hewan/babi.png",
            option = "",
            wordChild = "1207"
        ),
        Word(
            id = "0208",
            chapterId = "02",
            indonesian = "Ular",
            balinese = "Lelipi",
            imageUrl = "hewan/ular.png",
            option = "",
            wordChild = "1208"
        ),
        Word(
            id = "0209",
            chapterId = "02",
            indonesian = "Monyet/Kera",
            balinese = "Bojog",
            imageUrl = "hewan/monyet.png",
            option = "",
            wordChild = "1209"
        ),
        Word(
            id = "0210",
            chapterId = "02",
            indonesian = "Sapi",
            balinese = "Sampi",
            imageUrl = "hewan/sapi.png",
            option = "",
            wordChild = "1210"
        ),
        Word(
            id = "0211",
            chapterId = "02",
            indonesian = "Kepiting",
            balinese = "Yuyu",
            imageUrl = "hewan/kepiting.png",
            option = "",
            wordChild = "12011"
        ),

        //TODO: Child
        Word(
            id = "1200",
            chapterId = "12",
            indonesian = "Anak Kuda",
            balinese = "Bebedag",
            imageUrl = "hewan/anak_kuda.png",
            option = "",
        ),
        Word(
            id = "1201",
            chapterId = "12",
            indonesian = "Anak Kambing",
            balinese = "Wiwi",
            imageUrl = "hewan/anak_kambing.png",
            option = "",
        ),
        Word(
            id = "1202",
            chapterId = "12",
            indonesian = "Anak Kucing",
            balinese = "Tai",
            imageUrl = "hewan/anak_kucing.png",
            option = "",
        ),
        Word(
            id = "1203",
            chapterId = "12",
            indonesian = "Anak Bebek",
            balinese = "Memeri",
            imageUrl = "hewan/anak_bebek.png",
            option = "",
        ),
        Word(
            id = "1204",
            chapterId = "12",
            indonesian = "Anak Anjing",
            balinese = "Kuluk",
            imageUrl = "hewan/anak_anjing.png",
            option = "",
        ),
        Word(
            id = "1205",
            chapterId = "12",
            indonesian = "Anak Kerbau",
            balinese = "Bedigal",
            imageUrl = "hewan/anak_kerbau.png",
            option = "",
        ),
        Word(
            id = "1206",
            chapterId = "12",
            indonesian = "Anak Tikus",
            balinese = "Nying-nying",
            imageUrl = "hewan/anak_tikus.png",
            option = "",
        ),
        Word(
            id = "1207",
            chapterId = "12",
            indonesian = "Anak Babi",
            balinese = "Kucit",
            imageUrl = "hewan/anak_babi.png",
            option = "",
        ),
        Word(
            id = "1208",
            chapterId = "12",
            indonesian = "Anak Ular",
            balinese = "Brara",
            imageUrl = "hewan/anak_ular.png",
            option = "",
        ),
        Word(
            id = "1209",
            chapterId = "12",
            indonesian = "Anak Monyet/Kera",
            balinese = "Apa",
            imageUrl = "hewan/anak_monyet.png",
            option = "",
        ),
        Word(
            id = "1210",
            chapterId = "12",
            indonesian = "Anak Sapi",
            balinese = "Godel",
            imageUrl = "hewan/anak_sapi.png",
            option = "",
        ),
        Word(
            id = "1211",
            chapterId = "12",
            indonesian = "Anak Kepiting",
            balinese = "Kreket/Utung-utung",
            imageUrl = "hewan/anak_kepiting.png",
            option = "",
        ),

        //TODO: Angka 03
        Word(
            id = "0300",
            chapterId = "03",
            indonesian = "Satu",
            balinese = "Besik",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0301",
            chapterId = "03",
            indonesian = "Dua",
            balinese = "Kalih",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0302",
            chapterId = "03",
            indonesian = "Tiga",
            balinese = "Telu",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0304",
            chapterId = "03",
            indonesian = "Empat",
            balinese = "Papat",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0305",
            chapterId = "03",
            indonesian = "Lima",
            balinese = "Lima",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0306",
            chapterId = "03",
            indonesian = "Enam",
            balinese = "Nenem",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0307",
            chapterId = "03",
            indonesian = "Tujuh",
            balinese = "Pitu",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0308",
            chapterId = "03",
            indonesian = "Delapan",
            balinese = "Kutus",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0309",
            chapterId = "03",
            indonesian = "Sembilan",
            balinese = "Sia/Sanga",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0310",
            chapterId = "03",
            indonesian = "Sepuluh",
            balinese = "Dasa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0311",
            chapterId = "03",
            indonesian = "Sebelas",
            balinese = "Solas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0312",
            chapterId = "03",
            indonesian = "Dua Belas",
            balinese = "Roras",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0313",
            chapterId = "03",
            indonesian = "Tiga Belas",
            balinese = "Telulas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0314",
            chapterId = "03",
            indonesian = "Empat Belas",
            balinese = "Pat belas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0315",
            chapterId = "03",
            indonesian = "Lima Belas",
            balinese = "Limolas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0316",
            chapterId = "03",
            indonesian = "Enam Belas",
            balinese = "Nem belas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0317",
            chapterId = "03",
            indonesian = "Tujuh Belas",
            balinese = "Pitulas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0318",
            chapterId = "03",
            indonesian = "Delapan Belas",
            balinese = "Plekutus",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0319",
            chapterId = "03",
            indonesian = "Sembilan Belas",
            balinese = "Siangolas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0320",
            chapterId = "03",
            indonesian = "Dua Puluh",
            balinese = "Duang dasa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0321",
            chapterId = "03",
            indonesian = "Dua Puluh Satu",
            balinese = "Selikur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0322",
            chapterId = "03",
            indonesian = "Dua Puluh Dua",
            balinese = "Dua likur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0323",
            chapterId = "03",
            indonesian = "Dua Puluh Tiga",
            balinese = "Telu likur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0324",
            chapterId = "03",
            indonesian = "Dua Puluh Empat",
            balinese = "Patlikur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0325",
            chapterId = "03",
            indonesian = "Dua Puluh Lima",
            balinese = "Selae",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0326",
            chapterId = "03",
            indonesian = "Dua Puluh Enam",
            balinese = "Nem likur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0327",
            chapterId = "03",
            indonesian = "Dua Puluh Tujuh",
            balinese = "Pitu likur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0328",
            chapterId = "03",
            indonesian = "Dua Puluh Delapan",
            balinese = "Ululikur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0329",
            chapterId = "03",
            indonesian = "Dua Puluh Sembilan",
            balinese = "Sanga likur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0330",
            chapterId = "03",
            indonesian = "Tiga Puluh",
            balinese = "Telung Dasa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0331",
            chapterId = "03",
            indonesian = "Tiga Puluh Satu",
            balinese = "Telung Dasa Besik",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0332",
            chapterId = "03",
            indonesian = "Tiga Puluh Lima",
            balinese = "Patsasur",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0333",
            chapterId = "03",
            indonesian = "Empat Puluh",
            balinese = "Petang Dasa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0334",
            chapterId = "03",
            indonesian = "Empat Puluh Lima",
            balinese = "Setiman",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0335",
            chapterId = "03",
            indonesian = "Lima Puluh",
            balinese = "Seket",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0336",
            chapterId = "03",
            indonesian = "Tujuh Puluh Lima",
            balinese = "Tigang Benang",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0337",
            chapterId = "03",
            indonesian = "Delapan Puluh",
            balinese = "Ulung Dasa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0338",
            chapterId = "03",
            indonesian = "Seratus",
            balinese = "Satus",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0339",
            chapterId = "03",
            indonesian = "Seratus Lima Puluh",
            balinese = "Karobelah",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0340",
            chapterId = "03",
            indonesian = "Seratus Tujuh Puluh Lima",
            balinese = "Lebak",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0341",
            chapterId = "03",
            indonesian = "Dua Ratus",
            balinese = "Satak",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0342",
            chapterId = "03",
            indonesian = "Tiga Ratus",
            balinese = "Telung Atus",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0343",
            chapterId = "03",
            indonesian = "Empat Ratus",
            balinese = "Samas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0344",
            chapterId = "03",
            indonesian = "Enam Ratus",
            balinese = "Telung Atak",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0345",
            chapterId = "03",
            indonesian = "Delapan Ratus",
            balinese = "Domas",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "0346",
            chapterId = "03",
            indonesian = "Seribu",
            balinese = "Siu",
            imageUrl = "",
            option = "",
        ),

        //TODO: Benda

        //TODO: Makanan dan Minuman

        //TODO: Anggota Badan

        //TODO: Kata Kerja
    )
}