fun main() {
    val graph = Graph()

    // Додаємо вершини та зв'язки між ними
    graph.addEdge("Математика", "Програмування")
    graph.addEdge("Програмування", "Алгоритми")
    graph.addEdge("Математика", "Физіка")
    graph.addEdge("Алгоритми", "Бази даних")
    graph.addEdge("Алгоритми", "IoT")

    // Викликаємо топологічне сортування
    val topologicalOrder = graph.topologicalSort()

    // Виводимо результат
    println("Топологічне сортування:")
    topologicalOrder.forEachIndexed { index, subject ->
        println("${index + 1}. $subject")
    }
}

class Graph {
    private val graphVertices = mutableMapOf<String, MutableList<String>>()

    fun addEdge(from: String, to: String) {
        graphVertices.getOrPut(from) { mutableListOf() }.add(to)
    }

    fun topologicalSort(): List<String> {
        val visited = mutableSetOf<String>()
        val result = mutableListOf<String>()

        for (vertex in graphVertices.keys) {
            if (vertex !in visited) {
                topologicalSortUtil(vertex, visited, result)
            }
        }

        return result.reversed()
    }

    private fun topologicalSortUtil(vertex: String, visited: MutableSet<String>, result: MutableList<String>) {
        visited.add(vertex)

        val neighbors = graphVertices[vertex] ?: emptyList()
        for (neighbor in neighbors) {
            if (neighbor !in visited) {
                topologicalSortUtil(neighbor, visited, result)
            }
        }

        result.add(vertex)
    }
}
