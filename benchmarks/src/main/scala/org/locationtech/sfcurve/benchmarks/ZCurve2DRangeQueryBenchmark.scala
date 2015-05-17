package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DRangeQueryBenchmark extends BenchmarkRunner(classOf[ZCurve2DRangeQueryBenchmark])
class ZCurve2DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 7 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)

  //time range query results under various resolutions

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
}
