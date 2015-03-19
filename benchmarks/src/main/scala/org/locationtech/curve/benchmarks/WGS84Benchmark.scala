package sfc



import com.google.caliper.Param

object WGS84Benchmark extends BenchmarkRunner(classOf[WGS84Benchmark])

class WGS84Benchmark extends CurveBenchmark {


  /*def timeWGS84IndexCreate(reps: Int) = run(reps)(WGS84IndexCreation)
  def WGS84IndexCreation = {
    var x = -180
    var y = -90

    while(x < 180) {
      while(y < 90) {
        HilbertCurve(WGS84Coordinate(x),WGS84Coordinate(y))
        y += 1
      }
      x += 1
    }
}*/

  def timeWGS84BadCase(reps: Int) = run(reps)(WGS84BadCase)
  def WGS84BadCase = {

      val i = 2 //resolution bits
      while (i < 24){
          val sfc = HilbertCurve(i)
          val range = sfc.RangeQuery(WGS84Coordinate(-178.123456, -86.398493), WGSCoordinate(179.3211113, 87.393483))
          ++i
      }

  }

  //Are we doing the error on tiling vs. coordinates?

}
