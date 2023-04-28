package com.example.bahasaku.data

import com.example.bahasaku.core.components.CourseType
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course

object Populate {
    val populateChapter = listOf(
        Chapter(
            id = "0",
            title = "Salam dan Perkenalan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "1",
            title = "Benda",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "2",
            title = "Angka",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "3",
            title = "Hewan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "4",
            title = "Makanan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "5",
            title = "Minuman",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "6",
            title = "Amggota Badan",
            courseNumber = 0,
            imageUrl = "Hewan/anak_bebek.png"
        ),
        Chapter(
            id = "7",
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
}