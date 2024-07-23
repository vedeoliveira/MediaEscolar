
data class Notas(val notas: MutableList<Float>, var media: Float?){
    override fun toString(): String {
        val notasString = ("| NOTAS | "+notas.joinToString(" | "){"%.2f".format(it)})
        return "$notasString | MÉDIA | %.2f | ".format(media)
    }
}

data class Materias(var materia: String?, val notas: MutableList<Notas>?){
    override fun toString(): String {
        val notasString = notas?.joinToString("\n") ?: ""
        return "$materia\n$notasString"
    }
}

data class Alunos(var rga : Int?, var nome: String?, var idade: Int?, var serie: String?, val materias: MutableList<Materias>?){
    override fun toString(): String {
        val materiasString = materias?.joinToString("\n") ?: ""
        return "| RGA: $rga | Aluno : $nome | Série : $serie |\n| Materias: |\n$materiasString\n".uppercase() + "-".repeat(50)


    }
}

val cadCad = mutableListOf<Alunos>()//Variável  GLOBAL

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
                print("Pesquida de Alunos")
            }
            3 -> {
                print("Encerrando programa.\nAté logo!")
            }
        }
        break
    }
}

var sair = false

fun cadastroSequencial(){
    linha("Médias Escolares")

    linha("Quantos alunos serão cadastrados?")
    print("--> ")

    val numAlunos = readln().toInt()
    //val cadCad = mutableListOf<Alunos>()

    for (i in 1 .. numAlunos) {
        val rga = cadCad.size + 1

        println("Digite o nome do Aluno: ")
        print("--> ")

        val aluno = readlnOrNull()
        linha()

        println("Digite a idade do Aluno: ")
        print("--> ")
        val idade: Int = readln().toInt()
        linha()

        println("Em que série esse aluno está?\nEX.: 1ºA")
        print("--> ")
        val serie = readln().uppercase()

        val cadMat = mutableListOf<Materias>()

        var addNota = 0f

        linha("Quantas Matérias serão lançadas?")
        print("--> ")
        val numMat: Int = readln().toInt()

        linha("Cadastro de Matérias")

        //laço lançamento de matérias
        for (cont in 1..numMat) {
            println("Qual o nome da  ${cadMat.size + 1}ª Matéria?")
            print("--> ")

            val novaMat = readln().uppercase()
            var totNota: Float = 0f
            val cadNotas = mutableListOf<Float>()

            linha("Quantas notas serão lançadas?")
            print("--> ")
            val numNot: Int = readln().toInt()

            linha("Lançamento de Notas")
            //laço de lançamento de notas
            for (cont in 1..numNot) {
                println("Digite a ${cadNotas.size + 1}ª Nota de $novaMat!")
                print("--> ")

                addNota = readln().toFloat()
                totNota += addNota
                cadNotas.add(addNota)

                linha()
            }
            val totMedia = totNota / numNot
            val notas = Notas(cadNotas, totMedia)

            cadMat.add(Materias(novaMat, mutableListOf(notas)))
        }
        val cadAluno = Alunos(rga, aluno, idade, serie, cadMat)

        cadCad.add(cadAluno)
    }
    linha("Cadastro Completo")
    cadCad.forEach {println(it)}
}

//função para validar se a variável esta recebendo um numero ou string retorno true para numero false para string
fun verNumero(valor : String): Boolean {
    //return if (valor in "0123456789") true else false // Primeiro código com a mesma finalidade
    return valor.all {it.isDigit()} //Chat Gpt Corrigido
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
                } else {
                    println("Valor inválido, digite um valor válido!")
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
        } else {
            println("Opção Errada.\nDigite novamente!")
        }
    }
}