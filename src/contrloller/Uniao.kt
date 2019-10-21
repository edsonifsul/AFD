package controller

import model.Afd
import model.Afnl
import model.Transicoes

class Uniao {
    fun uniao(afd1: Afd, afd2: Afd): Afnl{
        val afnl: Afnl
        val inicais = mutableListOf<String>()
        val finais = mutableListOf<String>()
        val alfabetos = mutableListOf<String>()
        val estados = mutableListOf<String>()
        var trasicoes = mutableListOf<Transicoes>()

        inicais.add("qi")

        afd1.fim.forEach {
            finais.add(it)
        }
        afd2.fim.forEach {
            finais.add(it)
        }

        afd1.alfabeto.forEach {
            alfabetos.add(it)
        }
        afd2.alfabeto.forEach {
            if (!alfabetos.contains(it)) alfabetos.add(it)
        }
        alfabetos.add("lambda")
        alfabetos.reverse()

        afd1.estados.forEach {
            estados.add(it)
        }
        afd2.estados.forEach {
            estados.add(it)
        }
        estados.add("qi")
        estados.reverse()

        afd1.transicoes.forEach {
            val aux = Transicoes()
            aux.inicial = it.inicial
            aux.entrada = it.entrada
            aux.final = it.final
            trasicoes.add(aux)
        }
        afd2.transicoes.forEach {
            val aux = Transicoes()
            aux.inicial = it.inicial
            aux.entrada = it.entrada
            aux.final = it.final
            trasicoes.add(aux)
        }

        val aux = Transicoes()
        aux.inicial = "qi"
        aux.entrada = "lambda"
        aux.final = afd1.inicio
        trasicoes.add(aux)
        val aux1 = Transicoes()
        aux1.inicial = "qi"
        aux1.entrada = "lambda"
        aux1.final = afd2.inicio
        trasicoes.add(aux1)

        afd1.fim.forEach {
            if (it.contains(afd1.inicio))
                if (!finais.contains("*qi"))
                    finais.add("*qi")
        }
        afd2.fim.forEach {
            if (it.contains(afd2.inicio))
                if (!finais.contains("qi"))
                    finais.add("qi")
        }
        /*
        trasicoes.forEach {
            finais.forEach { f ->
                //if (f.contains(it.inicial))
                    //it.inicial = "*${it.inicial}"
                if (f.contains(it.final))
                    it.final = "*${it.final}"
            }
        }

         */
        afnl = Afnl(inicais[0], finais, alfabetos, estados, trasicoes)
        return afnl
    }
}