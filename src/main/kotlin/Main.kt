fun main() {
    // Create a new instance of the Graph class.
    val graph = Graph()

    // Add edges to the graph, representing relationships between subjects.
    graph.addEdge("Mathematics", "Programming")
    graph.addEdge("Programming", "Algorithms")
    graph.addEdge("Mathematics", "Physics")
    graph.addEdge("Algorithms", "Database")
    graph.addEdge("Algorithms", "IoT")

    // Perform topological sorting on the graph.
    val topologicalOrder = graph.topologicalSort()

    // Print the result of topological sorting.
    println("Topological sorting:")
    topologicalOrder.forEachIndexed { index, subject ->
        println("${index + 1}. $subject")
    }
}

class Graph {
    // Create a mutable map to store vertices and their adjacency lists.
    private val graphVertices = mutableMapOf<String, MutableList<String>>()

    // Add an edge between two vertices.
    fun addEdge(from: String, to: String) {
        //  If 'from' vertex is not in the map, add it with an empty list.
        //  Then, add the 'to' vertex to the adjacency list of 'from'.
        graphVertices.getOrPut(from) { mutableListOf() }.add(to)
    }

    // Perform topological sorting on the graph.
    fun topologicalSort(): List<String> {
        //  Create a set to track visited vertices during traversal.
        val visited = mutableSetOf<String>()

        //  Create a list to store the topologically sorted result.
        val result = mutableListOf<String>()

        // Iterate through all vertices in the graph.
        for (vertex in graphVertices.keys) {
            // If the vertex has not been visited, initiate a topological sort.
            if (vertex !in visited) {
                topologicalSortUtil(vertex, visited, result)
            }
        }

        // Return the topologically sorted result in reverse order.
        return result.reversed()
    }

    //  Recursive utility function for topological sorting.
    private fun topologicalSortUtil(vertex: String, visited: MutableSet<String>, result: MutableList<String>) {
        // Mark the current vertex as visited.
        visited.add(vertex)

        //  Get the neighbors (adjacency list) of the current vertex.
        val neighbors = graphVertices[vertex] ?: emptyList()

        //  Iterate through the neighbors.
        for (neighbor in neighbors) {
            //  If the neighbor has not been visited, recursively perform topological sort.
            if (neighbor !in visited) {
                topologicalSortUtil(neighbor, visited, result)
            }
        }

        // Add the current vertex to the result list.
        result.add(vertex)
    }
}
