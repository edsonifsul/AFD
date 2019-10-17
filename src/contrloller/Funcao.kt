package controller

import model.Afd
import model.Afnl

class Funcao {


    fun printarAfnl(afd: Afnl, string: String){
        //recebe o afnlambda e printa na tela
        var i: Int
        println("$string: ")
        print("Estados: ")
        afd?.estados.forEach { print(" $it") }
        println()
        println("Estado(s) Inicial(is): ${afd?.inicio}")
        print("Estado(s) Final(is):")
        afd?.fim.forEach { print(" $it") }
        println()
        print("Alfabeto:")
        afd?.alfabeto.forEach { print(" $it") }
        println()
        println("Transições: ")
        i=0
        afd?.transicoes.forEach {
            print("${it.inicial}")
            print(" ${it.entrada} ")
            println(it.final)
        }
    }

}