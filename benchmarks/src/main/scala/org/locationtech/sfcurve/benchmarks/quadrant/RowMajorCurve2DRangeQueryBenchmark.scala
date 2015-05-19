package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param

import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._
import org.joda.time.{DateTimeZone, DateTime}

import org.joda.time.Days

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object RowMajorCurve2DRangeQueryBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DRangeQueryBenchmark])
class RowMajorCurve2DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 8 //max res
  val sfc = new RowMajorCurve2D(Math.pow(2,res).toInt)

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

  //sfseize

  val x = DefaultDimensions.createLongitude(res)  
  val y = DefaultDimensions.createLatitude(res)   

  val sfrm2D = new ComposedCurve(
                    new RowMajorCurve(OrdinalVector(res,res)),
                    Seq(x, y))
               
  //UR
  val URquery = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0)
  ))

  def timeRowMajorCurve2DQuadrantsfUR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfUR)
  def RowMajorCurve2DQuadrantsfUR = {
          sfrm2D.getRangesCoveringCell(URquery)
  }

  //LL
  val LLquery = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0)
  ))

  def timeRowMajorCurve2DQuadrantsfLL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfLL)
  def RowMajorCurve2DQuadrantsfLL = {
          sfrm2D.getRangesCoveringCell(LLquery)
  }

  //UL
  val ULquery = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0)
  ))

  def timeRowMajorCurve2DQuadrantsfUL(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfUL)
  def RowMajorCurve2DQuadrantsfUL = {
          sfrm2D.getRangesCoveringCell(ULquery)
  }

  //LR
  val LRquery = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0)
  ))

  def timeRowMajorCurve2DQuadrantsfLR(reps: Int) = run(reps)(RowMajorCurve2DQuadrantsfLR)
  def RowMajorCurve2DQuadrantsfLR = {
          sfrm2D.getRangesCoveringCell(LRquery)
  }

}
