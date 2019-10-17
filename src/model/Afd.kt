package model

class Afd {

    var inicio: String
    var fim: List<String>
    var alfabeto: List<String>
    var estados: List<String>
    var transicoes: List<Transicoes>

    constructor(
        inicio: String,
        fim: List<String>,
        alfabeto: List<String>,
        estados: List<String>,
        transicoes: List<Transicoes>){

        this.inicio=inicio
        this.fim=fim
        this.alfabeto=alfabeto
        this.estados=estados
        this.transicoes=transicoes
    }
}