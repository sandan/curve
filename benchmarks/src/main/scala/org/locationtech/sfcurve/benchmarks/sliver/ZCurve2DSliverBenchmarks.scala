package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param

import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve2DSliverBenchmark extends BenchmarkRunner(classOf[ZCurve2DSliverBenchmark])
class ZCurve2DSliverBenchmark extends CurveBenchmark {
 
  @Param  
  val res = 1 //max res
  val sfc = new ZCurve2D(Math.pow(2,res).toInt)


  val x = 180
  val y = 90
  val v_x =( 0.1 * 360 )
  val v_y =( 0.8 * 180 )

    /* Vertical sliver range queries 
     * ~10% of total dim length horizontal, ~80% of total dim length vertical
     */
//curve 
    def timeZCurve2DLL(reps: Int) = run(reps)(ZCurve2DLL)
    def ZCurve2DLL = {
            sfc.toRanges(-x, -v_y/2, -x + v_x  , v_y/2)
    }
  
    def timeZCurve2DLR(reps: Int) = run(reps)(ZCurve2DLR)
    def ZCurve2DLR = {
            sfc.toRanges(-x + 90, -v_y/2, -x + 90 + v_x, v_y/2)
    }
  
 
    def timeZCurve2DRL(reps: Int) = run(reps)(ZCurve2DRL)
    def ZCurve2DRL = {
            sfc.toRanges(x - 90, -v_y/2, (x - 90) + v_x  , v_y/2)
    }
  
    def timeZCurve2DRR(reps: Int) = run(reps)(ZCurve2DRR)
    def ZCurve2DRR = {
            sfc.toRanges(x - v_x, -v_y/2, x, v_y/2)
    }

    val v_s =( 0.8 * 360 )
    val v_t =( 0.1 * 180 )

    /* Horizontal sliver range queries 
     * ~10% of total dim length vertical, ~80% of total dim length horizontal
     */

    def timeZCurve2DHLL(reps: Int) = run(reps)(ZCurve2DHLL)
    def ZCurve2DHLL = {
            sfc.toRanges(-v_s/2, -y, v_s/2, -y + v_t)
    }
  
    def timeZCurve2DHLR(reps: Int) = run(reps)(ZCurve2DHLR)
    def ZCurve2DHLR = {
            sfc.toRanges(-v_s/2, -y + 45, v_s/2, -y + 45 + v_t)
    }
  
 
    def timeZCurve2DHRL(reps: Int) = run(reps)(ZCurve2DHRL)
    def ZCurve2DHRL = {
            sfc.toRanges(-v_s/2, y - 45, v_s/2, (y - 45) + v_t)
    }
  
    def timeZCurve2DHRR(reps: Int) = run(reps)(ZCurve2DHRR)
    def ZCurve2DHRR = {
            sfc.toRanges(-v_s/2, y - v_t, v_s/2, y)
    }

    //sfseize 
    val sf2D = new ComposedCurve(
                   new ZCurve(OrdinalVector(res,res)),
                   Seq(DefaultDimensions.createLongitude(res), DefaultDimensions.createLatitude(res)))

    /** Vertical queries similar to above**/
    val vertQueryLL = Cell(Seq(
     DefaultDimensions.createDimension("x", -x, -x + v_x , 0),
     DefaultDimensions.createDimension("y", -v_y/2, v_y/2 , 0)
    ))

    val vertQueryLR = Cell(Seq(
     DefaultDimensions.createDimension("x", -x + 90, -x + 90 + v_x , 0),
     DefaultDimensions.createDimension("y", -v_y/2, v_y/2 , 0)
    ))

    val vertQueryRL = Cell(Seq(
     DefaultDimensions.createDimension("x", x - 90, (x - 90) + v_x , 0),
     DefaultDimensions.createDimension("y", -v_y/2, v_y/2 , 0)
    ))

    val vertQueryRR = Cell(Seq(
     DefaultDimensions.createDimension("x", x - v_x, x , 0),
     DefaultDimensions.createDimension("y", -v_y/2, v_y/2 , 0)
    ))


    def timeZCurve2DSFLL(reps: Int) = run(reps)(ZCurve2DSFLL)
    def ZCurve2DSFLL = {
          sf2D.getRangesCoveringCell(vertQueryLL)
    }

    def timeZCurve2DSFLR(reps: Int) = run(reps)(ZCurve2DSFLR)
    def ZCurve2DSFLR = {
          sf2D.getRangesCoveringCell(vertQueryLR)
    }

    def timeZCurve2DSFRL(reps: Int) = run(reps)(ZCurve2DSFRL)
    def ZCurve2DSFRL = {
          sf2D.getRangesCoveringCell(vertQueryRL)
    }

    def timeZCurve2DSFRR(reps: Int) = run(reps)(ZCurve2DSFRR)
    def ZCurve2DSFRR = {
          sf2D.getRangesCoveringCell(vertQueryRR)
    }

    /** Horizontal queries **/

    val horizQueryLL = Cell(Seq(
     DefaultDimensions.createDimension("x", -v_s/2, v_s/2 , 0),
     DefaultDimensions.createDimension("y", -y, -y + v_t , 0)
    ))

    val horizQueryLR = Cell(Seq(
     DefaultDimensions.createDimension("x", -v_s/2, v_s/2 , 0),
     DefaultDimensions.createDimension("y", -y + 45, -y + 45 + v_t , 0)
    ))

    val horizQueryRL = Cell(Seq(
     DefaultDimensions.createDimension("x", -v_s/2, v_s/2 , 0),
     DefaultDimensions.createDimension("y", y - 45, (y - 45) + v_t , 0)
    ))

    val horizQueryRR = Cell(Seq(
     DefaultDimensions.createDimension("x", -v_s/2, v_s/2 , 0),
     DefaultDimensions.createDimension("y", y - v_t, y, 0)
    ))

    def timeZCurve2DSFHLL(reps: Int) = run(reps)(ZCurve2DSFHLL)
    def ZCurve2DSFHLL = {
          sf2D.getRangesCoveringCell(horizQueryLL)
    }

    def timeZCurve2DSFHLR(reps: Int) = run(reps)(ZCurve2DSFHLR)
    def ZCurve2DSFHLR = {
          sf2D.getRangesCoveringCell(horizQueryLR)
    }

    def timeZCurve2DSFHRL(reps: Int) = run(reps)(ZCurve2DSFHRL)
    def ZCurve2DSFHRL = {
          sf2D.getRangesCoveringCell(horizQueryRL)
    }

    def timeZCurve2DSFHRR(reps: Int) = run(reps)(ZCurve2DSFHRR)
    def ZCurve2DSFHRR = {
          sf2D.getRangesCoveringCell(horizQueryRR)
    }
}
