package org.locationtech.sfc
/**
 * Interface for space filling curves. A first approximation.
 **/
trait SpaceFillingCurve {
  def PointToValue(x: Double, y: Double): Long
  def ValueToPoint(value: Long): (Double, Double)
  def RangeQuery(ll_x: Double, ll_y: Double, ur_x: Double, ur_y: Double)
}
