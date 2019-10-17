package controller

import java.io.*

class arquivo {

    fun arquivo () :BufferedReader{
        val bufferedReader = File("entrada.dat").bufferedReader()
        return bufferedReader
        /*val lineList = mutableListOf<String>()

        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach { println("> " + it)}*/
    }
}