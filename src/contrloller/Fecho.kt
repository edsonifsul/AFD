package controller

import model.Afd
import model.Afnl
import model.FechoModel
import model.Transicoes

class Fecho {
    val tabelaTransicoes = mutableListOf<Transicoes>()
    fun fechoLambda(afd: Afnl): List<FechoModel> {
        val fecho = mutableListOf<FechoModel>()
        var aux = FechoModel()

        afd.transicoes.forEach {
            if (it.entrada == "lambda") {
                if (fecho.isEmpty()) {
                    aux.estado = it.inicial
                    aux.entrada = it.entrada
                    aux.fecho = it.final
                    fecho.add(aux)
                } else {
                    for (f in fecho) {
                        if (f.estado == it.inicial) {
                            if (!f.fecho.contains(it.final)) {
                                f.fecho = f.fecho + it.final
                            }
                        } else {
                            aux.estado = it.inicial
                            aux.entrada = it.entrada
                            aux.fecho = it.final
                            fecho.add(aux)
                        }
                    }
                }

            }
        }
        fecho.forEach {
            for (fim in afd.fim){
                if (it.fecho.contains(fim))
                    if (!it.f.contains(it.estado))
                        it.f.add(it.estado)
            }
            /*
            print(it.estado)
            print(" ${it.entrada} ")
            println(it.fecho)
            println(it.f)
             */
        }
        return fecho
    }

    fun afnd(afnl: Afnl, fecho: List<FechoModel>){
        val afd: Afd
        var aux = Transicoes()
        var aux2 = Transicoes()

        afnl.transicoes.forEach {
            if (tabelaTransicoes.isEmpty())
                tabelaTransicoes.add(it)
            else{
                //t é o alias do segundo it do foreach
                tabelaTransicoes.forEach { t ->
                    if (t.inicial == it.inicial)
                        if (t.entrada == it.entrada)
                            if (t.entrada == "lambda")
                                aux = it
                            else
                                t.final = t.final + it.final
                        else
                            tabelaTransicoes.add(it)
                    if (t.entrada == "lambda")
                        tabelaTransicoes.add(it)
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