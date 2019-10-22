package controller

import model.Afd
import model.Afnl
import model.FechoModel
import model.Transicoes
import java.io.BufferedWriter

class Fecho {
    val tabelaTransicoes = mutableListOf<Transicoes>()
    val fecho = FechoModel()



    fun fechoLambda(afnl: Afnl, estado: String): FechoModel {
        /*
        função para realizar a operação ECLOSE descrita nas páginas
        83 - 86 do livro do Hopcroft.
        Verifica se existem transições lambda e as elimina.
         */
        var aux = FechoModel()
        afnl.transicoes.forEach {
            if (it.inicial == estado) {
                if (it.entrada == "lambda"){
                    if (!aux.estado.contains(it.inicial))aux.estado = it.inicial
                    if (!aux.entrada.contains(it.entrada))aux.entrada = it.entrada
                    if (!aux.fecho.contains(aux.estado)) aux.fecho = aux.estado
                    if (!aux.fecho.contains(it.final)) aux.fecho = aux.fecho + it.final
                }else{
                    val aux2 = FechoModel()
                    aux2.estado = aux.estado
                    aux2.entrada = it.entrada
                    aux2.fecho = aux.fecho + it.final
                    return aux2
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
        /*
        função para realizar a conversão do afnlambda para o afd
        utilizando o conceito de ECLOSE. Esse processo suprime a conversão para
        o afnd passando direto para o afd correspondente
         */
        var t = Transicoes()
        val transicoes = mutableListOf<Transicoes>()
        val alphabeto = mutableListOf<String>()
        t.inicial = fecho.estado
        t.entrada = fecho.entrada
        t.final = fecho.fecho
        afnl.transicoes.forEach {
            if (it.entrada != "lambda")
                transicoes.add(it)
        }
        afnl.alfabeto.forEach {
            if (it != "lambda")
                alphabeto.add(it)
        }

        var controle = false
        var cont = 0
        /* torna vazio a tabelaTrasicoes para garantir
        um critério de parada válido
         */
        tabelaTransicoes.clear()
        while (controle == false){
            /*
            Cria uma lista de Transições para trabalhar com a iteração do
            forEach na lista de transições e inserir as novas transições na lista
             */
            val tbaux = mutableListOf<Transicoes>()
            if (tabelaTransicoes.isEmpty()) tbaux.add(t)
            else{
                tabelaTransicoes.forEach {
                    if (!tbaux.contains(it)) tbaux.add(it)
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
            /*cont++
            if (cont > 100) controle = true
             */
            if (tbaux == tabelaTransicoes) controle = true
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

        /*
        mapa de estados para realizar a criação do arquivo de saída
        com os estados renomeados e popula esse mapa
         */
        val estadosMap = mutableMapOf<String, String>()
        println("Estados Renomeados:")
        estados.forEach {
            estadosMap[it] = "p$cont"
            cont++
        }
        estadosMap.forEach{
            print("${it.key}: ")
            println(it.value)
        }

        /*
        a função fechoLambda desse modelo garante que a primeira transição
        inicial da tabela de transições seja o estado inicial
        do afd resultante da conversão
         */
        val inicio = tabelaTransicoes[0].inicial

        /*
        printa o AFD resultante da conversão no console para controle
        ou para verificação
         */
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

        //Inicio da gravação do arquivo de saída
        val arquivo = arquivo()
        val bufferedWriter: BufferedWriter
        bufferedWriter = arquivo.gravaArquivo()

        /*
        Usando o mapa gerado, insere no arquivo o valor referente ao estado
         */
        var tamanho = estadosMap.size
        estadosMap.forEach{
            if (--tamanho > 0) bufferedWriter.write("${it.value},")
            else bufferedWriter.write(it.value)
        }
        bufferedWriter.newLine()
        tamanho = alphabeto.size
        alphabeto.forEach {
            if (--tamanho > 0) bufferedWriter.write("${it},")
            else bufferedWriter.write(it)
        }
        bufferedWriter.newLine()
        tabelaTransicoes.forEach {
            bufferedWriter.write(estadosMap[it.inicial])
            bufferedWriter.write(",${it.entrada},")
            bufferedWriter.write(estadosMap[it.final])
            bufferedWriter.newLine()
        }
        bufferedWriter.write(">${estadosMap[inicio]}")
        bufferedWriter.newLine()
        bufferedWriter.write("*")
        tamanho = finais.size
        finais.forEach {
            if (--tamanho > 0) bufferedWriter.write("${estadosMap[it]},")
            else bufferedWriter.write("${estadosMap[it]}")
        }
        bufferedWriter.close()
    }
}