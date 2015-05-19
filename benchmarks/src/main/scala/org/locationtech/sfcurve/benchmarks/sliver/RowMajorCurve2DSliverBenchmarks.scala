package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */

object RowMajorCurve2DSliverBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DSliverBenchmark])
class RowMajorCurve2DSliverBenchmark extends CurveBenchmark {
   
  val res = 1 //max res
  val sfc = new RowMajorCurve2D(Math.pow(2,res).toInt)


  val x = 180
  val y = 90
  val v_x =( 0.1 * 360 )
  val v_y =( 0.8 * 180 )

    /* Vertical sliver range queries 
     * ~10% of total dim length horizontal, ~80% of total dim length vertical
     */
//curve 
    def timeRowMajorCurve2DLL(reps: Int) = run(reps)(RowMajorCurve2DLL)
    def RowMajorCurve2DLL = {
            sfc.toRanges(-x, -v_y/2, -x + v_x  , v_y/2)
    }
  
    def timeRowMajorCurve2DLR(reps: Int) = run(reps)(RowMajorCurve2DLR)
    def RowMajorCurve2DLR = {
            sfc.toRanges(-x + 90, -v_y/2, -x + 90 + v_x, v_y/2)
    }
  
 
    def timeRowMajorCurve2DRL(reps: Int) = run(reps)(RowMajorCurve2DRL)
    def RowMajorCurve2DRL = {
            sfc.toRanges(x - 90, -v_y/2, (x - 90) + v_x  , v_y/2)
    }
  
    def timeRowMajorCurve2DRR(reps: Int) = run(reps)(RowMajorCurve2DRR)
    def RowMajorCurve2DRR = {
            sfc.toRanges(x - v_x, -v_y/2, x, v_y/2)
    }


  val v_s =( 0.8 * 360 )
  val v_t =( 0.1 * 180 )

    /* Horizontal sliver range queries 
     * ~10% of total dim length vertical, ~80% of total dim length horizontal
     */

    def timeRowMajorCurve2DHLL(reps: Int) = run(reps)(RowMajorCurve2DHLL)
    def RowMajorCurve2DHLL = {
            sfc.toRanges(-v_s/2, -y, v_s/2, -y + v_t)
    }
  
    def timeRowMajorCurve2DHLR(reps: Int) = run(reps)(RowMajorCurve2DHLR)
    def RowMajorCurve2DHLR = {
            sfc.toRanges(-v_s/2, -y + 45, v_s/2, -y + 45 + v_t)
    }
  
 
    def timeRowMajorCurve2DHRL(reps: Int) = run(reps)(RowMajorCurve2DHRL)
    def RowMajorCurve2DHRL = {
            sfc.toRanges(-v_s/2, y - 45, v_s/2, (y - 45) + v_t)
    }
  
    def timeRowMajorCurve2DHRR(reps: Int) = run(reps)(RowMajorCurve2DHRR)
    def RowMajorCurve2DHRR = {
            sfc.toRanges(-v_s/2, y - v_t, v_s/2, y)
    }

     val sfz2D = new RowMajorCurve(OrdinalVector(res,res))
   
     //queries are a function of resolution
     val numtiles = (Math.pow(2,res) * Math.pow(2,res)).toInt
     val center = (Math.pow(2,res)/2).toInt
     val end = (2 * center) - 1 //0 indexed square grid
   
     val vertQuery = Query(Seq(
                    OrdinalRanges(OrdinalPair(center/2, (center + end)/2)),
                    OrdinalRanges(OrdinalPair(center/2, (center+end)/2))))
   
     val horizQuery = Query(Seq(
                    OrdinalRanges(OrdinalPair(center/2, (center + end)/2)),
                    OrdinalRanges(OrdinalPair(center/2, (center+end)/2))))
   
    //sfseize 
       def timeRowMajorCurve2DSFH(reps: Int) = run(reps)(RowMajorCurve2DSFH)
       def RowMajorCurve2DSFH = {
             sfz2D.getRangesCoveringQuery(vertQuery)
       }
   

}
