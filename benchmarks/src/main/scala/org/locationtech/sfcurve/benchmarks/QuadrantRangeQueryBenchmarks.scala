package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import org.locationtech.sfcurve.rowmajor._
import org.locationtech.sfcurve.hilbert._

import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.CompactHilbertCurve
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve._

import com.google.caliper.Param

object QuadrantRangeQueryBenchmarks extends BenchmarkRunner(classOf[QuadrantRangeQueryBenchmarks])
class QuadrantRangeQueryBenchmarks extends CurveBenchmark {

  val resolution = 8

  val xbound = 180  
  val ybound = 90
  val factor = 2.0

  //sfc
  val z2D = new ZCurve2D(Math.pow(2,resolution).toInt)
  val h2D = new HilbertCurve2D(resolution)
  val rm2D = new RowMajorCurve2D(Math.pow(2, resolution).toInt)

  //sfseize
  val sfz2D = new ZCurve(OrdinalVector(resolution,resolution))
  val sfh2D = new CompactHilbertCurve(OrdinalVector(resolution, resolution))
  val sfrm2D = new RowMajorCurve(OrdinalVector(resolution, resolution))

  //quadrant range queries

  //upper right
  def timeZ2DRangeQueryUR(reps: Int) = run(reps)(z2DRangeQueryUR)
  def z2DRangeQueryUR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        z2D.toRanges(0,0, x, y) 
      }
  }

  def timeHilbert2DRangeQueryUR(reps: Int) = run(reps)(h2DRangeQueryUR)
  def h2DRangeQueryUR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        h2D.toRanges(0,0, x, y) 
      }
  }

  def timeRowMajor2DRangeQueryUR(reps: Int) = run(reps)(rm2DRangeQueryUR)
  def rm2DRangeQueryUR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        rm2D.toRanges(0 ,0, x, y) 
      }

  }

  //upper left

  def timeZ2DRangeQueryUL(reps: Int) = run(reps)(z2DRangeQueryUL)
  def z2DRangeQueryUL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        z2D.toRanges(-x,0, 0, y) 
      }
  }

  def timeHilbert2DRangeQueryUL(reps: Int) = run(reps)(h2DRangeQueryUL)
  def h2DRangeQueryUL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        h2D.toRanges(-x,0, 0, y) 
      }
  }

  def timeRowMajor2DRangeQueryUL(reps: Int) = run(reps)(rm2DRangeQueryUL)
  def rm2DRangeQueryUL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        rm2D.toRanges(-x ,0, 0, y) 
      }

  }

  //lower right

  def timeZ2DRangeQueryLR(reps: Int) = run(reps)(z2DRangeQueryLR)
  def z2DRangeQueryLR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        z2D.toRanges(0,-y, x, 0) 
      }
  }

  def timeHilbert2DRangeQueryLR(reps: Int) = run(reps)(h2DRangeQueryLR)
  def h2DRangeQueryLR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        h2D.toRanges(0,-y, x, 0) 
      }
  }

  def timeRowMajor2DRangeQueryLR(reps: Int) = run(reps)(rm2DRangeQueryLR)
  def rm2DRangeQueryLR = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        rm2D.toRanges(0 ,-y, x, 0) 
      }

  }

  //lower left

  def timeZ2DRangeQueryLL(reps: Int) = run(reps)(z2DRangeQueryLL)
  def z2DRangeQueryLL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        z2D.toRanges(-x,-y, 0, 0) 
      }
  }

  def timeHilbert2DRangeQueryLL(reps: Int) = run(reps)(h2DRangeQueryLL)
  def h2DRangeQueryLL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        h2D.toRanges(-x,-y, 0, 0) 
      }
  }

  def timeRowMajor2DRangeQueryLL(reps: Int) = run(reps)(rm2DRangeQueryLL)
  def rm2DRangeQueryLL = {
      for(x <- 1 until xbound;
           y <- 1 until ybound){
        rm2D.toRanges(-x ,-y, 0, 0) 
      }

  }

   //sfseize quadrant range queries
/** TODO: find out how to specify lat/long doubles to range queries in sfseize
  def timeZ2DSFRangeQuery(reps: Int) = run(reps)(z2DSFRangeQuery)
  def z2DSFRangeQuery = {

      for(x <- 1 until ybound;
           y <- 1 until ybound){
      }

  }

  def timeHilbert2DSFRangeQuery(reps: Int) = run(reps)(h2DSFRangeQuery)
  def h2DSFRangeQuery = {

      for(x <- 1 until ybound;
           y <- 1 until ybound){
      }

  }

  def timeRowMajor2DSFRangeQuery(reps: Int) = run(reps)(rm2DSFRangeQuery)
  def rm2DSFRangeQuery = {
      for(x <- 1 until ybound;
           y <- 1 until ybound){
      }

  }

**/
}
