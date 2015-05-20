package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param
import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.Days

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve3DRangeQueryBenchmark extends BenchmarkRunner(classOf[ZCurve3DRangeQueryBenchmark])
class ZCurve3DRangeQueryBenchmark extends CurveBenchmark {
   
  @Param
  val res = 1 //max res
  val y2k = new DateTime(2000, 1, 1, 0, 0, 0, DateTimeZone.forID("UTC"))
  val time_bound = new DateTime(2015, 12, 31, 23, 59, 59, DateTimeZone.forID("UTC"))

  val query_start_time = new DateTime(2000, 6, 15, 0, 0, 0, DateTimeZone.forID("UTC"))
  val query_end_time = new DateTime(2000, 7, 15, 23, 59, 59, DateTimeZone.forID("UTC"))

  val sfc = new ZCurve3D(Math.pow(2,res).toInt, { dt => Days.daysBetween(dt, y2k).getDays }) //precision for time using Z3??

  //curve

  //UR
  def timeZCurve3DQuadrantUR(reps: Int) = run(reps)(ZCurve3DQuadrantUR)
  def ZCurve3DQuadrantUR = {
         sfc.toRanges(0.0, 0.0, query_start_time, 180.0, 90.0, query_end_time)
  }

  //LL
  def timeZCurve3DQuadrantLL(reps: Int) = run(reps)(ZCurve3DQuadrantLL)
  def ZCurve3DQuadrantLL = {
         sfc.toRanges(-180.0, -90.0, query_start_time,  0.0, 0.0, query_end_time)
  }

  //UL
  def timeZCurve3DQuadrantUL(reps: Int) = run(reps)(ZCurve3DQuadrantUL)
  def ZCurve3DQuadrantUL = {
         sfc.toRanges(-180.0, 0.0, query_start_time, 0.0, 90.0, query_end_time)
  }

  //LR
  def timeZCurve3DQuadrantLR(reps: Int) = run(reps)(ZCurve3DQuadrantLR)
  def ZCurve3DQuadrantLR = {
         sfc.toRanges(0.0, -90.0, query_start_time, 180.0, 0.0, query_end_time)
  }


  //sfseize
  val x = DefaultDimensions.createLongitude(res)
  val y = DefaultDimensions.createLatitude(res)
  val t = DefaultDimensions.createDateTime(y2k, time_bound, res)

  val sfz3D = new ComposedCurve(new ZCurve(OrdinalVector(res,res,res)), Seq(x,y,t))


  //UR
  val queryUR = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0),
    DefaultDimensions.createDateTime(
      query_start_time,
      query_end_time,
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfUR(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfUR)
  def RowMajorCurve3DQuadrantsfUR = {
          sfz3D.getRangesCoveringCell(queryUR)
  }

  //LL
  val queryLL = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0),
    DefaultDimensions.createDateTime(
      query_start_time,
      query_end_time,
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfLL(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfLL)
  def RowMajorCurve3DQuadrantsfLL = {
          sfz3D.getRangesCoveringCell(queryLL)
  }

  //UL
  val queryUL = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0),
    DefaultDimensions.createDateTime(
      query_start_time,
      query_end_time,
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfUL(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfUL)
  def RowMajorCurve3DQuadrantsfUL = {
          sfz3D.getRangesCoveringCell(queryUL)
  }

  //LR
  val queryLR = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0),
    DefaultDimensions.createDateTime(
      query_start_time,
      query_end_time,
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfLR(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfLR)
  def RowMajorCurve3DQuadrantsfLR = {
          sfz3D.getRangesCoveringCell(queryLR)
  }
}
