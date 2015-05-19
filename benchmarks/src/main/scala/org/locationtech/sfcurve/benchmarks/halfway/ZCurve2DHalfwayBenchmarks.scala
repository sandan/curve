package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param
import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.examples.Geohash
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DHalfwayBenchmark extends BenchmarkRunner(classOf[ZCurve2DHalfwayBenchmark])
class ZCurve2DHalfwayBenchmark extends CurveBenchmark {
   
  val res = 1 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)
  val sfz2D = new ZCurve(OrdinalVector(res,res))


  //queries are a function of resolution
  val numtiles = (Math.pow(2,res) * Math.pow(2,res)).toInt
  val center = (Math.pow(2,res)/2).toInt
  val end = (2 * center) - 1 //0 indexed square grid

  val HalfwayQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center/2, (center + end)/2)),
                 OrdinalRanges(OrdinalPair(center/2, (center+end)/2))))


    /* Halfway to border range queries */
  

    def timeZCurve2DH(reps: Int) = run(reps)(ZCurve2DH)
    def ZCurve2DH = {
            sfc.toRanges(-90.0, -45.0, 90.0, 45.0)
    }
  
    def timeZCurve2DSFH(reps: Int) = run(reps)(ZCurve2DSFH)
    def ZCurve2DSFH = {
          sfz2D.getRangesCoveringQuery(HalfwayQuery)
    }



}
