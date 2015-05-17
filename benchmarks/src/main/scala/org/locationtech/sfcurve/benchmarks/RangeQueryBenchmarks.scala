package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import org.locationtech.sfcurve.rowmajor._
import org.locationtech.sfcurve.hilbert._

import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.CompactHilbertCurve
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve._

import com.google.caliper.Param

object RangeQueryBenchmarks extends BenchmarkRunner(classOf[RangeQueryBenchmarks])
class RangeQueryBenchmarks extends CurveBenchmark {

  val resolution = 8

  val xbound = 90  
  val ybound = 45
  val factor = 2.0

  //sfc
  val z2D = new ZCurve2D(Math.pow(2,resolution).toInt)
  val h2D = new HilbertCurve2D(resolution)
  val rm2D = new RowMajorCurve2D(Math.pow(2, resolution).toInt)

  //sfseize
  val sfz2D = new ZCurve(OrdinalVector(resolution,resolution))
  val sfh2D = new CompactHilbertCurve(OrdinalVector(resolution, resolution))
  val sfrm2D = new RowMajorCurve(OrdinalVector(resolution, resolution))

  //square centered range queries

  def timeZ2DRangeQuery(reps: Int) = run(reps)(z2DRangeQuery)
  def z2DRangeQuery = {
      for(x <- 1 until ybound;
           y <- 1 until ybound){
        z2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }
  }

  def timeHilbert2DRangeQuery(reps: Int) = run(reps)(h2DRangeQuery)
  def h2DRangeQuery = {
      for(x <- 1 until ybound;
           y <- 1 until ybound){
        h2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }
  }

  def timeRowMajor2DRangeQuery(reps: Int) = run(reps)(rm2DRangeQuery)
  def rm2DRangeQuery = {
      for(x <- 1 until ybound;
           y <- 1 until ybound){
        rm2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }

  }

  //time creation for sfseize

  def timeSFQuery(reps: Int) = run(reps)(SFQuery)
  def SFQuery = {

      for(x <- 1 until ybound;
           y <- 1 until ybound){
          Query(Seq(
            OrdinalRanges(OrdinalPair(x,y)),
            OrdinalRanges(OrdinalPair(x,y))))
      }

  }
/** TODO: find out how to specify lat/long doubles to range queries in sfseize
  def timeZ2DSFRangeQuery(reps: Int) = run(reps)(z2DSFRangeQuery)
  def z2DSFRangeQuery = {

      for(x <- 1 until ybound;
           y <- 1 until ybound){
        sfz2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }

  }

  def timeHilbert2DSFRangeQuery(reps: Int) = run(reps)(h2DSFRangeQuery)
  def h2DSFRangeQuery = {

      for(x <- 1 until ybound;
           y <- 1 until ybound){
        sfh2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }

  }

  def timeRowMajor2DSFRangeQuery(reps: Int) = run(reps)(rm2DSFRangeQuery)
  def rm2DSFRangeQuery = {
      for(x <- 1 until ybound;
           y <- 1 until ybound){
        sfrm2D.toRanges(-factor * x ,-factor * y, factor * x, factor * y) 
      }

  }

**/
}
