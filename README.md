# AdPro Eshop
**Nama:**   Henry Aditya Kosasi<br>
**NPM:**    2306214990<br>
**Kelas:**  AdPro A<br>
# Navigation List
- [Module 1](#module-1)
- [Module 2](#module-2)
- [Module 3](#module-3)
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



## Module 3
1. Explain what principles you apply to your project!
   Pada Modul 3 kali ini, terdapat beberapa Prinsip yang saya terapkan, yaitu:
   1. Single Responsibility Principle
        - Saya memisahkan CarController dari file ProductController, dengan begitu setiap class dan file kini memiliki tanggung jawab yang spesifik. Hal ini membuat proses maintenance ke depannya menjadi lebih mudah karena perubahan pada satu bagian tidak akan memengaruhi bagian lainnya.
   2. Open-Closed Principle
        - Saya membuat service baru yaitu ModelService, disini CarService dan ProductService akan extends ke ModelService. Dimana ini sudah sesuai dengan isi dari OCP, yaitu untuk terbuka terhadap ekstensi dan tertutup terhadap modifikasi. Jadinya kalau di masa depan perlu penambahan fungsi di CarService atau ProductService, maka dapat dilakukan tanpa perlu memodifikasi kode yang ada di ModelService.
   3. Liskov Substitution Principle
        - Sebelumnya CarController ada di ProductController dan dia extends ke ProductController, namun hal ini melanggar konsep LSP. Liskov Substitution Principle menyatakan bahwa objek dari subclass harus bisa menggantikan objek dari superclass tanpa mengubah kebenaran atau perilaku program. Disini CarController dan ProductController tidak bisa saling menggantikan, dengan begitu lebih baik dipisah saja, mereka seharusnya tidak memiliki hubungan inheritance jika perilakunya berbeda.
   4. Interface Segregation Principle
        - Jujur saya merasa tidak ada yang perlu diubah lagi untuk prinsip ini, sepertinya sudah terimplementasikan dengan baik karena CarService dan ProductService implementasi methodnya sudah sesuai dan sama seperti yang ada di ModelService. Karena CarService dan ProductService tidak menambahkan method baru yang tidak ada di ModelService, ini menunjukkan bahwa mereka hanya mengimplementasikan method yang benar-benar dibutuhkan. Sehingga udah best practice untuk ISP.
   5. Dependency Inversion Principle
        - Prinsip Dependency Inversion Principle (DIP) menyatakan bahwa modul high-level tidak boleh bergantung pada modul low-level. Keduanya harus bergantung pada abstraksi. Dengan begitu saya ubah agar CarController agar bergantung kepada CarService, bukan CarServiceImpl.
2. Explain the advantages of applying SOLID principles to your project with examples.
    - Setiap class atau modul memiliki tanggung jawab yang jelas dan terpisah, sehingga memudahkan maintenance dan pengembangan. Selain itu kode menjadi lebih terorganisir dan mudah dibaca karena fokusnya cukup satu tugas.  Contohnya, dengan memisahkan CarController dan ProductController, maka perubahan pada ProductController tidak akan memengaruhi CarController.
    - Kode menjadi lebih fleksibel dan mudah dikembangkan tanpa perlu mengubah kode yang sudah ada. Dengan membuat ModelService sebagai base class untuk CarService dan ProductService, kita bisa menambahkan fungsionalitas baru di CarService atau ProductService tanpa mengubah ModelService. 
   
3. Explain the disadvantages of not applying SOLID principles to your project with examples.
    - Jika dibiarkan, class atau modul ke depannya bisa memiliki terlalu banyak tanggung jawab, sehingga sulit di-maintain dan rentan terhadap bug. Conthnya, Jika CarController dan ProductController digabung dalam satu class, perubahan pada ProductController bisa saja memengaruhi CarController juga.
    - Kode Sulit Dipahami oleh Developer Lain. Tidak hanya developer lain, tapi diri kita juga terdampak. Jika kita mengimplementasikan suatu fungsi dan setelah beberapa saat baru kembali ke fungsi itu, kita mungkin akan kesulitan membaca ulang code yang kita buat jika tidak mengikuti prinsip SOLID. Misalnya, jika sebuah fungsi atau class terlalu panjang dan melakukan banyak hal (melanggar SRP), tentunya setiap kali kita atau orang lain baca akan lebih effort untuk bisa mengerti. 
    - Kode menjadi kaku dan sulit dikembangkan, karena setiap penambahan fitur baru memerlukan modifikasi pada kode yang sudah ada. 