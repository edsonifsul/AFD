package controller

import model.Afd
import model.Afnl
import model.FechoModel
import model.Transicoes

class Fecho {
    val tabelaTransicoes = mutableListOf<Transicoes>()
    val fecho = FechoModel()



    fun fechoLambda(afnl: Afnl, estado: String): FechoModel {

        var aux = FechoModel()
        afnl.transicoes.forEach {
            if (it.inicial == estado) {
                if (it.entrada == "lambda"){
                    if (!aux.estado.contains(it.inicial))aux.estado = it.inicial
                    if (!aux.entrada.contains(it.entrada))aux.entrada = it.entrada
                    if (!aux.fecho.contains(aux.estado)) aux.fecho = aux.estado
                    if (!aux.fecho.contains(it.final)) aux.fecho = aux.fecho + it.final
                }
            }
        }
        /*
        println()
        print(aux.estado)
        print(" ${aux.entrada} ")
        println(aux.fecho)
         */
        return aux
    }

    fun afnd(afnl: Afnl, fecho: List<FechoModel>){
        val afd: Afd
        var aux = Transicoes()
        val transicoes = mutableListOf<Transicoes>()
        afnl.transicoes.forEach {
            transicoes.add(it)
        }

        aux.inicial = transicoes[0].inicial
        aux.entrada = transicoes[0].entrada
        aux.final = transicoes[0].final
        tabelaTransicoes.add(aux)
        var controle = false
        while (controle == false){
            tabelaTransicoes.forEach {
                transicoes.forEach { transicao ->
                    if (it.inicial.contains(transicao.inicial))
                        if (it.entrada.contains(transicao.entrada))
                            if (!it.final.contains(transicao.final))
                                it.final = it.final + transicao.final
                        else{
                                aux.inicial = transicao.inicial
                                aux.entrada = transicao.entrada
                                aux.final = transicao.final
                                tabelaTransicoes.add(aux)
                            }
                    else{
                            aux.inicial = transicao.inicial
                            aux.entrada = transicao.entrada
                            aux.final = transicao.final
                            tabelaTransicoes.add(aux)
                        }

                }
            }
        }

        //return afd
    }
}
/*
if (aux.entrada == "lambda"){
                if (it.entrada == "lambda")
                    aux.final = aux.final + it.final
                tabelaTransicoes.add(aux)
                aux.entrada = ""
            }else if (aux.entrada == ""){
                if (it.entrada == "lambda"){
                    aux.inicial = it.inicial
                    aux.entrada = it.entrada
                    aux.final = it.final
                }else{
                    aux.final = aux.final + it.final
                    aux.entrada = "*"
                }
            }else{
                if (it.entrada == "lambda"){

                }
            }
 */