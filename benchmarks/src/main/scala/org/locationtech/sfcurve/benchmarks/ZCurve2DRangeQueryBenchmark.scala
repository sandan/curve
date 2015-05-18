package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param
import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DRangeQueryBenchmark extends BenchmarkRunner(classOf[ZCurve2DRangeQueryBenchmark])
class ZCurve2DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 8 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)
  val sfz2D = new ZCurve(OrdinalVector(res,res))


  //UR
  def timeZCurve2DQuadrantUR(reps: Int) = run(reps)(ZCurve2DQuadrantUR)
  def ZCurve2DQuadrantUR = {
          sfc.toRanges(0.0, 0.0, 180.0, 90.0)
  }

  //LL
  def timeZCurve2DQuadrantLL(reps: Int) = run(reps)(ZCurve2DQuadrantLL)
  def ZCurve2DQuadrantLL = {
          sfc.toRanges(-180.0, -90.0, 0.0, 0.0)
  }

  //UL
  def timeZCurve2DQuadrantUL(reps: Int) = run(reps)(ZCurve2DQuadrantUL)
  def ZCurve2DQuadrantUL = {
          sfc.toRanges(-180.0, 0.0, 0.0, 90.0)
  }

  //LR
  def timeZCurve2DQuadrantLR(reps: Int) = run(reps)(ZCurve2DQuadrantLR)
  def ZCurve2DQuadrantLR = {
          sfc.toRanges(0.0, -90.0, 180.0, 0.0)
  }



  //queries are a function of resolution
  val numtiles = (Math.pow(2,res) * Math.pow(2,res)).toInt
  val center = (Math.pow(2,res)/2).toInt
  val end = (2 * center) - 1 //0 indexed square grid
  val URQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center,end)),
                 OrdinalRanges(OrdinalPair(center,end))))

  val ULQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(0,center)),
                 OrdinalRanges(OrdinalPair(center,end))))

  val LRQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center,end)),
                 OrdinalRanges(OrdinalPair(0,center))))

  val LLQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(0,center)),
                 OrdinalRanges(OrdinalPair(0,center))))

  //sfseize
  def timeZCurve2DQuadrantsfUR(reps: Int) = run(reps)(ZCurve2DQuadrantsfUR)
  def ZCurve2DQuadrantsfUR = {
          sfz2D.getRangesCoveringQuery(URQuery)
  }

  //LL
  def timeZCurve2DQuadrantsfLL(reps: Int) = run(reps)(ZCurve2DQuadrantsfLL)
  def ZCurve2DQuadrantsfLL = {
          sfz2D.getRangesCoveringQuery(LLQuery)
  }

  //UL
  def timeZCurve2DQuadrantsfUL(reps: Int) = run(reps)(ZCurve2DQuadrantsfUL)
  def ZCurve2DQuadrantsfUL = {
          sfz2D.getRangesCoveringQuery(ULQuery)
  }

  //LR
  def timeZCurve2DQuadrantsfLR(reps: Int) = run(reps)(ZCurve2DQuadrantsfLR)
  def ZCurve2DQuadrantsfLR = {
          sfz2D.getRangesCoveringQuery(LRQuery)
  }

}
