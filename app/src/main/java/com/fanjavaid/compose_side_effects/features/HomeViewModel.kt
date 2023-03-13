package com.fanjavaid.compose_side_effects.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

data class ApiResult(
    val data: List<String>,
    val error: Boolean,
)

class HomeViewModel : ViewModel() {

    private var _travelBucketList = MutableStateFlow(ApiResult(emptyList(), false))
    val travelBucketList: StateFlow<ApiResult> = _travelBucketList.asStateFlow()

    fun getTravelBucketList(keyword: String? = null) = viewModelScope.launch {
        println("getTravelBucketList from API - $keyword")
        flow {
            delay(1_000)
            if (keyword != null) {
                emit(dummyDataFromApi().filter { it.contains(keyword, true) })
            } else {
                emit(dummyDataFromApi())
            }
        }.flowOn(Dispatchers.IO).collect {
            _travelBucketList.value = ApiResult(it, false)
        }
    }

    private fun dummyDataFromApi() = listOf(
        "Pink Beach, Indonesia",
        "Grand Prismatic Spring, Yellowstone National Park, Usa",
        "Great Wall Of China",
        "Cappadocia, Turkey",
        "To Sua Ocean Trench, Samoa",
        "Rainbow Mountains, China",
        "Amazon Rainforest, South America",
        "Hermitage Museum, Russia",
        "Abu Simbel, Egypt",
        "Chernobyl, Ukraine",
        "Gyeongbokgung Palace, Seoul",
        "Grand Canyon, Arizona, Usa",
        "Batu Caves, Kuala Lumpur, Malaysia",
        "Leaning Tower Of Pisa, Italy",
        "Taj Mahal, India",
        "Baobabs Alley, Madagascar",
        "Iguazu Falls, South America",
        "Giant’S Causeway, Ireland",
        "Rapa Nui (Easter Island), Chilean Polynesia",
        "Benagil Cave, Portugal",
        "Niagara Falls, Usa/Canada",
        "Arashiyama Bamboo Forest, Japan",
        "Hobbiton, New Zealand",
        "Sydney Opera House, Australia ",
        "Neuschwanstein Castle, Germany",
        "Colosseum, Rome, Italy",
        "Statue Of Liberty, New York City, Usa",
        "Reynisfjara Beach, Iceland",
        "Bastei Bridge, Germany",
        "Victoria Falls, Africa",
        "Ait Benhaddou, Morocco",
        "Danakil Depression, Ethiopia",
        "Salar De Uyuni, Bolivia",
        "The White Desert, Egypt",
        "Christ The Redeemer, Rio De Janeiro",
        "The Lourve, Paris, France",
        "Registan Square, Uzbekistan",
        "Golden Bridge, Vietnam",
        "Buckingham Palace, London, England",
        "Reichstag Dome, Berlin, Germany",
        "Guggenheim Bilbao, Spain",
        "London Eye, London, England",
        "Acropolis, Athens, Greece",
        "Gardens By The Bay, Singapore",
        "Disneyland, California, Usa",
        "Abba Museum, Stockholm, Sweden",
        "Pamukkale, Turkey",
        "Great Blue Hole, Belize",
        "Karijini National Park, Australia",
        "Mona, Tasmania, Australia",
        "Grand Canal, Venice, Italy",
        "Uluru, Northern Territory, Australia",
        "The Louvre, Abu Dhabi",
        "Naoshima Island, Japan",
        "Bellagio Fountain, Las Vegas, Usa",
        "Banzai Pipeline, Hawaii",
        "The Blue Mosque, Istanbul, Turkey",
        "Matterhorn, Switzerland",
        "Golden Gate Bridge, San Francisco, Usa",
        "Burj Khalifa, Dubai",
        "Borobudar Temple, Indonesia",
        "Hollywood Sign, Los Angeles, Usa",
        "Red Square, Moscow, Russia",
        "Mt Fuji, Japan",
        "Ha Long Bay, Vietnam",
        "Mount Rushmore, Usa",
        "Milford Sound, New Zealand",
        "Machu Picchu, Peru",
        "Sheikh Zayed Grand Mosque, Abu Dhabi",
        "Moraine Lake, Canada",
        "Kata Tjuta, Northern Territory, Australia",
        "Eiffel Tower, Paris, France",
        "Angkor Temples, Cambodia",
        "Central Park, New York City, Usa",
        "Berlin Wall, Germany",
        "Ik-Kil Cenote, Mexico",
        "St Peters Basilica, Vatican City, Italy",
        "Sistine Chapel, Vatican City, Italy ",
        "Tai Kwun, Hong Kong",
        "Terracotta Army, China",
        "Sagrada Familia, Spain",
        "Pyramids Of Giza, Egypt",
        "Great Barrier Reef, Queensland, Australia",
        "Tianjin Binhai Library, China",
        "Heart Of Voh, New Caledonia",
        "Times Square, New York City, Usa",
        "Forbidden City, Beijing, China",
        "Darvaza Gas Crater, Turkmenistan",
        "Pompeii, Italy",
        "Grey Glacier, Patagonia",
        "Seljalandsfoss Waterfall, Iceland",
        "Petra, Jordan",
        "Alcatraz, San Francisco Bay, Usa",
        "Cherry Blossoms, Japan",
        "Freedom Tower, New York City, Usa",
        "Serengeti National Park, Tanzania",
        "Chefchaouen, Morocco",
        "Stonehenge, England",
        "Northern Lights, Norway",
        "Havana, Cuba",
    )
}
