package sfc

/**
 * Immutable CoordinateWGS84 implementation of Coordinate.
 */
object CoordinateWGS84 {
  
  //main constructor in chain
  def apply(longitude: Double, latitude: Double, precision: Long): CoordinateWGS84 = {
      
  require((90 >= latitude &&  latitude >= -90 ), 
      throw new NumberFormatException(
          "Latitude must be in the WGS84 CRS - (90 >= latitude >= -90) "))
  require((180 >= longitude &&  longitude >= -180 ), 
      throw new NumberFormatException(
          "Longitude must be in the WGS84 CRS - (180 >= longitude >= -180) "))
  
    new CoordinateWGS84(longitude.toDouble + 180, latitude.toDouble + 90, 0)
  }
  
  def apply(longitude: Double, latitude: Double): CoordinateWGS84 = {
   
    /** TODO: What should default precision be? **/
    apply(setLongitude(longitude), setLatitude(latitude), 0)
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
class CoordinateWGS84(longitude: Double, latitude: Double, precision: Double) extends Coordinate {
  def getLatitude(){
    latitude - 90
  }
  
  def getLongitude(){
    longitude - 180
  }
}
