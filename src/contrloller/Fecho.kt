package controller

import model.Afd
import model.Afnl
import model.FechoModel
import model.Transicoes

class Fecho {
    val tabelaTransicoes = mutableListOf<Transicoes>()
    val fecho = mutableListOf<FechoModel>()
    fun fechoLambda(afd: Afnl) {

        var aux = FechoModel()

        afd.transicoes.forEach {
            if (it.entrada == "lambda")
                if (fecho.isEmpty()){
                    aux.estado = it.inicial
                    aux.entrada = it.entrada
                    aux.fecho = it.final
                    fecho.add(aux)
                }else{
                    fecho.forEach { f ->
                        if (f.estado == it.inicial)
                            f.fecho = f.fecho + it.final
                        else{
                            aux.estado = it.inicial
                            aux.entrada = it.entrada
                            aux.fecho = it.final
                            fecho.add(aux)
                        }
                    }
                }
        }
        println()
        fecho.forEach {
            print(it.estado)
            print(" ${it.entrada} ")
            println(it.fecho)
        }
    }

    fun afnd(afnl: Afnl, fecho: List<FechoModel>){
        val afd: Afd
        var aux = Transicoes()
        var cont = -1
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