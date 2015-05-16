package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param


object ZCurve2DBenchmark extends BenchmarkRunner(classOf[ZCurve2DBenchmark])

class ZCurve2DBenchmark extends CurveBenchmark {


  def timeZCurve2DBadCase(reps: Int) = run(reps)(ZCurve2DBadCase)
  def ZCurve2DBadCase = {

      var i = 2 //resolution bits
      while (i < 20){
          val sfc = new ZCurve2D(i)
          val range = sfc.toRanges(-178.123456, -86.398493, 179.3211113, 87.393483)
          i += 1
      }

  }

  def timeZCurve2DCityCase(reps: Int) = run(reps)(ZCurve2DCityCase)
  def ZCurve2DCityCase = {
      var i = 10
      var lx = -180
      var ly = 89.68103
      var ux = -179.597625
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 24){
          val sfc = new ZCurve2D(i)
          while((yrun + ly) > -90){
              xrun = 0
              while ((xrun + ux) < 180){
                  val range = sfc.toRanges(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 5
              }
              yrun -= 5
          }
          i += 1
      }
  }


def timeZCurve2DStateCase(reps: Int) = run(reps)(ZCurve2DStateCase)
  def ZCurve2DStateCase = {
      var i = 6
      var lx = -180
      var ly = 86.022914
      var ux = -173.078613
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 24){
          val sfc = new ZCurve2D(i)
          while ((yrun + ly) > -90){
              xrun = 0
              while((xrun + ux) < 180){
                  val range = sfc.toRanges(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 5
              }
              yrun -= 5
          }

          i += 1
      }
  }

  def timeZCurve2DCountryCase(reps: Int) = run(reps)(ZCurve2DCountryCase)
  def ZCurve2DCountryCase = {
      var i = 6
      var lx = -180
      var ly = 82.689749
      var ux = -171.408692
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 24){
          val sfc = new ZCurve2D(i)
          while ((yrun + ly) > -90){
              xrun = 0
              while((xrun + ux) < 180){
                  val range = sfc.toRanges(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 5
              }
              yrun -= 5
          }

          i += 1
      }
  }
}
