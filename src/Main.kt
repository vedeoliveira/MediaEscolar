
data class Notas(val notas: MutableList<Float>, var media: Float?){
    override fun toString(): String {
        val notasString = ("| NOTAS | "+notas.joinToString(" | "))
        return "$notasString | MÉDIA | %.2f | ".format(media)
    }
}

data class Materias(var materia: String?, val notas: MutableList<Notas>?){
    override fun toString(): String {
        val notasString = notas?.joinToString("\n") ?: ""
        return "$materia\n$notasString"
    }
}

data class Alunos(var Nome: String?, var idade: Int?, var serie: String?, val materias: MutableList<Materias>?){
    override fun toString(): String {
        val materiasString = materias?.joinToString("\n") ?: ""
        return "Aluno $Nome cursando a $serie\nMaterias:\n$materiasString"
    }
}

fun main() {
    fun linha(){
        for (i in 1..50){
            print("-")
        }
        println()
    }
    linha()
    println("Digite o nome do Aluno: ")
    val aluno = readLine()
    linha()
    println("Digite a idade do Aluno: ")
    val idade: Int = readLine()!!.toInt()
    linha()
    println("Em que série esse aluno está?\nEX.: 1ºA")
    val serie = readLine()!!.uppercase()
    linha()
    //println("O(A) $aluno tem $idade anos e está matriculado(a) na $serie série.")

    val cad_mat = mutableListOf<Materias>()
    var addNota = 0f

    println("--Cadastro de Matérias --")

    linha()
    while (true){
        println("Qual o nome da  ${cad_mat.size + 1}ª Matéria?")
        val novaMat = readLine()!!.toString()

        //cad_mat.add(Materias(readLine()!!.toString()))

        var totNota : Float = 0f
        val cad_notas = mutableListOf<Float>()

        linha()
        println("---Lançamento de Notas---")
        linha()

        for (i in 1..2){
            println("Digite a ${cad_notas.size + 1}ª Nota de $novaMat!")
            addNota = readLine()!!.toFloat()
            totNota += addNota
            cad_notas.add(addNota)
            linha()
        }

        val totMedia = totNota / 2
        val notas = Notas(cad_notas, totMedia)

        cad_mat.add(Materias(novaMat, mutableListOf(notas)))
        if (cad_mat.size == 2) break
    }
    println(cad_mat)
    val cad_aluno = Alunos(aluno, idade, serie, cad_mat)
    println(cad_aluno.toString())
}