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
object RowMajorCurve3DRangeQueryBenchmark extends BenchmarkRunner(classOf[RowMajorCurve3DRangeQueryBenchmark])
class RowMajorCurve3DRangeQueryBenchmark extends CurveBenchmark {
   
  @Param
  val res = 8 //max res

  //sfseize

  val x = DefaultDimensions.createLongitude(res)  
  val y = DefaultDimensions.createLatitude(res)   
  val t = DefaultDimensions.createDateTime(
      new DateTime(2000, 1, 1, 0, 0, 0, DateTimeZone.forID("UTC")),
      new DateTime(2015, 12, 31, 23, 59, 59, DateTimeZone.forID("UTC")),
      res //time precision bits
    )

  val sfrm3D = new ComposedCurve(
                    new RowMajorCurve(OrdinalVector(res,res,res)),
                    Seq(x, y, t))
               
  //UR
  val queryUR = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0),
    DefaultDimensions.createDateTime(
      new DateTime(2000, 6, 15, 0, 0, 0, DateTimeZone.forID("UTC")),
      new DateTime(2000, 7, 15, 23, 59, 59, DateTimeZone.forID("UTC")),
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfUR(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfUR)
  def RowMajorCurve3DQuadrantsfUR = {
          sfrm3D.getRangesCoveringCell(queryUR)
  }

  //LL
  val queryLL = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0),
    DefaultDimensions.createDateTime(
      new DateTime(2000, 6, 15, 0, 0, 0, DateTimeZone.forID("UTC")),
      new DateTime(2000, 7, 15, 23, 59, 59, DateTimeZone.forID("UTC")),
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfLL(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfLL)
  def RowMajorCurve3DQuadrantsfLL = {
          sfrm3D.getRangesCoveringCell(queryLL)
  }

  //UL
  val queryUL = Cell(Seq(
    DefaultDimensions.createDimension("x", -180.0, 0.0, 0),
    DefaultDimensions.createDimension("y", 0.0, 90.0, 0),
    DefaultDimensions.createDateTime(
      new DateTime(2000, 6, 15, 0, 0, 0, DateTimeZone.forID("UTC")),
      new DateTime(2000, 7, 15, 23, 59, 59, DateTimeZone.forID("UTC")),
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfUL(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfUL)
  def RowMajorCurve3DQuadrantsfUL = {
          sfrm3D.getRangesCoveringCell(queryUL)
  }

  //LR
  val queryLR = Cell(Seq(
    DefaultDimensions.createDimension("x", 0.0, 180.0, 0),
    DefaultDimensions.createDimension("y", -90.0, 0.0, 0),
    DefaultDimensions.createDateTime(
      new DateTime(2000, 6, 15, 0, 0, 0, DateTimeZone.forID("UTC")),
      new DateTime(2000, 7, 15, 23, 59, 59, DateTimeZone.forID("UTC")),
      0
    )
  ))

  def timeRowMajorCurve3DQuadrantsfLR(reps: Int) = run(reps)(RowMajorCurve3DQuadrantsfLR)
  def RowMajorCurve3DQuadrantsfLR = {
          sfrm3D.getRangesCoveringCell(queryLR)
  }

}
