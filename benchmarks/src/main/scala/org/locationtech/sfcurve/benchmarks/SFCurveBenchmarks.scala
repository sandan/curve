package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import org.locationtech.sfcurve.rowmajor._
import org.locationtech.sfcurve.hilbert._

import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.CompactHilbertCurve
import org.eichelberger.sfc.RowMajorCurve
import org.eichelberger.sfc.SpaceFillingCurve.{OrdinalVector, SpaceFillingCurve}

import com.google.caliper.Param

object SFCurveBenchmarks extends BenchmarkRunner(classOf[SFCurveBenchmarks])
class SFCurveBenchmarks extends CurveBenchmark {

  val resolution = 128

  //time creation for sfcurves

  def timeZ2DCreate(reps: Int) = run(reps)(z2DCreation)
  def z2DCreation = {
      for (res <- 1 until resolution){
        new ZCurve2D(res)
      }
  }

  def timeHilbert2DCreate(reps: Int) = run(reps)(h2DCreation)
  def h2DCreation = {
      for (res <- 1 until resolution){
        new HilbertCurve2D(res)
      }
  }

  def timeRowMajor2DCreate(reps: Int) = run(reps)(rm2DCreation)
  def rm2DCreation = {
      for (res <- 1 until resolution){
        new RowMajorCurve2D(res)
      }
  }

  //time creation for sfseize

  def timeZ2DSFCreate(reps: Int) = run(reps)(z2DSFCreation)
  def z2DSFCreation = {
      for (res <- 1 until resolution){
        new ZCurve(OrdinalVector(res,res))
      }
  }

  def timeHilbert2DSFCreate(reps: Int) = run(reps)(h2DSFCreation)
  def h2DSFCreation = {
      for (res <- 1 until resolution){
        new CompactHilbertCurve(OrdinalVector(res,res))
      }
  }

  def timeRowMajor2DSFCreate(reps: Int) = run(reps)(rm2DSFCreation)
  def rm2DSFCreation = {
      for (res <- 1 until resolution){
        new RowMajorCurve(OrdinalVector(res,res))
      }
  }

}
