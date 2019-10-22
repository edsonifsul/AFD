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

    fun conversao(afnl: Afnl, fecho: FechoModel){

        var t = Transicoes()
        val transicoes = mutableListOf<Transicoes>()
        val alphabeto = mutableListOf<String>()
        t.inicial = fecho.fecho
        t.entrada = afnl.alfabeto[0]
        t.final = ""
        afnl.transicoes.forEach {
            if (it.entrada != "lambda")
                transicoes.add(it)
        }
        afnl.alfabeto.forEach {
            alphabeto.add(it)
        }
        alphabeto.remove("lambda")
        var controle = false
        var cont = 0

        tabelaTransicoes.clear()
        while (controle == false){
            val tbaux = mutableListOf<Transicoes>()
            if (tabelaTransicoes.isEmpty()) tbaux.add(t)
            else{
                tabelaTransicoes.forEach {
                    tbaux.add(it)
                }
            }

            tbaux.forEach {
                alphabeto.forEach { alpha ->
                    transicoes.forEach { item ->
                        if (it.inicial.contains(item.inicial)) {
                            if (item.entrada == alpha) {
                                if (!it.final.contains(item.final)) {
                                    it.entrada = alpha
                                    it.final = it.final + item.final
                                    if (!tabelaTransicoes.contains(it)) tabelaTransicoes.add(it)
                                }
                            }else{
                                if (!it.final.contains(item.final)){
                                    val aux = Transicoes()
                                    aux.inicial = it.inicial
                                    aux.entrada = alpha
                                    aux.final = it.final + item.final
                                    if (!tabelaTransicoes.contains(aux)) tabelaTransicoes.add(aux)
                                }
                            }
                        }
                    }
                }
                var cont = 0
                tabelaTransicoes.forEach { item ->
                    if (it.final.equals(item.inicial))
                        cont++
                }

                if (cont == 0) {
                    val aux = Transicoes()
                    aux.inicial = it.final
                    tabelaTransicoes.add(aux)
                }
            }
            cont++
            if (cont > 100) controle = true
        }
        val finais = mutableListOf<String>()
        val estados = mutableListOf<String>()
        cont = 0
        println()
        tabelaTransicoes.forEach {
            if (!estados.contains(it.inicial)) estados.add(it.inicial)
        }
        //criação da lista
        afnl.fim.forEach {
            estados.forEach { item ->
                if (item.contains(it))
                    if (!finais.contains(item)) finais.add(item)
            }
        }

        println("Estados Renomeados:")
        estados.forEach {
            print("$it: ")
            println("p$cont")
            cont++
        }
        val inicio = tabelaTransicoes[0].inicial


        println()
        println("AFD resultante da Conversão:")
        print("Estados: ")
        estados.forEach {
            print("$it ")
        }
        println()
        print("Alfabeto: ")
        alphabeto.forEach {
            print("$it ")
        }
        println()
        println("Transições:")
        tabelaTransicoes.forEach {
            print(it.inicial)
            print(" ${it.entrada} ")
            println(it.final)
        }
        println("Inicial: $inicio")
        print("Finais: ")
        finais.forEach {
            print("$it ")
        }

        val afd: Afd
        afd = Afd(inicio, finais, alphabeto, estados, tabelaTransicoes )
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