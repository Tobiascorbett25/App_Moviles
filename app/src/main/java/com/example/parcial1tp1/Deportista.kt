package com.example.parcial1tp13

data class Deportista(
    val id: Int,
    val nombre: String,
    val deporte: Deporte,
    val pais: Pais,
    val enActividad: Boolean
)

enum class Deporte {
    FUTBOL,
    BASQUETBOL,
    TENIS,
    HOCKEY,
    ATLETISMO,
    BMX,
    CICLISMO,
    JUDO,
    VOLEIBOL,
    MODELO,
    CARRERAS
}

enum class Pais {
    ARGENTINA,
    URUGUAY,
    COLOMBIA,
    CHILE,
    BRASIL
}
