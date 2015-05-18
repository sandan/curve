package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object RowMajorCurve2DRangeQueryBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DRangeQueryBenchmark])
class RowMajorCurve2DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 8 //max res
  val sfc = new RowMajorCurve2D(Math.pow(2,res).toInt)
  val sfrm2D = new RowMajorCurve(OrdinalVector(res,res))


  //UR
  def timeRowMajorCurve2DQuadrantUR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantUR)
  def RowMajorCurve2DQuadrantUR = {
          sfc.toRanges(0.0, 0.0, 180.0, 90.0)
  }

  //LL
  def timeRowMajorCurve2DQuadrantLL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantLL)
  def RowMajorCurve2DQuadrantLL = {
          sfc.toRanges(-180.0, -90.0, 0.0, 0.0)
  }

  //UL
  def timeRowMajorCurve2DQuadrantUL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantUL)
  def RowMajorCurve2DQuadrantUL = {
          sfc.toRanges(-180.0, 0.0, 0.0, 90.0)
  }

  //LR
  def timeRowMajorCurve2DQuadrantLR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantLR)
  def RowMajorCurve2DQuadrantLR = {
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
  def timeRowMajorCurve2DQuadrantsfUR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfUR)
  def RowMajorCurve2DQuadrantsfUR = {
          sfrm2D.getRangesCoveringQuery(URQuery)
  }

  //LL
  def timeRowMajorCurve2DQuadrantsfLL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfLL)
  def RowMajorCurve2DQuadrantsfLL = {
          sfrm2D.getRangesCoveringQuery(LLQuery)
  }

  //UL
  def timeRowMajorCurve2DQuadrantsfUL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfUL)
  def RowMajorCurve2DQuadrantsfUL = {
          sfrm2D.getRangesCoveringQuery(ULQuery)
  }

  //LR
  def timeRowMajorCurve2DQuadrantsfLR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfLR)
  def RowMajorCurve2DQuadrantsfLR = {
          sfrm2D.getRangesCoveringQuery(LRQuery)
  }

}
