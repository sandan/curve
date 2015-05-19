package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param

import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._
import org.eichelberger.sfc._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DRangeQueryBenchmark extends BenchmarkRunner(classOf[ZCurve2DRangeQueryBenchmark])
class ZCurve2DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 8 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)

  /* Quadrant range queries */
  //in (xmin, ymin, xmax, ymax) form
  val UR = ((0.0, 0.0), (180.0, 90.0))
  val LL = ((-180.0, -90.0), (0.0, 0.0))
  val UL = ((-180.0, 0.0), (0.0, 90.0))
  val LR = ((0.0, -90.0), (180.0, 0.0))

  //UR
  def timeZCurve2DQuadrantUR(reps: Int) = run(reps)(ZCurve2DQuadrantUR)
  def ZCurve2DQuadrantUR = {
          sfc.toRanges(UR._1._1, UR._1._2, UR._2._1, UR._2._2) //(xmin, ymin, xmax, ymax)
  }

  //LL
  def timeZCurve2DQuadrantLL(reps: Int) = run(reps)(ZCurve2DQuadrantLL)
  def ZCurve2DQuadrantLL = {
          sfc.toRanges(LL._1._1, LL._1._2, LL._2._1, LL._2._2) //(xmin, ymin, xmax, ymax)
  }

  //UL
  def timeZCurve2DQuadrantUL(reps: Int) = run(reps)(ZCurve2DQuadrantUL)
  def ZCurve2DQuadrantUL = {
          sfc.toRanges(UL._1._1, UL._1._2, UL._2._1, UL._2._2) //(xmin, ymin, xmax, ymax)
  }

  //LR
  def timeZCurve2DQuadrantLR(reps: Int) = run(reps)(ZCurve2DQuadrantLR)
  def ZCurve2DQuadrantLR = {
          sfc.toRanges(LR._1._1, LR._1._2, LR._2._1, LR._2._2) //(xmin, ymin, xmax, ymax)
  }


  //sfseize
  //queries are in (xmin, xmax, ymin, ymax form)

  val x = DefaultDimensions.createLongitude(res)
  val y = DefaultDimensions.createLatitude(res)

  val sfz2D = new ComposedCurve(
                    new ZCurve(OrdinalVector(res,res)),
                    Seq(x, y))

  //UR
  
  val URquery = Cell(Seq(
    DefaultDimensions.createDimension("x", UR._1._1, UR._2._1, 0),
    DefaultDimensions.createDimension("y", UR._1._2, UR._2._2, 0)
  ))

  def timeZCurve2DQuadrantsfUR(reps: Int) = run(reps)(ZCurve2DQuadrantsfUR)
  def ZCurve2DQuadrantsfUR = {
          sfz2D.getRangesCoveringCell(URquery)
  }

  //LL

  val LLquery = Cell(Seq(
    DefaultDimensions.createDimension("x", LL._1._1, LL._2._1, 0),
    DefaultDimensions.createDimension("y", LL._1._2, LL._2._2, 0)
  ))

  def timeZCurve2DQuadrantsfLL(reps: Int) = run(reps)(ZCurve2DQuadrantsfLL)
  def ZCurve2DQuadrantsfLL = {
          sfz2D.getRangesCoveringCell(LLquery)
  }

  //UL

  val ULquery = Cell(Seq(
    DefaultDimensions.createDimension("x", UL._1._1, UL._2._1, 0),
    DefaultDimensions.createDimension("y", UL._1._2, UL._2._2, 0)
  ))

  def timeZCurve2DQuadrantsfUL(reps: Int) = run(reps)(ZCurve2DQuadrantsfUL)
  def ZCurve2DQuadrantsfUL = {
          sfz2D.getRangesCoveringCell(ULquery)
  }

  //LR

  val LRquery = Cell(Seq(
    DefaultDimensions.createDimension("x", LR._1._1, LR._2._1, 0),
    DefaultDimensions.createDimension("y", LR._1._2, LR._2._2, 0)
  ))

  def timeZCurve2DQuadrantsfLR(reps: Int) = run(reps)(ZCurve2DQuadrantsfLR)
  def ZCurve2DQuadrantsfLR = {
          sfz2D.getRangesCoveringCell(LRquery)
  }
}
