package controller

import model.Afd
import model.Afnl
import model.Transicoes

class Combinacao {
    fun combinacao(afd1: Afd): Afnl {
        val afnl: Afnl
        var inicais: String
        val finais = mutableListOf<String>()
        val alfabetos = mutableListOf<String>()
        val estados = mutableListOf<String>()
        val trasicoes = mutableListOf<Transicoes>()


        inicais = "qi"

        afd1.fim.forEach {
            val final = it.replace("*","")
            if (!finais.contains(final)) finais.add(final)
            val aux = Transicoes()
            val inicial: String
            if (it.contains('*')){
                inicial= it.replace("*","")
                aux.inicial=inicial
            }else aux.inicial=it
            aux.entrada="lambda"
            aux.final=afd1.inicio
            if (aux.inicial != aux.final) trasicoes.add(aux)
        }

        afd1.alfabeto.forEach {
            alfabetos.add(it)
        }
        alfabetos.add("lambda")

        afd1.estados.forEach {
            estados.add(it)
        }
        estados.add("qi")

        afd1.transicoes.forEach {
            trasicoes.add(it)
        }

        val aux = Transicoes()
        aux.inicial="qi"
        aux.entrada="lambda"
        aux.final=afd1.inicio
        trasicoes.add(aux)

        finais.add("qi")

        afnl = Afnl(inicais, finais, alfabetos, estados, trasicoes)
        return afnl
    }
}