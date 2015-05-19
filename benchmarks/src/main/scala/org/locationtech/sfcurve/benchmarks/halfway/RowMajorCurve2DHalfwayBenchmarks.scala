package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param
import org.eichelberger.sfc._
import org.eichelberger.sfc.SpaceFillingCurve._
import org.eichelberger.sfc.Dimensions._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */

object RowMajorCurve2DHalfwayBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DHalfwayBenchmark])
class RowMajorCurve2DHalfwayBenchmark extends CurveBenchmark {
  @Param
  val res = 1 //max res
  val sfc = new RowMajorCurve2D(Math.pow(2,res).toInt)
  val sfz2D = new ComposedCurve(
                   new RowMajorCurve(OrdinalVector(res,res)),
                   Seq(DefaultDimensions.createLongitude(res), DefaultDimensions.createLatitude(res)))

  val HalfwayQuery = Cell(Seq(
    DefaultDimensions.createDimension("x", -90.0, 90.0, 0),
    DefaultDimensions.createDimension("y", -45.0, 45.0, 0)
  ))


  /* Halfway to border range queries */
  
  def timeRowMajorCurve2DH(reps: Int) = run(reps)(RowMajorCurve2DH)
  def RowMajorCurve2DH = {
          sfc.toRanges(-90.0, -45.0, 90.0, 45.0)
  }
  
  def timeRowMajorCurve2DSFH(reps: Int) = run(reps)(RowMajorCurve2DSFH)
  def RowMajorCurve2DSFH = {
        sfz2D.getRangesCoveringCell(HalfwayQuery)
  }



}
