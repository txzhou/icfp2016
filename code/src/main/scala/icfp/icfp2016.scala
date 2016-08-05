package icfp

/**
  * Created by htien on 8/5/16.
  */

import Origami._

object icfp2016 {

  val fourVertices =
    """4
       0,0
       1,0
       1,1
       0,1
    """.stripMargin

  val destination: Seq[Point] = OrigamiParse.parsePolygon.run(OrigamiParse.tokenize(fourVertices)).value._2.pts

  case class SilhouetteState(polys: Seq[Polygon], edges: Seq[LineSegment], map: Map[Int, List[Point]]) {
    val isSolved: Boolean = polys.length == 1 && destination.forall(polys.head.pts.contains(_))
    val isLegal: Boolean = true
    val vertices: Set[Point] = (polys.flatMap(_.pts) ++ edges.flatMap(_.endpoints)).toSet
//    val facets: Seq[Facet] = ???
//    val normalization: Silhouette = ???

    // def unfold(edge: LineSegment): List[SilhouetteState] = ???

  }

  //(silh, skel) => silhState

  def analyze(prob: Problem): SilhouetteState = {
    val initLabel = prob._2.edges.flatMap(line => line.endpoints).distinct.zipWithIndex.map {
      case (p, i) => (i, List(p))
    }.toMap
    SilhouetteState(prob._1.polys, prob._2.edges, initLabel)
  }


  case class Solution(vertices: Set[Point], map: Map[Int, List[Point]]) {
 // case class Solution(vertices: Set[Point], facets: Seq[Facet], map: Map[Int, List[Point]]) {
    //    override def toString(): String = {
    //      ???
    //    }
  }

  def solve(problem: SilhouetteState): Solution = {

    if (problem.isSolved) Solution(problem.vertices, problem.map)
//    else {
//      for {
//        edge <- problem.edges // boundary edges
//        progress <- problem.unfold(edge).filter(_.isLegal)
//      } yield solve(progress)
//    }.head
    else Solution(problem.vertices, problem.map)
  }


}

object solve {

  import implicits._

  import icfp2016._
  import OrigamiParse._


  val ex =
    """1
4
0,0
1,0
1/2,1/2
0,1/2
5
0,0 1,0
1,0 1/2,1/2
1/2,1/2 0,1/2
0,1/2 0,0
0,0 1/2,1/2
    """

  val initToks = tokenize(ex)
  println(s"initToks = $initToks")
  val prob = parseProblem.run(initToks).value._2
  println(prob)

//  val problem: SilhouetteState = analyze(prob)
//  val solution: Solution = icfp2016.solve(problem)
//  println(solution)

}
