package sfc
/**
 * Immutable NormalizedCoordinatWGS84 of Coordinate
 */
object NormalizedCoordinateWGS84 {
  def apply(lonNormal: Long, latNormal: Long, precision: Long) {
    new NormalizedCoordinateWGS84(setNormalizedLongitude(lonNormal, precision), 
                                  setNormalizedLatitude(latNormal, precision), 
                                  precision)
  }
  def apply(point: Array[Long], precision: Long) {
    apply(point(0), point(1), precision)
  }
  private def setNormalizedLatitude(latNormal: Long, precision: Long) = {
    require(latNormal >= 0 && latNormal <= precision,
      throw new NumberFormatException(
        "Normalized latitude must be greater than 0 and less than the maximum precision"))
    latNormal * 180d / (precision - 1)
  }
  private def setNormalizedLongitude(lonNormal: Long, precision: Long) = {
    require(lonNormal >= 0 && lonNormal <= precision,
      throw new NumberFormatException(
        "Normalized longitude must be greater than 0 and less than the maximum precision"))
    lonNormal * 360d / (precision - 1)
  }
  private def normalizeLatitude(latitude: Double, precision: Long): Long = {
    (latitude * (precision - 1) / 180d).toLong
  }
  private def normalizeLongitude(longitude: Double, precision: Long): Long = {
    (longitude * (precision - 1) / 360d).toLong
  }
}

class NormalizedCoordinateWGS84(longitude: Double, latitude: Double, precision: Long) extends Coordinate {
  def getLatitude() {
    latitude - 90
  }
  def getLongitude() {
    longitude - 180
  }
  
  def getNormalLatitude(prec: Long){
    NormalizedCoordinateWGS84.normalizeLatitude(latitude, prec)
  }
  
  def getNormalLongitude(prec: Long){
    NormalizedCoordinateWGS84.normalizeLongitude(longitude, prec)
  }
}

