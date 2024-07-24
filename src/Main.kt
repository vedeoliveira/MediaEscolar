package models

import Alunos
// também posso colocar um alias como import Alunos as alu

import menu
import linha
import verNumero

val cadCad = mutableListOf<Alunos>()//Variável  GLOBAL
var sair = false

fun main() {
    while (true) {
        if (sair) break
        linha("Médias e notas de Alunos")
        println(
            "Escolha uma opção!\n" +
                    "[1]-Cadastro Sequencial\n" +
                    "[2]-Pesquisa de Alunos\n" +
                    "[3]-Sair"
        )
        print("--> ")
        val opc = readln()
        if (verNumero(opc)) {
            menu(opc.toInt())
        }
    }
}