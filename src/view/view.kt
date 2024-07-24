import models.cadCad
import models.sair

fun linha(titulo: String = ""){
    //Cria uma linha tracejada no local em que for chamado.

    val linha = 50
    if (titulo == "")
        println("-".repeat(linha))
    else {
        println("-".repeat(linha))

        val espaco = (linha - titulo.length)/2
        val textoEspaco = "-".repeat(espaco)
        println(textoEspaco + titulo + textoEspaco)

        println("-".repeat(linha))

    }
}

fun menu(opc : Int?){
    while (true){
        when (opc){
            1 -> cadastroSequencial()
            2 -> {
                print("Pesquisa de Alunos")
            }
            3 -> {
                print("Encerrando programa.\nAté logo!")
            }
        }
        break
    }
}

fun cadastroSequencial(){
    linha("Médias Escolares")
    var numAlunos = ""

    while(true){
        linha("Quantos alunos serão cadastrados?")
        print("--> ")
        numAlunos = readln()
        if (verNumero(numAlunos)) break
    }

    for (i in 1 .. numAlunos.toInt()) {
        val rga = cadCad.size + 1
        var aluno = ""
        var idade = ""
        var serie = ""

        while(true) {
            println("Digite o nome do Aluno: ")
            print("--> ")

            aluno = readln()
            if (verCampo(aluno)) break
            linha()
        }

        while(true){
            println("Digite a idade do Aluno: ")
            print("--> ")
            idade = readln()
            if (verNumero(idade)) break
            linha()
        }

        while(true) {
            println("Em que série esse aluno está?\nEX.: 1ºA")
            print("--> ")
            serie = readln().uppercase()
            if (verCampo(serie)) break
        }

        val cadMat = mutableListOf<Materias>()
        var addNota = 0f
        var numMat = ""

        while(true) {
            linha("Quantas Matérias serão lançadas?")
            print("--> ")
            numMat = readln()
            if (verNumero(numMat)) break
        }

        linha("Cadastro de Matérias")

        //laço lançamento de matérias
        for (cont in 1..numMat.toInt()) {
            var novaMat = ""
            while(true) {
                println("Qual o nome da  ${cadMat.size + 1}ª Matéria?")
                print("--> ")

                novaMat = readln().uppercase()
                if (verCampo(novaMat)) break
            }

            var totNota: Float = 0f
            val cadNotas = mutableListOf<Float>()
            var numNot = ""

            while(true) {
                linha("Quantas notas serão lançadas?")
                print("--> ")
                numNot = readln()
                if (verNumero(numNot)) break
            }

            linha("Lançamento de Notas")
            //laço de lançamento de notas
            for (cont in 1..numNot.toInt()) {
                var addNota = ""
                while(true) {
                    println("Digite a ${cadNotas.size + 1}ª Nota de $novaMat!")
                    print("--> ")
                    addNota = readln()
                    if (verNumero(addNota, "f")) break
                }
                totNota += addNota.toFloat()
                cadNotas.add(addNota.toFloat())

                linha()
            }
            val totMedia: Float = totNota / numNot.toInt()
            //val notas = Notas(cadNotas, totMedia)
            val notas = Notas(cadNotas, totMedia, situacao(totMedia))

            cadMat.add(Materias(novaMat, mutableListOf(notas)))
        }
        val cadAluno = Alunos(rga, aluno, idade.toInt(), serie, cadMat)

        cadCad.add(cadAluno)
    }
    linha("Cadastro Completo")
    cadCad.forEach {println(it)}
}

fun situacao(media: Float): String {
    if (media <= 5) {
        return "REPROVADO"
    } else {
        if (media < 8) {
            return "RECUPERACAO"
        } else return "APROVADO"
    }
}

//verfica se o número digitado é inteiro ou ponto flutuante vvv
fun verNumero(valor : String, tipo : String = "i"): Boolean {

    if (valor == ""){
        println("O campo não pode estar vazio!")
        return false
    }

    when(tipo) {
        "i" -> {
            if (valor.all { it.isDigit() }) {
                if (valor.toInt() == 0) {
                    println(
                        "O VALOR digitado não pode ser 0.\n" +
                                "Digite Novamente!"
                    )
                    return false
                } else {
                    return true
                }
            } else {
                println(
                    "Opção errada. Digite apenas números inteiros!\n" +
                            "Ex.: 1,2,3...9"
                )
                return false
            }
        }

        "f" -> {
            if (valor == "."){
                println("Valor digitado inválido!")
                return false
            }

            if (valor.all { it.isDigit() || it == '.' }) {
                return true
            } else {
                println("Digite apenas números!")
                return false
            }
        }
        else -> {
            println("Tipo desconhecido!")
            return false
        }
    }
}

fun verCampo(valor : String): Boolean{
    if (valor == ""){
        println("O camo não pode estar vazio!")
        return false
    } else
        return true
}
fun menu(opc : Int){
    when (opc){
        1 -> cadastroSequencial()
        2 -> {
            while(true) {
                linha("Pesquisa de Alunos")
                println("Digite o Nº de REG. DO ALUNO (RGA).")
                print("--> ")
                val rga = readln()
                if (verNumero(rga)) {
                    val encontrou = pesqAlunos(rga.toInt())
                    if (encontrou != null){
                        println(encontrou)
                    }
                    break
                }
            }
        }
        3 -> {
            print("Encerrando programa.\nAté logo!")
            sair = true
        }
        else -> println("Opção Errada.\nDigite novamente!")
    }
}

fun pesqAlunos(rga : Int):Alunos? {
    // anterior a váriavel com a lista dos alunos era passada para uma variável interna, agora ela utiliza uma variável GLOBAL
    val encontrou = cadCad.find { it.rga == rga}
    if (encontrou == null) {
        println("$rga não encontrado")
        return null
    }else {
        return encontrou
    }
}