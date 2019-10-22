package controller

import java.io.*

class arquivo {

    fun arquivo () :BufferedReader{
        val bufferedReader = File("entrada2.dat").bufferedReader()
        return bufferedReader
        /*val lineList = mutableListOf<String>()

        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach { println("> " + it)}*/
    }

    //Função para gravar no arquivo de saída
    fun gravaArquivo() :BufferedWriter{
        val bufferedWriter = File("saida.dat").bufferedWriter()
        return bufferedWriter
    }
}