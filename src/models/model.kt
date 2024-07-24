data class Notas(val notas: MutableList<Float>, var media: Float?, var situacao : String?){
    override fun toString(): String {
        val notasString = ("| NOTAS | "+notas.joinToString(" | "){"%.2f".format(it)})
        return "$notasString |\n" +
                "| MÉDIA | %.2f | $situacao |".format(media)
    }
}

data class Materias(var materia: String?, val notas: MutableList<Notas>?){
    override fun toString(): String {
        val notasString = notas?.joinToString("\n") ?: ""
        return " * $materia\n$notasString"
    }
}

data class Alunos(var rga : Int?, var nome: String?, var idade: Int?, var serie: String?, val materias: MutableList<Materias>?){
    override fun toString(): String {
        val materiasString = materias?.joinToString("\n") ?: ""
        return "| RGA: $rga | Aluno : $nome | Série : $serie |\n| Materias: |\n$materiasString\n".uppercase() + "-".repeat(50)
    }
}