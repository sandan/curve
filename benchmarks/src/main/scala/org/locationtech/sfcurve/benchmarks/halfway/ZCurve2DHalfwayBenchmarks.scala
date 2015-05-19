package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param
import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DHalfwayBenchmark extends BenchmarkRunner(classOf[ZCurve2DHalfwayBenchmark])
class ZCurve2DHalfwayBenchmark extends CurveBenchmark {

  @Param  
  val res = 1 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)
  val sfz2D = new ComposedCurve(
                   new ZCurve(OrdinalVector(res,res)),
                   Seq(DefaultDimensions.createLongitude(res), DefaultDimensions.createLatitude(res)))

  val HalfwayQuery = Cell(Seq(
    DefaultDimensions.createDimension("x", -90.0, 90.0, 0),
    DefaultDimensions.createDimension("y", -45.0, 45.0, 0)
  ))

  /* Halfway to border range queries */
  def timeZCurve2DH(reps: Int) = run(reps)(ZCurve2DH)
  def ZCurve2DH = {
          sfc.toRanges(-90.0, -45.0, 90.0, 45.0)
  }

  def timeZCurve2DSFH(reps: Int) = run(reps)(ZCurve2DSFH)
  def ZCurve2DSFH = {
        sfz2D.getRangesCoveringCell(HalfwayQuery)
  }



}
