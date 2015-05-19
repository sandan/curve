package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import org.locationtech.sfcurve.rowmajor._
import org.locationtech.sfcurve.hilbert._

import org.eichelberger.sfc._
import org.eichelberger.sfc.Dimensions
import org.eichelberger.sfc.SpaceFillingCurve._

import com.google.caliper.Param

/**
 *  Benchmark timing creation of curves given a resolution
 */
object SFCurveBenchmarks extends BenchmarkRunner(classOf[SFCurveBenchmarks])
class SFCurveBenchmarks extends CurveBenchmark {

  @Param
  val res = 10

  //time creation for sfcurves
  def timeZ2DCreate(reps: Int) = run(reps)(z2DCreation)
  def z2DCreation = {
        new ZCurve2D(Math.pow(2,res).toInt)
  }

  def timeHilbert2DCreate(reps: Int) = run(reps)(h2DCreation)
  def h2DCreation = {
      new HilbertCurve2D(res)
  }

  def timeRowMajor2DCreate(reps: Int) = run(reps)(rm2DCreation)
  def rm2DCreation = {
        new RowMajorCurve2D(Math.pow(2,res).toInt)
  }

  //time creation for sfseize

  def timeZ2DSFCreate(reps: Int) = run(reps)(z2DSFCreation)
  def z2DSFCreation = {
        new ZCurve(OrdinalVector(res,res))
  }

  def timeHilbert2DSFCreate(reps: Int) = run(reps)(h2DSFCreation)
  def h2DSFCreation = {
        new CompactHilbertCurve(OrdinalVector(res,res))
  }

  def timeRowMajor2DSFCreate(reps: Int) = run(reps)(rm2DSFCreation)
  def rm2DSFCreation = {
        new RowMajorCurve(OrdinalVector(res,res))
  }

  def timeRowMajor2DSFComposedCreate(reps: Int) = run(reps)(rm2DSFComposedCreation)
  def rm2DSFComposedCreation = {
        new ComposedCurve(
        new RowMajorCurve(OrdinalVector(res,res)),
        Seq(
         DefaultDimensions.createLongitude(res),
         DefaultDimensions.createLatitude(res)
        )
       )
  }

  def timeZ2DSFComposedCreate(reps: Int) = run(reps)(z2DSFComposedCreation)
  def z2DSFComposedCreation = {
        new ComposedCurve(
        new ZCurve(OrdinalVector(res,res)),
        Seq(
         DefaultDimensions.createLongitude(res),
         DefaultDimensions.createLatitude(res)
        )
       )
  }

  def timeHilbert2DSFComposedCreate(reps: Int) = run(reps)(h2DSFComposedCreation)
  def h2DSFComposedCreation = {
        new ComposedCurve(
        new CompactHilbertCurve(OrdinalVector(res,res)),
        Seq(
         DefaultDimensions.createLongitude(res),
         DefaultDimensions.createLatitude(res)
        )
       )
  }

}
