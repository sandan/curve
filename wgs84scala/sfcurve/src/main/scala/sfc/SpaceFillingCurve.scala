package sfc
trait SpaceFillingCurve {
  def PointToValue(point: Coordinate): Long
  
  def ValueToPoint(value: Long): Coordinate
  
  def RangeQuery(lowerLeft: Coordinate, upperRight: Coordinate)
}
