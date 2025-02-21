# AdPro Eshop
**Nama:**   Henry Aditya Kosasi<br>
**NPM:**    2306214990<br>
**Kelas:**  AdPro A<br>
# Navigation List
- [Module 1](#module-1)
- [Module 2](#module-2)
# Deployment Link
Website deployed at koyeb: https://henry-adpro-tutorial.koyeb.app/

## Module 1
### Reflection 1
Pada tutorial pertama ini, saya berusaha untuk menulis code saya sedeskriptif mungkin, sesuai dengan prinsip bahwa code yang kita tulis harus bisa menjelaskan fungsi dirinya sendiri. Saya juga berusaha untuk tidak menggunakan comment, karena pada dasarnya kita disarankan memakai comment hanya untuk kasus tertentu seperti license legal dan lainnya.
<br><br>
Saya juga mencoba mengimplementasikan sedikit validasi di input edit ProductQuantity, dimana harus merupakan integer dan tidak boleh berupa angka negatif. Namun, selain dari itu saya tidak memberikan validasi input seperti pada create product.
Saya juga memberikan ProductId dalam bentuk UUID, dengan begitu path url tidak mudah dibobol dengan memasukkan id yang incremental (1, 2, 3, ...).
<br><br>
Masih cukup banyak hal yang bisa diimprovisasi dari code saya, seperti mengimplementasikan error handling dengan benar (agar tidak muncul page dengan tampilan error-errornya), memberikan validasi input di semua bagian, lalu mengimplementasikan autentikasi dan otorisasi.

### Reflection 2
1. - Setelah menulis unit_test, saya merasa lebih aman dan juga efisien. Saya menyadari bahwa dengan menggunakan unit_test seperti ini, saya setidaknya mengurangi kemungkinan terlewatnya bug baru setelah mengimplementasikan fitur baru.
- Menurut saya tergantung seberapa kompleks dan seberapa efektif unit_test kita (bisa dibilang menyesuaikan dengan kebutuhan). Tapi secara umum harusnya yang penting bisa mengcover kasus-kasus umum dan mungkin beberapa edge case.
- Salah satu acuan yang bisa kita gunakan yaitu dengan melihat seberapa besar code coverage dari unit test kita. Jika code coverage sudah 100%, bukan berarti code kita sudah pasti bebas dari seluruh bug dan error. Code coverage itu sebatas memberi tahu seberapa banyak baris code kita yang sudah diuji, namun itu bisa saja masih ada edge case yang belum diuji.
  <br><br>
2. Menurut saya, membuat class Java baru untuk mengetest jumlah item dalam product list dengan setup dan instance variable yang sama seperti CreateProductFunctionalTest.java bisa menimbulkan beberapa masalah dalam hal clean coding. Pertama, yaitu karena menyebabkan code duplication, di mana logika setup dan instance variable yang seharusnya bisa digunakan kembali justru diulang di class yang baru. Hal ini melanggar prinsip DRY (Don't Repeat Yourself) dan membuat kode menjadi lebih sulit untuk dimaintain. Kedua, menambahkan class baru dapat membuat orang bingung kenapa ada dua class terpisah yang menguji fungsionalitas yang saling terkait tapi malah dipisah menjadi dua class berbeda.
   <br><br>
   Sebagai solusi, lebih baik jika kita tambahkan test case barunya ke dalam file CreateProductFunctionalTest.java. Dengan begitu, kita bisa menghindari code duplication, menjaga kode tetap terorganize, dan memudahkan maintenance ke depannya.


## Module 2
1. Terdapat beberapa code quality issue yang saya perbaiki selama mengerjakan exercise modul 2 ini. Code quality tersebut yaitu:
- Yang pertama, yaitu menghapus beberapa method setUp() yang tidak terlalu dibutuhkan. Method ini tidak saya beri implementasi apapun, sehingga sonarcloud mendeteksinya sebagai code yang dapat berujung membuat kebingungan orang lain yang membacanya. Dengan begitu, solusi saya adalah menghapus method setUp() yang ada.
    ```java
        public class ProductServiceImplTest {
            @BeforeEach
            void setUp(){
            }
            ...
        }
    ```
- Yang kedua, yaitu menghapus beberapa modifier public pada file test yang saya buat. Pada JUnit 4, semua test harus dibuat public agar bisa dijalankan, namun pada JUnit 5, kelas uji dan metode uji tidak harus public, asalkan tidak menggunakan private. Artinya, kita bisa menggunakan default (tanpa modifier), sehingga membuat code lebih rapi dan mudah dibaca. Contohnya:
    ```java
        public class ProductServiceImplTest {
           ... 
        }
        // Bisa diubah menjadi:
        class ProductServiceImplTest {
           ... 
        }
    ```
  
2. Menurut saya, implementasi workflow CI/CD yang saya terapkan di eshop ini sudah sesuai dengan prinsip Continuous Integration dan Continuous Deployment.
    <br><br>
    Pertama, dari sisi Continuous Integration, saya sudah menggunakan GitHub Actions untuk menjalankan proses otomatis setiap kali ada perubahan kode yang di-push ke repositori. Proses ini mencakup build, testing, dan analisis kode menggunakan tools-tools seperti Gradle, SonarCloud, dan juga Scorecard. Dengan begitu, setiap perubahan kode terintegrasi dengan baik dan diverifikasi secara otomatis, sesuai dengan definisi Continuous Integration.
    <br><br>
    Kedua, untuk Continuous Deployment, saya memakai Koyeb yang terhubung langsung dengan repositori eshop. Setiap kali ada perubahan kode yang di-push, Koyeb secara otomatis melakukan pull dan deployment ulang tanpa memerlukan campur tangan saya. Menurut saya ini sudah memenuhi prinsip Continuous Deployment, di mana setiap perubahan yang lolos tahap integrasi langsung diterapkan ke lingkungan produksi.
    <br><br>
    Dengan begitu, workflow CI/CD yang saya terapkan sudah memenuhi kedua aspek tersebut, yaitu integrasi kode yang terus-menerus dan otomatis, serta deployment yang langsung ke lingkungan produksi tanpa hambatan.