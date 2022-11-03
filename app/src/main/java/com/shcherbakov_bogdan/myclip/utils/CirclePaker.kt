package com.shcherbakov_bogdan.myclip.utils

import java.lang.Math.acos
import java.lang.Math.atan2
import java.util.*
import kotlin.math.pow

class Node(var x: Double, var y: Double, var radius: Double, var distance: Double)

// Euclidean distance between node1 and node2
fun distanceNodes(node1: Node, node2: Node): Double {
    return kotlin.math.sqrt((node1.x - node2.x).pow(2) + (node1.y - node2.y).pow(2))
}

// Euclidean distance from origin and node
fun distanceFromOrigin(node: Node): Double {
    return kotlin.math.sqrt((node.x).pow(2) + (node.y).pow(2))
}

//## Creates a new node from cm, cn and a fixed radius for the new node. Both solutions are returned.
fun newNode(cm: Node, cn: Node, radius: Double): List<Node> {
    val phi1 = atan2(cn.y - cm.y, cn.x - cm.x) //мб atan()
    val phi2 = acos(
        (distanceNodes(cm, cn).pow(2) + (cm.radius + radius).pow(2) -
                (cn.radius + radius).pow(2))
                / (2 * (cm.radius + radius) * distanceNodes(cm, cn))
    )
    val sol1 = Node(
        cm.x + (cm.radius + radius) * kotlin.math.cos(phi1 + phi2),
        cm.y + (cm.radius + radius) * kotlin.math.sin(phi1 + phi2),
        radius, 0.00
    )
    val sol2 = Node(
        cm.x + (cm.radius + radius) * kotlin.math.cos(phi1 - phi2),
        cm.y + (cm.radius + radius) * kotlin.math.sin(phi1 - phi2),
        radius, 0.00
    )
    sol1.distance = distanceFromOrigin(sol1)
    sol2.distance = distanceFromOrigin(sol2)
    return listOf(sol1, sol2)
}

// Calculates the position of the first three nodes and recenters the configuration around the origin.
fun initialise(
    r0: Double,
    r1: Double,
    r2: Double
): Triple<MutableList<Node>, Node, LinkedList<Node>> {
    val nodes: MutableList<Node> =
        mutableListOf(Node(0.00, 0.00, r0, 0.00), Node(r0 + r1, 0.00, r1, 0.00))
    val (sol1, sol2) = newNode(nodes[0], nodes[1], r2)
    if (sol1.y < 0) {
        nodes.add(sol1)
    } else {
        nodes.add(sol2)
    }

    // Auxillary variables for equal detour point calculation
    val a = r0 + r1
    val b = r0 + r2
    val c = r1 + r2

    val s = (a + b + c) / 2
    val delta = kotlin.math.sqrt(s * (s - a) * (s - b) * (s - c))

    var l1 = a + delta / (s - a)
    var l2 = b + delta / (s - b)
    var l3 = c + delta / (s - c)

    val lSum = l1 + l2 + l3
    l1 /= lSum; l2 /= lSum; l3 /= lSum

    // Calculate coords of equal detour point and recenter the node
    val xEDP = l1 * nodes[2].x + l2 * nodes[1].x + l3 * nodes[0].x
    val yEDP = l1 * nodes[2].y + l2 * nodes[1].y + l3 * nodes[0].y
    for (node in nodes) {
        node.x = node.x - xEDP
        node.y = node.y - yEDP
        node.distance = distanceFromOrigin(node)
    }
    val frontChain: LinkedList<Node> = LinkedList(nodes)
    var cm = frontChain.minByOrNull { it.distance }
    if (cm == null) {
        cm = Node(1.0, 1.0, 1.0, 1.0)
    }
    return Triple(nodes, cm, frontChain)
}

//    Generates candidate nodes
//    d is the signed perpendicular distance of the vector cm to cn
//    Only the solution with d<0 (i.e. exterior to the front chain) is returned
fun candidateNode(cm: Node, radius: Double, frontChain: LinkedList<Node>): Node {
    val index = frontChain.indexOf(cm)
    val cn = if (index + 1 == frontChain.size) {
        frontChain.first
    } else {
        frontChain[index + 1]
    }
    val (sol1, sol2) = newNode(cm, cn, radius)
    val d = (sol1.x - cm.x) * (cn.y - cm.y) - (sol1.y - cm.y) * (cn.x - cm.x)
    if (d < 0) {
        return sol1
    }
    return sol2
}

//Returns the closest intersecting node, where distance here is defined to be the distance
// traversed from cm/cn to the node
fun checkIntersect(condidate: Node, cm: Node, frontChain: LinkedList<Node>): Pair<Node?, Int> {
    val index = frontChain.indexOf(cm)
    val cn = if (index + 1 == frontChain.size) {
        frontChain.first
    } else {
        frontChain[index + 1]
    }
    var intersectingNode: Node? = null
    var direction = 0
    val intersectingNodes =
        frontChain.filter {
            distanceNodes(
                condidate,
                it
            ) - (it.radius + condidate.radius) < -0.00000001
        }
    if (intersectingNodes.isEmpty()) {
        return Pair(intersectingNode, direction)
    }
    val totalDistance = frontChain.sumOf { it.radius * 2 }
    val distances: MutableList<List<Double>> = mutableListOf()
    for (node in intersectingNodes) {
        distances.add(distanceToNode(node, cm, cn, frontChain, totalDistance))
    }
    var minDistance = distances[0]
    var minIndex = 0
    for (distance in distances) {
        if (distance[1] < minDistance[1]) {
            minDistance = distance
            minIndex = distances.indexOf(distance)
        }
    }
    intersectingNode = intersectingNodes[minIndex]
    direction = if (minDistance[0] < minDistance[1]) {
        -1
    } else {
        1
    }
    return Pair(intersectingNode, direction)
}

// Computes the distance traversed from to_node to cm/cn along the front chain
fun distanceToNode(
    toNode: Node,
    cm: Node,
    cn: Node,
    frontChain: LinkedList<Node>,
    totalDistance: Double
): List<Double> {
    var cmBackwardDistance = cn //мб сn не надо передавать , тк в коде его нет
    var currentNode = cm
    while (true) {
        cmBackwardDistance.radius += 2 * currentNode.radius
        currentNode = if (currentNode == frontChain.first) {
            frontChain.last
        } else {
            val index = frontChain.indexOf(currentNode)
            frontChain[index - 1]
        }
        if (currentNode == toNode) {
            break
        }
    }
    val cnForwardDistance = totalDistance - cmBackwardDistance.radius - 2 * toNode.radius
    return listOf(cmBackwardDistance.radius, cnForwardDistance)
}

fun removeNodesAhead(toNode: Node?, cm: Node, frontChain: LinkedList<Node>) {
    var index = frontChain.indexOf(cm)
    var cmNext = if (index + 1 == frontChain.size) {
        null
    } else {
        frontChain[index + 1]
    }// вроде как нужно сделала, но не уверена
    while (cmNext != toNode) {
        if (cm == frontChain.last) {
            if (frontChain.first == toNode) {
                break
            }
            frontChain.remove(frontChain.first)
            index = frontChain.indexOf(cm)
            cmNext =
                null // тут вроде всегда null следующий(ну типо если всегда после последнего null), но если не пройдет, то можно вставить код
            continue
        }
        frontChain.remove(cmNext)
        cmNext = if (index + 1 == frontChain.size) {
            null
        } else {
            frontChain[index + 1]
        }
    }
}

fun removeNodesBehind(toNode: Node?, cn: Node, frontChain: LinkedList<Node>) {
    var index = frontChain.indexOf(cn)
    var cnPrev = if (index == 0) {
        null
    } else {
        frontChain[index - 1]
    }
    while (cnPrev != toNode) {
        if (cn == frontChain.first) {
            if (frontChain.last == toNode) {
                break
            }
            frontChain.remove(frontChain.last)
            cnPrev = null // тоже самое (не уверена)
            continue
        }
        frontChain.remove(cnPrev)
        index = frontChain.indexOf(cn)
        cnPrev = if (index == 0) {
            null
        } else {
            frontChain[index - 1]
        }
    }
}

fun modifyFrontChain(
    intersectionNode: Node?,
    cm: Node,
    frontChain: LinkedList<Node>,
    direction: Int
): Pair<Node?, Boolean> {
    if (direction == 0) {
        return Pair(cm, false)
    }
    var newCm = cm //нужно только потому что параметры менять нельзя
    if (direction == -1) {
        val index = frontChain.indexOf(cm)
        val cn = if (index + 1 == frontChain.size) {
            frontChain.first
        } else {
            frontChain[index + 1]
        }
        removeNodesBehind(intersectionNode, cn, frontChain)
        if (intersectionNode != null) {
            newCm = intersectionNode
        }
    } else {
        removeNodesAhead(intersectionNode, cm, frontChain)
    }
    return Pair(newCm, true)
}

fun resolveIntersection(cm: Node, radius: Double, frontChain: LinkedList<Node>): Pair<Node, Node?> {
    var intersection = true
    var candidate: Node? = null
    var newCm = cm
    while (intersection) {
        candidate = candidateNode(cm, radius, frontChain)
        val (intersectionNode, direction) = checkIntersect(candidate, cm, frontChain)
        val (temp, temp2) = modifyFrontChain(intersectionNode, cm, frontChain, direction)
        if (temp != null) {
            newCm = temp
        }
        intersection = temp2
    }
    return Pair(newCm, candidate)
}

fun pack(radii: List<Double>): MutableList<Triple<Double, Double, Double>> {
    if (radii.size < 3) {
        TODO()//прокинуть ошибку в логи для себя
    }
    var (nodes, cm, frontChain) = initialise(radii[0], radii[1], radii[2])
    var i = 0
    val result: MutableList<Triple<Double, Double, Double>> = mutableListOf()
    for (radius in radii) {
        if (i in 0..2) {
            result.add(Triple(nodes[i].x, nodes[i].y, nodes[i].radius))
            i++
            continue
        }
        val (temp1, newNode) = resolveIntersection(cm, radius, frontChain)
        cm = temp1
        if (newNode != null) {
            val index = frontChain.indexOf(cm)
            frontChain.add(index + 1, newNode)
            result.add(Triple(newNode.x, newNode.y, newNode.radius))
        }
        i++
    }
    return result //ВАЖНО!!!ВОЗМОЖНО!!! НУЖНО ВЕЗДЕ ВОЗВРАЩАТЬ frontChain ТК ЭТО НЕ ССЫЛОЧНЫЙ ТИП(не запускала, не проверяла)
}