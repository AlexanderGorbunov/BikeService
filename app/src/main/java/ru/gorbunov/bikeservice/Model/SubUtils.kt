package ru.gorbunov.bikeservice.Model

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.experimental.and


object SubUtils {
    fun toHex(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (i in bytes.indices.reversed()) {
            val b: Byte = bytes[i] and 0xff.toByte()
            if (b < 0x10) sb.append('0')
            sb.append(Integer.toHexString(b.toInt()))
            if (i > 0) {
                sb.append(" ")
            }
        }
        return sb.toString()
    }

    fun normalizeNFCCode(original_code : String) : String
    {
        val s = split(original_code)
        val s2 = getDub(s)
        return getBikeCode(s2)
    }

    fun split(str: String?): List<String> {
        val pattern: Pattern = Pattern.compile("(\\w+)|(\\.{3})|[^\\s]")
        val matcher: Matcher = pattern.matcher(str)
        val list: MutableList<String> = ArrayList()
        while (matcher.find()) {
            list.add(matcher.group())
        }
        return list
    }

    fun getDub(list : List<String>) : ArrayList<String>
    {
        var dubls = arrayListOf<String>()
        for (string in list) {
            if (Collections.frequency(list, string) > 1 && !dubls.contains(string)) {
                dubls.add(string)
                //list.removeAt(list.indexOf(strings))
            }
        }
        return dubls
    }

    fun getBikeCode(dubls: List<String>) : String
    {
        for (string in dubls) {
            if (string.length > 1)
                return string
        }
        return ""
    }

    /*fun toReversedHex(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (i in bytes.indices) {
            if (i > 0) {
                sb.append(" ")
            }
            val b: Byte = bytes[i] and 0xff.toByte()
            if (b < 0x10) sb.append('0')
            sb.append(Integer.toHexString(b))
        }
        return sb.toString()
    }

    fun toDec(bytes: ByteArray): Long {
        var result: Long = 0
        var factor: Long = 1
        for (i in bytes.indices) {
            val value: Byte = bytes[i] and 0xffL.toByte()
            result += value * factor
            factor *= 256L
        }
        return result
    }

    fun toReversedDec(bytes: ByteArray): Long {
        var result: Long = 0
        var factor: Long = 1
        for (i in bytes.indices.reversed()) {
            val value: Byte = bytes[i] and 0xffL.toByte()
            result += value * factor
            factor *= 256L
        }
        return result
    }*/
}