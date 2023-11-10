class Graph {
    private val graphVertices = mutableMapOf<String, MutableList<String>>()

    fun addEdge(from: String, to: String) {
        graphVertices.getOrPut(from) { mutableListOf() }.add(to)
        printGraphState(from, to)
    }

    fun depthFirstSearch(startVertex: String) {
        val visited = mutableSetOf<String>()
        dfs(startVertex, visited)
    }

    private fun dfs(vertex: String, visited: MutableSet<String>) {
        visited.add(vertex)
        println("Відвідуємо вершину: $vertex")

        val neighbors = graphVertices[vertex] ?: emptyList()
        for (neighbor in neighbors) {
            if (neighbor !in visited) {
                dfs(neighbor, visited)
            }
        }
    }

    private fun printGraphState(from: String, to: String) {
        println("Додаємо до графа зв'язок: $from -> $to")
        println("Поточні вершини графа:")
        for ((vertex, neighbors) in graphVertices) {
            println("$vertex -> $neighbors")
        }
        println()
    }

    // Інші методи тут

    fun topologicalSort(): List<String> {
        val visited = mutableSetOf<String>()
        val result = mutableListOf<String>()

        for (vertex in graphVertices.keys) {
            if (vertex !in visited) {
                initiateSort(vertex, visited, result)
            }
        }

        val reversedResult = result.reversed()
        printFinalState(visited, result, reversedResult)
        return reversedResult
    }

    private fun initiateSort(vertex: String, visited: MutableSet<String>, result: MutableList<String>) {
        visited.add(vertex)
        printInitiateState(vertex, visited, result)

        val neighbors = graphVertices[vertex] ?: emptyList()
        for (neighbor in neighbors) {
            if (neighbor !in visited) {
                traverse(vertex, neighbor, visited, result)
            }
        }

        result.add(vertex)
        printAddVertexState(vertex, result)
    }

    private fun traverse(vertex: String, neighbor: String, visited: MutableSet<String>, result: MutableList<String>) {
        printTraverseState(vertex, neighbor, visited, result)
        initiateSort(neighbor, visited, result)
    }

    private fun printInitiateState(vertex: String, visited: MutableSet<String>, result: MutableList<String>) {
        println("Ініціюємо топологічне сортування з вершини: $vertex")
        println("Поточні відвідані вершини: $visited")
        println("Поточний список результатів: $result")
        println()
    }

    private fun printAddVertexState(vertex: String, result: MutableList<String>) {
        println("Додано вершину $vertex до списку результатів")
        println("Поточний список результатів: $result")
        println()
    }

    private fun printTraverseState(vertex: String, neighbor: String, visited: MutableSet<String>, result: MutableList<String>) {
        println("Переходимо від вершини $vertex до $neighbor")
        println("Поточні відвідані вершини: $visited")
        println("Поточний список результатів: $result")
        println()
    }

    private fun printFinalState(visited: MutableSet<String>, result: List<String>, reversedResult: List<String>) {
        println("Топологічне сортування завершено.")
        println("Всі відвідані вершини: $visited")
        println("Кінцевий список результатів: $result")
        println("Відсортований список: ${reversedResult.joinToString(", ")}")
    }
}

fun main() {
    val graph = Graph()

    graph.addEdge("Математика", "Програмування")
    graph.addEdge("Програмування", "Алгоритми")
    graph.addEdge("Математика", "Фізика")
    graph.addEdge("Алгоритми", "Бази даних")
    graph.addEdge("Алгоритми", "ІoT")

    println("Моделювання Depth-First Search:")
    graph.depthFirstSearch("Математика")

    val topologicalOrder = graph.topologicalSort()

    println("Топологічне сортування:")
    topologicalOrder.forEachIndexed { index, subject ->
        println("${index + 1}. $subject")
    }
}