package com.fanjavaid.compose_side_effects.features

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.util.Date

class DetailsViewModel : ViewModel() {

    suspend fun getDetails(id: Int): Result<Detail> {
        delay(750)

        if (id == 0) {
            return Result.Error(IllegalArgumentException("Invalid ID: $id"))
        }

        return Result.Success(
            Detail(
                title = "Bali, Indonesia",
                description = "Bali (juga dikenal sebagai Kepulauan Bali, Bali: ᬩᬮᬶ) adalah " +
                        "sebuah wilayah provinsi yang terletak di Indonesia. Ibu kotanya adalah Denpasar. " +
                        "Provinsi Bali terletak di bagian barat Kepulauan Nusa Tenggara." +
                        "\n\nDi awal kemerdekaan Indonesia, pulau ini termasuk dalam " +
                        "Provinsi Sunda Kecil yang beribu kota di Singaraja, dan kini terbagi " +
                        "menjadi 3 provinsi, yakni Bali, Nusa Tenggara Barat, dan Nusa Tenggara Timur.",
                timestamp = Date().time,
                othersInformation = "Pulau Bali adalah bagian dari Kepulauan Sunda Kecil" +
                        "sepanjang 153 km dan selebar 112 km sekitar 3,2 km dari Pulau Jawa"
            )
        )
    }
}

data class Detail(
    val title: String,
    val description: String,
    val timestamp: Long,
    val othersInformation: String,
)

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}