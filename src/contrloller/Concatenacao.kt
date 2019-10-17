package controller

import model.Afd
import model.Afnl
import model.Transicoes

class Concatenacao {
    fun concatencacao(afd1: Afd, afd2: Afd): Afnl {
        val afnl: Afnl
        var inicais: String
        val finais = mutableListOf<String>()
        val alfabetos = mutableListOf<String>()
        val estados = mutableListOf<String>()
        val trasicoes = mutableListOf<Transicoes>()
        var aux = Transicoes()

        inicais = afd1.inicio

        afd1.fim.forEach {
            val inicio: String
            if (it.contains('*')){
                inicio=it.replace("*","")
                aux.inicial=inicio
            } else aux.inicial=it
            aux.entrada="lambda"
            aux.final=afd2.inicio
            trasicoes.add(aux)
        }
        afd2.fim.forEach {
            if (it.contains('*')){
                var s = it.split("*")
                finais.add(s[1])
            }
            else finais.add(it)
        }

        afd1.alfabeto.forEach {
            alfabetos.add(it)
        }
        afd2.alfabeto.forEach {
            if (!alfabetos.contains(it)) alfabetos.add(it)
        }
        alfabetos.add("lambda")

        afd1.estados.forEach {
            estados.add(it)
        }
        afd2.estados.forEach {
            estados.add(it)
        }

        afd1.transicoes.forEach {
            trasicoes.add(it)
        }
        afd2.transicoes.forEach {
            trasicoes.add(it)
        }

        afnl = Afnl(inicais, finais, alfabetos, estados, trasicoes)
        return afnl
    }
}