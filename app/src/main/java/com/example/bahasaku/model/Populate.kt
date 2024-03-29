package com.example.bahasaku.model

import com.example.bahasaku.R
import com.example.bahasaku.model.entity.Chapter
import com.example.bahasaku.model.entity.Word

object Populate {
    //TODO: Chapter
    val populateChapter = listOf(
        Chapter(
            id = "00",
            title = "Salam",
            cardNumber = 8,
            exerciseNumber = 8,
            imageUrl = "salam/terima_kasih.jpg"
        ),
        Chapter(
            id = "01",
            title = "Panggilan",
            cardNumber = 9,
            exerciseNumber = 9,
            imageUrl = "panggilan/nenek.jpg"
        ),
        Chapter(
            id = "02",
            title = "Hewan",
            cardNumber = 12,
            exerciseNumber = 24,
            imageUrl = "hewan/anak_bebek.jpg",
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
            imageUrl = "salam/selamat_pagi.jpg",
            option = "Rahajeng|Semeng|Rahajeng Wengi|Rahajeng Pagi",
            wordChild = "",
            audio = R.raw.audio_0000
        ),
        Word(
            id = "0001",
            chapterId = "00",
            indonesian = "Selamat Siang",
            balinese = "Rahajeng Siang",
            imageUrl = "salam/selamat_siang.jpg",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Memargi|Semeng",
            wordChild = "",
            audio = R.raw.audio_0001
        ),
        Word(
            id = "0002",
            chapterId = "00",
            indonesian = "Selamat Malam",
            balinese = "Rahajeng Wengi",
            imageUrl = "salam/selamat_malam.jpg",
            option = "Rahajeng Semeng|Rahajeng Siang|Semeng|Rahajeng",
            wordChild = "",
            audio = R.raw.audio_0002
        ),
        Word(
            id = "0003",
            chapterId = "00",
            indonesian = "Selamat Makan",
            balinese = "Rahajeng Majengan",
            imageUrl = "salam/selamat_makan.jpg",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = "",
            audio = R.raw.audio_0003
        ),
        Word(
            id = "0004",
            chapterId = "00",
            indonesian = "Selamat Tahun Baru",
            balinese = "Rahajeng Nyanggra Warsa Anyar",
            imageUrl = "salam/selamat_tahun_baru.jpg",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = "",
            audio = R.raw.audio_0004
        ),
        Word(
            id = "0005",
            chapterId = "00",
            indonesian = "Selamat Ulang Tahun",
            balinese = "Rahajeng Wanti Warsa",
            imageUrl = "salam/selamat_ulang_tahun.jpg",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wengi|Rahajeng Memargi",
            wordChild = "",
            audio = R.raw.audio_0005
        ),
        Word(
            id = "0006",
            chapterId = "00",
            indonesian = "Selamat Tinggal",
            balinese = "Rahajeng Memargi",
            imageUrl = "salam/selamat_tinggal.jpg",
            option = "Rahajeng Semeng|Rahajeng|Rahajeng Wanti Warsa|Rahajeng Wengi",
            wordChild = "",
            audio = R.raw.audio_0006
        ),
        Word(
            id = "0007",
            chapterId = "00",
            indonesian = "Terima Kasih",
            balinese = "Matur Suksma",
            imageUrl = "salam/terima_kasih.jpg",
            option = "Rahajeng Semeng|Rahajeng Wengi|Rahajeng Wanti Warsa|Rahajeng Memargi",
            wordChild = "",
            audio = R.raw.audio_0007
        ),

        //TODO: Panggilan
        Word(
            id = "0100",
            chapterId = "01",
            indonesian = "Saya",
            balinese = "Tiang/Titiang/Irage",
            imageUrl = "panggilan/saya.jpg",
            option = "Awak|Mbok|Bli|Pekak|Meme|Bapa|Dadong/Niang|Adin",
            wordChild = "",
            audio = R.raw.audio_0100
        ),
        Word(
            id = "0101",
            chapterId = "01",
            indonesian = "Kamu",
            balinese = "Awak",
            imageUrl = "panggilan/kamu.jpg",
            option = "Adin|Mbok|Bli|Pekak|Meme|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0101
        ),
        Word(
            id = "0102",
            chapterId = "01",
            indonesian = "Kakak Laki-laki",
            balinese = "Bli",
            imageUrl = "panggilan/kakak_laki_laki.jpg",
            option = "Awak|Mbok|Adin|Pekak|Meme|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0102
        ),
        Word(
            id = "0103",
            chapterId = "01",
            indonesian = "Kakak Perempuan",
            balinese = "Mbok",
            imageUrl = "panggilan/kakak_perempuan.jpg",
            option = "Awak|Adin|Bli|Pekak|Meme|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0103
        ),
        Word(
            id = "0104",
            chapterId = "01",
            indonesian = "Ayah",
            balinese = "Bapa",
            imageUrl = "panggilan/ayah.jpg",
            option = "Awak|Mbok|Bli|Pekak|Meme|Adin|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0104
        ),
        Word(
            id = "0105",
            chapterId = "01",
            indonesian = "Ibu",
            balinese = "Meme",
            imageUrl = "panggilan/ibu.jpg",
            option = "Awak|Mbok|Bli|Pekak|Adin|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0105
        ),
        Word(
            id = "0106",
            chapterId = "01",
            indonesian = "Kakek",
            balinese = "Pekak",
            imageUrl = "panggilan/kakek.jpg",
            option = "Awak|Mbok|Bli|Adin|Meme|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0106
        ),
        Word(
            id = "0107",
            chapterId = "01",
            indonesian = "Nenek",
            balinese = "Dadong/Niang",
            imageUrl = "panggilan/nenek.jpg",
            option = "Awak|Mbok|Bli|Pekak|Meme|Bapa|Adin|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0107
        ),
        Word(
            id = "0108",
            chapterId = "01",
            indonesian = "Adik",
            balinese = "Adi/Adin",
            imageUrl = "panggilan/adik.jpg",
            option = "Awak|Mbok|Bli|Pekak|Meme|Bapa|Dadong/Niang|Tiang/Titiang/Irage",
            wordChild = "",
            audio = R.raw.audio_0108
        ),

        //TODO: Hewan
        Word(
            id = "0200",
            chapterId = "02",
            indonesian = "Kuda",
            balinese = "Jaran",
            imageUrl = "hewan/kuda.jpg",
            option = "Sampi|Kebo|Meong|Lelipi|Yuyu|Pekak|Dadong",
            wordChild = "1200",
            audio = R.raw.audio_0200
        ),
        Word(
            id = "0201",
            chapterId = "02",
            indonesian = "Kambing",
            balinese = "Kambing",
            imageUrl = "hewan/kambing.jpg",
            option = "Sampi|Meong|Lelipi|Bangkung|Jaran|Meong|Mbok|Adin",
            wordChild = "1201",
            audio = R.raw.audio_0201
        ),
        Word(
            id = "0202",
            chapterId = "02",
            indonesian = "Kucing",
            balinese = "Meong",
            imageUrl = "hewan/kucing.jpg",
            option = "Jaran|Kebo|Bangkung|Lelipi|Cicing|Adin|Pekak",
            wordChild = "1202",
            audio = R.raw.audio_0202
        ),
        Word(
            id = "0203",
            chapterId = "02",
            indonesian = "Bebek",
            balinese = "Bebek",
            imageUrl = "hewan/bebek.jpg",
            option = "Sampi|Bojog|Jaran|Meong|Cicing|Yuyu|Bojog",
            wordChild = "1203",
            audio = R.raw.audio_0203
        ),
        Word(
            id = "0204",
            chapterId = "02",
            indonesian = "Anjing",
            balinese = "Cicing",
            imageUrl = "hewan/anjing.jpg",
            option = "Bikul|Kebo|Bangkung|Lelipi|Jaran|Meong|Bojog",
            wordChild = "1204",
            audio = R.raw.audio_0204
        ),
        Word(
            id = "0205",
            chapterId = "02",
            indonesian = "Kerbau",
            balinese = "Kebo",
            imageUrl = "hewan/kerbau.jpg",
            option = "Bojog|Meong|Bikul|Lelipi|Cicing|Jaran|Meong",
            wordChild = "1205",
            audio = R.raw.audio_0205
        ),
        Word(
            id = "0206",
            chapterId = "02",
            indonesian = "Tikus",
            balinese = "Bikul",
            imageUrl = "hewan/tikus.jpg",
            option = "Sampi|Kebo|Jaran|Meong|Cicing|Lelipi|Yuyu",
            wordChild = "1206",
            audio = R.raw.audio_0206
        ),
        Word(
            id = "0207",
            chapterId = "02",
            indonesian = "Babi",
            balinese = "Bangkung",
            imageUrl = "hewan/babi.jpg",
            option = "Sampi|Bojog|Jaran|Meong|Bikul|Cicing|Lelipi|Yuyu",
            wordChild = "1207",
            audio = R.raw.audio_0207
        ),
        Word(
            id = "0208",
            chapterId = "02",
            indonesian = "Ular",
            balinese = "Lelipi",
            imageUrl = "hewan/ular.jpg",
            option = "Bojog|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul",
            wordChild = "1208",
            audio = R.raw.audio_0208
        ),
        Word(
            id = "0209",
            chapterId = "02",
            indonesian = "Monyet/Kera",
            balinese = "Bojog",
            imageUrl = "hewan/monyet.jpg",
            option = "Sampi|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul|Lelipi",
            wordChild = "1209",
            audio = R.raw.audio_0209
        ),
        Word(
            id = "0210",
            chapterId = "02",
            indonesian = "Sapi",
            balinese = "Sampi",
            imageUrl = "hewan/sapi.jpg",
            option = "Bojog|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul|Lelipi",
            wordChild = "1210",
            audio = R.raw.audio_0210
        ),
        Word(
            id = "0211",
            chapterId = "02",
            indonesian = "Kepiting",
            balinese = "Yuyu",
            imageUrl = "hewan/kepiting.jpg",
            option = "Bojog|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul|Lelipi|Sampi",
            wordChild = "12011",
            audio = R.raw.audio_0211
        ),

        //TODO: Child
        Word(
            id = "1200",
            chapterId = "12",
            indonesian = "Anak Kuda",
            balinese = "Bebedag",
            imageUrl = "hewan/anak_kuda.jpg",
            option = "Bojog|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul|Lelipi|Sampi",
            audio = R.raw.audio_1200
        ),
        Word(
            id = "1201",
            chapterId = "12",
            indonesian = "Anak Kambing",
            balinese = "Wiwi",
            imageUrl = "hewan/anak_kambing.jpg",
            option = "Bojog|Kebo|Jaran|Meong|Cicing|Bangkung|Bikul|Bebedag|Kambing",
            audio = R.raw.audio_1201
        ),
        Word(
            id = "1202",
            chapterId = "12",
            indonesian = "Anak Kucing",
            balinese = "Tai",
            imageUrl = "hewan/anak_kucing.jpg",
            option = "Sampi|Kebo|Lelipi|Meong|Cicing|Bangkung|Bikul|Bebedag|Wiwi",
            audio = R.raw.audio_1202
        ),
        Word(
            id = "1203",
            chapterId = "12",
            indonesian = "Anak Bebek",
            balinese = "Memeri",
            imageUrl = "hewan/anak_bebek.jpg",
            option = "Sampi|Kebo|Lelipi|Meong|Cicing|Bebek|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1203
        ),
        Word(
            id = "1204",
            chapterId = "12",
            indonesian = "Anak Anjing",
            balinese = "Kuluk",
            imageUrl = "hewan/anak_anjing.jpg",
            option = "Sampi|Kebo|Lelipi|Yuyu|Cicing|Bedigal|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1204
        ),
        Word(
            id = "1205",
            chapterId = "12",
            indonesian = "Anak Kerbau",
            balinese = "Bedigal",
            imageUrl = "hewan/anak_kerbau.jpg",
            option = "Jaran|Kebo|Lelipi|Yuyu|Kebo|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1205
        ),
        Word(
            id = "1206",
            chapterId = "12",
            indonesian = "Anak Tikus",
            balinese = "Nying-nying",
            imageUrl = "hewan/anak_tikus.jpg",
            option = "Sampi|Kebo|Bedigal|Bikul|Cicing|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1206
        ),
        Word(
            id = "1207",
            chapterId = "12",
            indonesian = "Anak Babi",
            balinese = "Kucit",
            imageUrl = "hewan/anak_babi.jpg",
            option = "Nying-nying|Bangkung|Lelipi|Yuyu|Kebo|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1207
        ),
        Word(
            id = "1208",
            chapterId = "12",
            indonesian = "Anak Ular",
            balinese = "Brara",
            imageUrl = "hewan/anak_ular.jpg",
            option = "Nying-nying|Bangkung|Lelipi|Kucit|Kebo|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1208
        ),
        Word(
            id = "1209",
            chapterId = "12",
            indonesian = "Anak Monyet/Kera",
            balinese = "Apa",
            imageUrl = "hewan/anak_monyet.jpg",
            option = "Nying-nying|Bangkung|Lelipi|Brara|Bojog|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1209
        ),
        Word(
            id = "1210",
            chapterId = "12",
            indonesian = "Anak Sapi",
            balinese = "Godel",
            imageUrl = "hewan/anak_sapi.jpg",
            option = "Nying-nying|Bangkung|Lelipi|Yuyu|Kebo|Kuluk|Tai|Sampi|Apa",
            audio = R.raw.audio_1210
        ),
        Word(
            id = "1211",
            chapterId = "12",
            indonesian = "Anak Kepiting",
            balinese = "Kreket/Utung-utung",
            imageUrl = "hewan/anak_kepiting.jpg",
            option = "Nying-nying|Bangkung|Lelipi|Godel|Yuyu|Kuluk|Tai|Bebedag|Wiwi",
            audio = R.raw.audio_1211
        ),

        //TODO: Angka
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