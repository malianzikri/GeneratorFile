# GeneratorFile
Generator FIle 
Projek ini merupakan kumpulan beberapa package dimana setiap package mengadung class yang memiliki fungsi-fungsi khusus.
- Package XML Generator
  terdiri dari dua file class yaitu generatorXML dan XMLParsing
    - generatorXML memiliki fungsi untuk menggenerate secara otomatis file xml dimana user hanya perlu memasukan taging yang           digunakan dan isinya.
    - XMLParsing memiliki fungsi untuk membaca file xml dengan otomatis.
      user hanya perlu memasukan tag yang mau diambil.
- Package DatabaseGenerator
  terdiri dari satu class yaitu GeneratorDatabase
    -GeneratorDatabase memiliki fungsi untuk melakukan query select,insert,update, dan delete secara otomatis.
    user hanya perlu memasukan nama table serta column data yang akan diambil.
- Package File
  terdiri dari dua class yaitu cluster file dan delete file
    - ClusterFile memiliki fungsi untuk mengelompokan data berdasarkan tanggal pembuatan
    - DeleteFile memiliki fungsi untuk menghapus file yang berada di direktori
- Package File.DOCX
  terdiri dari dua class yaitu ReadDoc dan WriteDoc
  - ReadDoc berfungsi untuk membaca file word yang berekstensi .docx
  - WriteDoc berfungsi untuk menulis file word
- Package File.XLS
  terdiri dari dua class yaitu CreateXLS dan ReadXLS
  - CreateXLS berfungsi untuk membuat file spreadsheet dengan format .xls
  - ReadXLS berfungsi untuk membaca file spreadsheet dengan format .xls
- Package File.XLSX
  terdiri dari dua class yaitu CreateXLSX dan ReadXLSX
  - CreateXLSX berfungsi untuk membuat file spreadsheet dengan format .xlsx
  - ReadXLSX berfungsi untuk membaca file spreadsheet dengan format .xlsx
- Package File_TXT
  terdiri dari dua class yaitu class CreateFile dan ReadFile
  - CreaFile berfungsi untuk membuat file .txt
  - ReadFile berfungsi untuk membaca file .txt pada suatu direktori
- Package Loggers
  terdiri dari satu class yaitu Loggers
  - Loggers berfungsi untuk mencatatat log dari program baik log eror maupun log berhasil
