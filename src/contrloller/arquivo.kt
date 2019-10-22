package controller

import java.io.*

class arquivo {

    fun arquivo () :BufferedReader{
        val bufferedReader = File("entrada3.dat").bufferedReader()
        return bufferedReader
        /*val lineList = mutableListOf<String>()

        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach { println("> " + it)}*/
    }

    fun gravaArquivo() :BufferedWriter{
        val bufferedWriter = File("saida.dat").bufferedWriter()
        return bufferedWriter
    }
}