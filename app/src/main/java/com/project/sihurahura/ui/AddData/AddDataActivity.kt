package com.project.sihurahura.ui.AddData

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.data.model.addData.AddData
import com.project.sihurahura.databinding.ActivityAddDataBinding
import com.project.sihurahura.ui.Home.HomeActivity
import com.project.sihurahura.util.Prefs
import com.project.sihurahura.util.VmData
import com.project.sihurahura.util.toast
import java.io.ByteArrayOutputStream

class AddDataActivity : AppCompatActivity() {
    private val binding: ActivityAddDataBinding by viewBinding()
    private val viewModel: AddDataViewModel by viewModels()
    private var prefs: Prefs? = null
    private var id: Int? = null
    private val REQUEST_CODE = 100
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = this.let { Prefs(it) }
        id = prefs?.getValueInt("id")
        Log.e("TAG", "id: $id")

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        with(binding) {
            setSupportActionBar(toolbarIsolasi)

            var isShow = true
            var scrollRange = -1
            binding.appBarIsolasi.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = "Lengkapi Data"
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    toolbarIsolasi.setNavigationIcon(R.drawable.ic_arrow_24dp)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    isShow = false
                }
            })

            btnBackIsolasi.setOnClickListener {
                finish()
            }

            submitButton.setOnClickListener {
                val nama = nama.text.toString().trim()
                val ttl = ttl.text.toString().trim()
                val jenis_kel = jenisKel.text.toString().trim()
                val agama = agama.text.toString().trim()
                val no_hp = noHp.text.toString().trim()
                val alamat = alamat.text.toString().trim()
                val asal_sekolah = asalSekolah.text.toString().trim()
                val jurusan_sekolah = jurusanSekolah.text.toString().trim()
                val nisn = nisn.text.toString().trim()
                val prodi = prodi.text.toString().trim()
                val status_daftar = statusDaftar.text.toString().trim()
                val nama_ayah = namaAyah.text.toString().trim()
                val status_ortu = statusOrtu.text.toString().trim()
                val pekerjaan = pekerjaan.text.toString().trim()
                val penghasilan = penghasilan.text.toString().trim()
                val no_hp_ortu = noHpOrtu.text.toString().trim()
                val nilai_ijasah = nilaiIjasah.text.toString().trim()
                val nilai_transkrip = nilaiTranskrip.text.toString().trim()


                val validate =
                    nama.isNotEmpty() && ttl.isNotEmpty() && jenis_kel.isNotEmpty() && agama.isNotEmpty() && no_hp.isNotEmpty() && alamat.isNotEmpty() && asal_sekolah.isNotEmpty() &&
                            jurusan_sekolah.isNotEmpty() && nisn.isNotEmpty() && prodi.isNotEmpty() && status_daftar.isNotEmpty() && nama_ayah.isNotEmpty() && status_ortu.isNotEmpty() &&
                            pekerjaan.isNotEmpty() && penghasilan.isNotEmpty() && no_hp_ortu.isNotEmpty() && nilai_ijasah.isNotEmpty() && nilai_transkrip.isNotEmpty()

                if (validate) {
                    viewModel.postDataUSer(
                        AddData(
                            id,
                            nama,
                            ttl,
                            jenis_kel,
                            agama,
                            no_hp,
                            alamat,
                            asal_sekolah,
                            jurusan_sekolah,
                            nisn,
                            prodi,
                            status_daftar,
                            nama_ayah,
                            status_ortu,
                            pekerjaan,
                            penghasilan,
                            no_hp_ortu,
                            nilai_ijasah,
                            nilai_transkrip,
                            "poto.jpg"
                        )
                    )
                } else {
                    toast("Lengkapi Data Dahulu . . .")
                }
            }
            btnPhoto.setOnClickListener {
                openGalleryForImage()
            }
        }
        setObservableAddData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val path: Uri? = data?.data
            binding.image.setImageURI(data?.data) // handle chosen image
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setObservableAddData() {
        viewModel.addDataUser.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    toast("Loading . . .")
                    binding.submitButton.isEnabled = false
                    binding.submitButton.setBackgroundColor(R.color.browser_actions_bg_grey)
                }

                is VmData.Success -> {
                    if (it.data.statusCode != 200) {
                        toast("Cek data kembali")
                    } else {
                        toast("${it.data.message}")
                        intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                is VmData.Failure -> {
                    toast("${it.message}")
                    binding.submitButton.isEnabled = true
                    binding.submitButton.setBackgroundColor(R.color.blue)
                }
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun convertToString(): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imgByte: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imgByte, Base64.DEFAULT)
    }
}