# AdPro Eshop
**Nama:**   Henry Aditya Kosasi<br>
**NPM:**    2306214990<br>
**Kelas:**  AdPro A<br>
# Navigation List
- [Module 1](#module-1)


## Module 1
### Reflection 1
Pada tutorial pertama ini, saya berusaha untuk menulis code saya sedeskriptif mungkin, sesuai dengan prinsip bahwa code yang kita tulis harus bisa menjelaskan fungsi dirinya sendiri. Saya juga berusaha untuk tidak menggunakan comment, karena pada dasarnya kita disarankan memakai comment hanya untuk kasus tertentu seperti license legal dan lainnya.
<br><br>
Saya juga mencoba mengimplementasikan sedikit validasi di input edit ProductQuantity, dimana harus merupakan integer dan tidak boleh berupa angka negatif. Namun, selain dari itu saya tidak memberikan validasi input seperti pada create product.
Saya juga memberikan ProductId dalam bentuk UUID, dengan begitu path url tidak mudah dibobol dengan memasukkan id yang incremental (1, 2, 3, ...).
<br><br>
Masih cukup banyak hal yang bisa diimprovisasi dari code saya, seperti mengimplementasikan error handling dengan benar (agar tidak muncul page dengan tampilan error-errornya), memberikan validasi input di semua bagian, lalu mengimplementasikan autentikasi dan otorisasi.