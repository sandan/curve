package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */

object RowMajorCurve2DHalfwayBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DHalfwayBenchmark])
class RowMajorCurve2DHalfwayBenchmark extends CurveBenchmark {
   
  val res = 1 //max res
  val sfc = new RowMajorCurve2D(Math.pow(2,res).toInt)
  val sfz2D = new RowMajorCurve(OrdinalVector(res,res))

  //queries are a function of resolution
  val numtiles = (Math.pow(2,res) * Math.pow(2,res)).toInt
  val center = (Math.pow(2,res)/2).toInt
  val end = (2 * center) - 1 //0 indexed square grid

  val HalfwayQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center/2, (center + end)/2)),
                 OrdinalRanges(OrdinalPair(center/2, (center+end)/2))))


    /* Halfway to border range queries */
  
    def timeRowMajorCurve2DH(reps: Int) = run(reps)(RowMajorCurve2DH)
    def RowMajorCurve2DH = {
            sfc.toRanges(-90.0, -45.0, 90.0, 45.0)
    }
  
    def timeRowMajorCurve2DSFH(reps: Int) = run(reps)(RowMajorCurve2DSFH)
    def RowMajorCurve2DSFH = {
          sfz2D.getRangesCoveringQuery(HalfwayQuery)
    }



}
