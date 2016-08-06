package tv.pluto.icfp

import java.io.ByteArrayInputStream
import sys.process._

object Visualizer {

  def visualize(edges: Iterable[Edge]): Unit = {
    val input = edges.map(edge => {
      s"${edge.p1.x.toDouble} ${edge.p1.y.toDouble} ${edge.p2.x.toDouble} ${edge.p2.y.toDouble}"
    }).mkString("\n")

    val builder = "python ./plotting/plot_edges.py" #< new ByteArrayInputStream(input.getBytes())

    builder.!!
  }

  def main(args: Array[String]) {
    visualize(List(Edge(Point(1, 2), Point(3, 4)), Edge(Point(2, -1), Point(3, 4)), Edge(Point(1, 2), Point(-3, 4))))
  }

}