package org.locationtech.sfc

/**
 * CoordinateWGS84 implementation of Coordinate.
 */
object CoordinateWGS84 {
  
  //main constructor in chain
  def apply(longitude: Double, latitude: Double): CoordinateWGS84 = {
      
  require((90 >= latitude &&  latitude >= -90 ), 
      throw new NumberFormatException(
          "Latitude must be in the WGS84 CRS - (90 >= latitude >= -90) "))
  require((180 >= longitude &&  longitude >= -180 ), 
      throw new NumberFormatException(
          "Longitude must be in the WGS84 CRS - (180 >= longitude >= -180) "))
  
    new CoordinateWGS84(longitude.toDouble + 180, latitude.toDouble + 90)
  }
  
  def apply(lonNormal: Long, latNormal: Long, precision: Long) {
   new CoordinateWGS84(setNormalizedLongitude(lonNormal, precision), 
                                  setNormalizedLatitude(latNormal, precision))

  }
  def apply(point: Array[Long], precision: Long) {
   new CoordinateWGS84(setNormalizedLongitude(point(0), precision), 
                                  setNormalizedLatitude(point(1), precision))

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

  private def setLatitude(lat: Double): Double = {
    require ((90 <= lat && lat >= -90),
        throw new NumberFormatException(
          "Latitude must be in the WGS84 CRS - (90 >= latitude >= -90) ") )
    lat + 90
  }
  
  private def setLongitude(lon: Double): Double = {
      require((180 >= lon &&  lon >= -180 ), 
      throw new NumberFormatException(
          "Longitude must be in the WGS84 CRS - (180 >= longitude >= -180) "))
      lon + 180
  }
}
  
  /**
   *  The Coordinate class
   */
class CoordinateWGS84(longitude: Double, latitude: Double) extends Coordinate {

  def getLatitude(){
    latitude - 90
  }
  
  def getLongitude(){
    longitude - 180
  }
  def getNormalLatitude(prec: Long){
    CoordinateWGS84.normalizeLatitude(latitude, prec)
  }
  
  def getNormalLongitude(prec: Long){
    CoordinateWGS84.normalizeLongitude(longitude, prec)
  }
}
