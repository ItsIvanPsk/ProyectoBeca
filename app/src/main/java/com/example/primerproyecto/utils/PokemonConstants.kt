package com.example.primerproyecto.utils

import java.sql.Timestamp

class PokemonConstants {
    companion object{
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val API_KEY = ""
        const val PRIVATE_KEY = ""
        const val limit = 20
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        fun hash():String{
            return ""
        }
        /*
        fun hash():String{
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(
                1,
                md.digest(input.toByteArray()).toString()).padStart(32, '0')
            )
        }
         */
    }
}