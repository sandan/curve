package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.rowmajor._
import com.google.caliper.Param

import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions._
import org.eichelberger.sfc.SpaceFillingCurve._

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */

object RowMajorCurve2DSliverBenchmark extends BenchmarkRunner(classOf[RowMajorCurve2DSliverBenchmark])
class RowMajorCurve2DSliverBenchmark extends CurveBenchmark {

  @Param  
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

    //sfseize 
    val sf2D = new ComposedCurve(
                   new RowMajorCurve(OrdinalVector(res,res)),
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


    def timeRowMajorCurve2DSFLL(reps: Int) = run(reps)(RowMajorCurve2DSFLL)
    def RowMajorCurve2DSFLL = {
          sf2D.getRangesCoveringCell(vertQueryLL)
    }

    def timeRowMajorCurve2DSFLR(reps: Int) = run(reps)(RowMajorCurve2DSFLR)
    def RowMajorCurve2DSFLR = {
          sf2D.getRangesCoveringCell(vertQueryLR)
    }

    def timeRowMajorCurve2DSFRL(reps: Int) = run(reps)(RowMajorCurve2DSFRL)
    def RowMajorCurve2DSFRL = {
          sf2D.getRangesCoveringCell(vertQueryRL)
    }

    def timeRowMajorCurve2DSFRR(reps: Int) = run(reps)(RowMajorCurve2DSFRR)
    def RowMajorCurve2DSFRR = {
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

    def timeRowMajorCurve2DSFHLL(reps: Int) = run(reps)(RowMajorCurve2DSFHLL)
    def RowMajorCurve2DSFHLL = {
          sf2D.getRangesCoveringCell(horizQueryLL)
    }

    def timeRowMajorCurve2DSFHLR(reps: Int) = run(reps)(RowMajorCurve2DSFHLR)
    def RowMajorCurve2DSFHLR = {
          sf2D.getRangesCoveringCell(horizQueryLR)
    }

    def timeRowMajorCurve2DSFHRL(reps: Int) = run(reps)(RowMajorCurve2DSFHRL)
    def RowMajorCurve2DSFHRL = {
          sf2D.getRangesCoveringCell(horizQueryRL)
    }

    def timeRowMajorCurve2DSFHRR(reps: Int) = run(reps)(RowMajorCurve2DSFHRR)
    def RowMajorCurve2DSFHRR = {
          sf2D.getRangesCoveringCell(horizQueryRR)
    }
}
