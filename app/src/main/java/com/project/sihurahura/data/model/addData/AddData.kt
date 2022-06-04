package com.project.sihurahura.data.model.addData

import com.google.gson.annotations.SerializedName

data class AddData(
    @SerializedName("id_user") val id: Int?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("ttl") val ttl: String?,
    @SerializedName("jenis_kel") val jenis_kel: String?,
    @SerializedName("agama") val agama: String?,
    @SerializedName("no_hp") val no_hp: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("asal_sekolah") val asal_sekolah: String?,
    @SerializedName("jurusan_sekolah") val jurusan_sekolah: String?,
    @SerializedName("nisn") val nisn: String?,
    @SerializedName("prodi") val prodi: String?,
    @SerializedName("status_daftar") val status_daftar: String?,
    @SerializedName("nama_ayah") val nama_ayah: String?,
    @SerializedName("status_ortu") val status_ortu: String?,
    @SerializedName("pekerjaan") val pekerjaan: String?,
    @SerializedName("penghasilan") val penghasilan: String?,
    @SerializedName("no_hp_ortu") val no_hp_ortu: String?,
    @SerializedName("nilai_ijazah") val nilai_ijazah: String?,
    @SerializedName("nilai_transkip") val nilai_transkip: String?,
    @SerializedName("foto") val foto: String?,
)
