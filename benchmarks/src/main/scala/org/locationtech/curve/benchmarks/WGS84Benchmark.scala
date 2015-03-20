package org.locationtech.curve.benchmarks

import org.locationtech.curve.hilbertCurve._
import com.google.caliper.Param


object WGS84Benchmark extends BenchmarkRunner(classOf[WGS84Benchmark])

class WGS84Benchmark extends CurveBenchmark {


  def timeWGS84BadCase(reps: Int) = run(reps)(WGS84BadCase)
  def WGS84BadCase = {

      var i = 2 //resolution bits
      while (i < 24){
          val sfc = HilbertCurve(i)
          val range = sfc.RangeQuery(-178.123456, -86.398493, 179.3211113, 87.393483)
          i += 1
      }

  }

  def timeWGS84CityCase(reps: Int) = run(reps)(WGS84CityCase)
  def WGS84CityCase = {
      var i = 2
      var lx = -180
      var ly = 89.68103
      var ux = -179.597625
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 10){
          val sfc = HilbertCurve(i)
          while((yrun + ly) > -90){
              xrun = 0
              while ((xrun + ux) < 180){
                  val range = sfc.RangeQuery(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 10
              }
              yrun -= 5
          }

          i += 1
      }
  }

  def timeWGS84StateCase(reps: Int) = run(reps)(WGS84StateCase)
  def WGS84StateCase = {
      var i = 2
      var lx = -180
      var ly = 86.022914
      var ux = -173.078613
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 10){
          val sfc = HilbertCurve(i)
          while ((yrun + ly) > -90){
              xrun = 0
              while((xrun + ux) < 180){
                  val range = sfc.RangeQuery(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 10
              }
              yrun -= 5
          }

          i += 1
      }
  }

  def timeWGS84CountryCase(reps: Int) = run(reps)(WGS84CountryCase)
  def WGS84CountryCase = {
      var i = 2
      var lx = -180
      var ly = 82.689749
      var ux = -171.408692
      var uy = 90
      var yrun = 0
      var xrun = 0

      while (i < 10){
          val sfc = HilbertCurve(i)
          while ((yrun + ly) > -90){
              xrun = 0
              while((xrun + ux) < 180){
                  val range = sfc.RangeQuery(lx+xrun, ly+yrun, ux+xrun, uy+yrun)
                  xrun += 10
              }
              yrun -= 5
          }

          i += 1
      }
  }
  //Are we doing the error on tiling vs. coordinates?

}
