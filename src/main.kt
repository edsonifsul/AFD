import controller.*
import model.Afd
import model.Afnl
import model.FechoModel
import model.Transicoes

fun main(){

    //cria uma variável para receber o retorno da abertura do aquivo
    val leitor = arquivo()

    //cria uma variável para receber as linhas lidas uma a uma no arquivo
    //limpa a lista para garantir que não haja lixo de memória
    val lineList = mutableListOf<String>()
    lineList.clear()

    //le o arquivo linha a linha armazenando a string na variável e depois
    //fecha o arquivo
    leitor.arquivo().useLines { lines -> lines.forEach { lineList.add(it) } }
    leitor.arquivo().close()

    //inicio da criação dos afds
    val afd1: Afd
    val afd2: Afd

    //criação e inicialização dos afnlambdas como null
    var afnl_uniao: Afnl
    var afnl_interceccao: Afnl
    var afnl_concatenacao: Afnl
    var afnl_combinacao: Afnl

    val operacao: Int = lineList[0].toInt()
    var inicio: String
    var fim: List<String>
    var alfabeto: List<String>
    var estados: List<String>
    var transicao = mutableListOf<Transicoes>()
    var auxiliar: List<String>

    var i: Int
    i=3
    //println(lineList)

    val fluxo = Funcao()
    when (operacao){
        4 -> {
            val combinacao = Combinacao()
            val fecho = Fecho()
            val f: List<FechoModel>
            estados = lineList[1].split(',')
            alfabeto = lineList[2].split(',')
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao.add(aux)
                i++
            }
            auxiliar = lineList[i].split(",")
            inicio = auxiliar[0].replace(">","")
            i++
            fim = lineList[i].split(",")
            fim.forEach {
                it.replace("*", "")
            }
            afd1 = Afd(inicio,fim,alfabeto,estados,transicao)
            afnl_combinacao = combinacao.combinacao(afd1)

            fluxo.printarAfnl(afnl_combinacao, "Combinação")
            fecho.fechoLambda(afnl_combinacao)

        }
        1 -> {
            val uniao = Uniao()
            val fecho = Fecho()
            val f: List<FechoModel>

            estados = lineList[1].split(',')
            alfabeto = lineList[2].split(',')
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao.add(aux)
                i++
            }
            auxiliar = lineList[i].split(",")
            inicio = auxiliar[0].replace(">","")
            i++
            fim = lineList[i].split(",")
            fim[0].replace("*","")
            afd1 = Afd(inicio,fim,alfabeto,estados,transicao)
            i+=2
            estados = lineList[i].split(',')
            i++
            alfabeto = lineList[i].split(',')
            i++
            var transicao2 = mutableListOf<Transicoes>()
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao2.add(aux)
                i++
            }
            auxiliar = lineList[i].split(">")
            inicio = auxiliar[1]
            i++
            fim = lineList[i].split(",")
            afd2 = Afd(inicio, fim, alfabeto, estados, transicao2)

            afnl_uniao = uniao.uniao(afd1,afd2)
            fluxo.printarAfnl(afnl_uniao, "União")
            fecho.fechoLambda(afnl_uniao)
        }
        2 -> {
            val intececcao = Interceccao()
            val fecho = Fecho()

            estados = lineList[1].split(',')
            alfabeto = lineList[2].split(',')
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao.add(aux)
                i++
            }
            auxiliar = lineList[i].split(",")
            inicio = auxiliar[0].replace(">","")
            i++
            fim = lineList[i].split(",")
            fim[0].replace("*","")
            afd1 = Afd(inicio,fim,alfabeto,estados,transicao)
            i+=2
            estados = lineList[i].split(',')
            i++
            alfabeto = lineList[i].split(',')
            i++
            var transicao2 = mutableListOf<Transicoes>()
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao2.add(aux)
                i++
            }
            auxiliar = lineList[i].split(">")
            inicio = auxiliar[1]
            i++
            fim = lineList[i].split(",")
            afd2 = Afd(inicio, fim, alfabeto, estados, transicao2)

            afnl_interceccao = intececcao.intececcao(afd1, afd2)
            fluxo.printarAfnl(afnl_interceccao, "Intercecção")
            println()
            fecho.fechoLambda(afnl_interceccao)
        }
        3 -> {
            val concatenacao = Concatenacao()
            val fecho = Fecho()

            estados = lineList[1].split(',')
            alfabeto = lineList[2].split(',')
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao.add(aux)
                i++
            }
            auxiliar = lineList[i].split(",")
            inicio = auxiliar[0].replace(">","")
            i++
            fim = lineList[i].split(",")
            fim[0].replace("*","")
            afd1 = Afd(inicio,fim,alfabeto,estados,transicao)
            i+=2
            estados = lineList[i].split(',')
            i++
            alfabeto = lineList[i].split(',')
            i++
            var transicao2 = mutableListOf<Transicoes>()
            while (lineList[i].contains(">")!=true){
                auxiliar = lineList[i].split(",")
                val aux = Transicoes()
                aux.inicial=auxiliar[0]
                aux.entrada=auxiliar[1]
                aux.final=auxiliar[2]
                transicao2.add(aux)
                i++
            }
            auxiliar = lineList[i].split(">")
            inicio = auxiliar[1]
            i++
            fim = lineList[i].split(",")
            afd2 = Afd(inicio, fim, alfabeto, estados, transicao2)
            afnl_concatenacao = concatenacao.concatencacao(afd1, afd2)
            fluxo.printarAfnl(afnl_concatenacao, "Concatenação")
            println()
            fecho.fechoLambda(afnl_concatenacao)
        }
    }

    lineList.clear()
}