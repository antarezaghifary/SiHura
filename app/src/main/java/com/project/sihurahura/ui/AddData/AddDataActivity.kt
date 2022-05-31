package com.project.sihurahura.ui.AddData

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.data.model.addData.AddData
import com.project.sihurahura.databinding.ActivityAddDataBinding
import com.project.sihurahura.ui.Main.MainActivity
import com.project.sihurahura.util.VmData
import com.project.sihurahura.util.toast

class AddDataActivity : AppCompatActivity() {
    private val binding: ActivityAddDataBinding by viewBinding()

    private val viewModel: AddDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    collapsingToolbar.title = "Beasiswa"
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
                            nilai_transkrip
                        )
                    )
                } else {
                    toast("Lengkapi Data Dahulu . . .")
                }
            }
        }
        setObservableAddData()
    }

    private fun setObservableAddData() {
        viewModel.addDataUser.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    toast("Loading . . .")
                }

                is VmData.Success -> {
                    if (it.data.statusCode != 200) {
                        toast("Cek data kembali")
                    } else {
                        toast("${it.data.message}")
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                is VmData.Failure -> {
                    toast("${it.message}")
                }
            }
        }
    }
}