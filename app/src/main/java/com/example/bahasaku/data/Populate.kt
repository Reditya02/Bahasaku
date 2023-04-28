package com.example.bahasaku.data

import com.example.bahasaku.core.components.CourseType
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Word

object Populate {
    val populateChapter = listOf(
        Chapter(
            id = "00",
            title = "Salam",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "01",
            title = "Panggilan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "02",
            title = "Hewan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "03",
            title = "Angka",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "04",
            title = "Benda",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "05",
            title = "Makanan dan Minuman",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "06",
            title = "Amggota Badan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "07",
            title = "Kata Kerja",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),

        //Child
        Chapter(
            id = "12",
            title = "Kata Kerja",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
    )

    val populateCourse = listOf(
        Course(
            id = "111",
            chapterId = "1",
            name = "Salam dan waktu",
            type = CourseType.Reading,
            reading = "",
            isAvailable = true
        ),
        Course(
            id = "121",
            chapterId = "1",
            name = "Salam dan waktu",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "112",
            chapterId = "1",
            name = "Nama hari dan mata angin",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "122",
            chapterId = "1",
            name = "Nama hari dan mata angin",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "113",
            chapterId = "1",
            name = "Keluarga",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "123",
            chapterId = "1",
            name = "Keluarga",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "114",
            chapterId = "1",
            name = "Angka I",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "124",
            chapterId = "1",
            name = "Angka I",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "115",
            chapterId = "1",
            name = "Pengalaman",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "125",
            chapterId = "1",
            name = "Pengalaman",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "116",
            chapterId = "1",
            name = "Kata kerja dasar",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "126",
            chapterId = "1",
            name = "Kata kerja dasar",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "117",
            chapterId = "1",
            name = "Berkenalan",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "127",
            chapterId = "1",
            name = "Berkenalan",
            type = CourseType.Exercise,
            reading = ""
        ),

        Course(
            id = "211",
            chapterId = "2",
            name = "Kata kerja berimbuhan",
            type = CourseType.Reading,
            reading = "",
            isAvailable = true
        ),
        Course(
            id = "221",
            chapterId = "2",
            name = "Kata kerja berimbuhan",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "212",
            chapterId = "2",
            name = "Barang di sekolah",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "222",
            chapterId = "2",
            name = "Barang di sekolah",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "213",
            chapterId = "2",
            name = "Kata kerja dasar dan berimbuhan I",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "223",
            chapterId = "2",
            name = "Kata kerja dasar dan berimbuhan I",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "214",
            chapterId = "2",
            name = "Barang untuk bersih-bersih",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "224",
            chapterId = "2",
            name = "Barang untuk bersih-bersih",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "215",
            chapterId = "2",
            name = "Anggota badan",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "225",
            chapterId = "2",
            name = "Anggota badan",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "216",
            chapterId = "2",
            name = "Jenis kalimat",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "226",
            chapterId = "2",
            name = "Jenis kalimat",
            type = CourseType.Exercise,
            reading = ""
        ),

        Course(
            id = "311",
            chapterId = "3",
            name = "Kata kerja dasar dan berimbuhan II",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "321",
            chapterId = "3",
            name = "Kata kerja dasar dan berimbuhan II",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "312",
            chapterId = "3",
            name = "Binatang",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "322",
            chapterId = "3",
            name = "Binatang",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "313",
            chapterId = "3",
            name = "Tanaman",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "323",
            chapterId = "3",
            name = "Tanaman",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "314",
            chapterId = "3",
            name = "Makanan",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "324",
            chapterId = "3",
            name = "Makanan",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "315",
            chapterId = "3",
            name = "Warna",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "325",
            chapterId = "3",
            name = "Warna",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "316",
            chapterId = "3",
            name = "Angka II",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "326",
            chapterId = "3",
            name = "Angka II",
            type = CourseType.Exercise,
            reading = ""
        ),

        Course(
            id = "411",
            chapterId = "4",
            name = "Kata kerja dasar dan berimbuhan III",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "421",
            chapterId = "4",
            name = "Kata kerja dasar dan berimbuhan III",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "412",
            chapterId = "4",
            name = "Barang di dapur",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "422",
            chapterId = "4",
            name = "Barang di dapur",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "413",
            chapterId = "4",
            name = "Pekerjaan",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "423",
            chapterId = "4",
            name = "Pekerjaan",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "414",
            chapterId = "4",
            name = "Anak binatang",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "424",
            chapterId = "4",
            name = "Anak binatang",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "415",
            chapterId = "4",
            name = "Cuaca",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "425",
            chapterId = "4",
            name = "Cuaca",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "416",
            chapterId = "4",
            name = "Kata sifat",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "426",
            chapterId = "4",
            name = "Kata sifat",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "417",
            chapterId = "4",
            name = "Kata bilangan",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "427",
            chapterId = "4",
            name = "Kata bilangan",
            type = CourseType.Exercise,
            reading = ""
        ),

        Course(
            id = "511",
            chapterId = "5",
            name = "Kata kerja dasar dan berimbuhan IV",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "521",
            chapterId = "5",
            name = "Kata kerja dasar dan berimbuhan IV",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "512",
            chapterId = "5",
            name = "Peralatan di sawah",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "522",
            chapterId = "5",
            name = "Peralatan di sawah",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "513",
            chapterId = "5",
            name = "Bencana alam",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "523",
            chapterId = "5",
            name = "Bencana alam",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "514",
            chapterId = "5",
            name = "Kata berulang",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "524",
            chapterId = "5",
            name = "Kata berulang",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "515",
            chapterId = "5",
            name = "Antonim",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "525",
            chapterId = "5",
            name = "Antonim",
            type = CourseType.Exercise,
            reading = ""
        ),
        Course(
            id = "516",
            chapterId = "5",
            name = "Sinonim",
            type = CourseType.Reading,
            reading = ""
        ),
        Course(
            id = "526",
            chapterId = "5",
            name = "Sinonim",
            type = CourseType.Exercise,
            reading = ""
        ),

    )

    val populateWord = listOf(
        //Salam
        Word(
            id = "0000",
            courseId = "00",
            indonesian = "Selamat Pagi",
            balinese = "Rahajeng Semeng",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0001",
            courseId = "00",
            indonesian = "Selamat Siang",
            balinese = "Rahajeng Siang",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0002",
            courseId = "00",
            indonesian = "Selamat Malam",
            balinese = "Rahajeng Wengi",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0003",
            courseId = "00",
            indonesian = "Selamat Makan",
            balinese = "Rahajeng Majengan",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0004",
            courseId = "00",
            indonesian = "Selamat Tahun Baru",
            balinese = "Rahajeng Nyanggra Warsa Anyar",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0005",
            courseId = "00",
            indonesian = "Selamat Ulang Tahun",
            balinese = "Rahajeng Wanti Warsa",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0006",
            courseId = "00",
            indonesian = "Selamat Tinggal",
            balinese = "Rahajeng Memargi",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),
        Word(
            id = "0007",
            courseId = "00",
            indonesian = "Semoga Selamat",
            balinese = "Dumogi Rahayu",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),

        //Panggilan
        Word(
            id = "0100",
            courseId = "01",
            indonesian = "",
            balinese = "",
            imageUrl = "",
            option = "",
            wordChild = ""
        ),

        //Hewan
        Word(
            id = "0200",
            courseId = "02",
            indonesian = "Kuda",
            balinese = "Jaran",
            imageUrl = "",
            option = "",
            wordChild = "1200"
        ),
        Word(
            id = "0201",
            courseId = "02",
            indonesian = "Kambing",
            balinese = "Kambing",
            imageUrl = "",
            option = "",
            wordChild = "1201"
        ),
        Word(
            id = "0202",
            courseId = "02",
            indonesian = "Kucing",
            balinese = "Meong",
            imageUrl = "",
            option = "",
            wordChild = "1202"
        ),
        Word(
            id = "0203",
            courseId = "02",
            indonesian = "Bebek",
            balinese = "Bebek",
            imageUrl = "",
            option = "",
            wordChild = "1203"
        ),
        Word(
            id = "0204",
            courseId = "02",
            indonesian = "Anjing",
            balinese = "Cicing",
            imageUrl = "",
            option = "",
            wordChild = "1204"
        ),
        Word(
            id = "0205",
            courseId = "02",
            indonesian = "Kerbau",
            balinese = "Kebo",
            imageUrl = "",
            option = "",
            wordChild = "1205"
        ),
        Word(
            id = "0206",
            courseId = "02",
            indonesian = "Tikus",
            balinese = "Bikul",
            imageUrl = "",
            option = "",
            wordChild = "1206"
        ),
        Word(
            id = "0207",
            courseId = "02",
            indonesian = "Babi",
            balinese = "Bangkung",
            imageUrl = "",
            option = "",
            wordChild = "1207"
        ),
        Word(
            id = "0208",
            courseId = "02",
            indonesian = "Ular",
            balinese = "Lelipi",
            imageUrl = "",
            option = "",
            wordChild = "1208"
        ),
        Word(
            id = "0209",
            courseId = "02",
            indonesian = "Monyet/Kera",
            balinese = "Bojog",
            imageUrl = "",
            option = "",
            wordChild = "1209"
        ),
        Word(
            id = "0210",
            courseId = "02",
            indonesian = "Sapi",
            balinese = "Sampi",
            imageUrl = "",
            option = "",
            wordChild = "1210"
        ),
        Word(
            id = "0211",
            courseId = "02",
            indonesian = "Kepiting",
            balinese = "Yuyu",
            imageUrl = "",
            option = "",
            wordChild = "12011"
        ),

        //Child
        Word(
            id = "1200",
            courseId = "12",
            indonesian = "Anak Kuda",
            balinese = "Bebedag",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1201",
            courseId = "12",
            indonesian = "Anak Kambing",
            balinese = "Wiwi",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1202",
            courseId = "12",
            indonesian = "Anak Kucing",
            balinese = "Tai",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1203",
            courseId = "12",
            indonesian = "Anak Bebek",
            balinese = "Memeri",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1204",
            courseId = "12",
            indonesian = "Anak Anjing",
            balinese = "Kuluk",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1205",
            courseId = "12",
            indonesian = "Anak Kerbau",
            balinese = "Bedigal",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1206",
            courseId = "12",
            indonesian = "Anak Tikus",
            balinese = "Nyinh-nying",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1207",
            courseId = "12",
            indonesian = "Anak Babi",
            balinese = "Kucit",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1208",
            courseId = "12",
            indonesian = "Anak Ular",
            balinese = "Brara",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1209",
            courseId = "12",
            indonesian = "Anak Monyet/Kera",
            balinese = "Apa",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1210",
            courseId = "12",
            indonesian = "Anak Sapi",
            balinese = "Godel",
            imageUrl = "",
            option = "",
        ),
        Word(
            id = "1211",
            courseId = "12",
            indonesian = "Anak Kepiting",
            balinese = "Kreket/Utung-utung",
            imageUrl = "",
            option = "",
        ),

        //Makanan

        //Minuman

        //Anggota Badan

        //Kata Kerja
    )
}