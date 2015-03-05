package org.locationtech.curve

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.uzaygezen.core.BacktrackingQueryBuilder;
import com.google.uzaygezen.core.BitVector;
import com.google.uzaygezen.core.BitVectorFactories;
import com.google.uzaygezen.core.CompactHilbertCurve;
import com.google.uzaygezen.core.FilteredIndexRange;
import com.google.uzaygezen.core.LongContent;
import com.google.uzaygezen.core.PlainFilterCombiner;
import com.google.uzaygezen.core.Query;
import com.google.uzaygezen.core.QueryBuilder;
import com.google.uzaygezen.core.RegionInspector;
import com.google.uzaygezen.core.SimpleRegionInspector;
import com.google.uzaygezen.core.ZoomingSpaceVisitorAdapter;
import com.google.uzaygezen.core.ranges.LongRange;
import com.google.uzaygezen.core.ranges.LongRangeHome;

object HilbertCurve{

 def apply(): HilbertCurve = {
  new HilbertCurve(18)
 }

 def apply(bitsPerDim: Int) : HilbertCurve = {
   new HilbertCurve(bitsPerDim) 
 }
 
}

class HilbertCurve(bitsPerDimension: Int)  extends SpaceFillingCurve {
  lazy val precision = math.pow(2, bitsPerDimension).toLong
  lazy val chc: CompactHilbertCurve = new CompactHilbertCurve( Array(bitsPerDimension, bitsPerDimension))
  
  def PointToValue(pt: CoordinateWGS84): Long = {
    PointToHilbert(pt)
  }

  def ValueToPoint(value: Long): CoordinateWGS84 = {
    HilbertToPoint(value)
  }
 
  def RangeQuery(lowerLeft: CoordinateWGS84, upperRight: CoordinateWGS84){
    rangeQuery(lowerLeft, upperRight)
  }

  private def PointToHilbert(point: CoordinateWGS84): Long = {
    var p = new Array[BitVector](2)
    for { i <- 0 to 1 } yield {
       p(i) = BitVectorFactories.OPTIMAL.apply(bitsPerDimension)
    }

    var hilbert = BitVectorFactories.OPTIMAL.apply(bitsPerDimension * 2)
    p(0).copyFrom(point.getNormalLongitude(precision))
    p(1).copyFrom(point.getNormalLatitude(precision))

    chc.index(p,0,hilbert)
    hilbert.toLong()
  }
  private def HilbertToPoint(hilbertValue: Long): CoordinateWGS84 = {
    var h = BitVectorFactories.OPTIMAL.apply(bitsPerDimension*2)
    h.copyFrom(hilbertValue)
    var p = new Array[BitVector](2) 
    for { i <- 0 to 1 } yield{
      p(i) = BitVectorFactories.OPTIMAL.apply(bitsPerDimension)
    }
    chc.indexInverse(h,p)
    CoordinateWGS84(p(0).toLong, p(1).toLong, precision)
  }

  private def rangeQuery(min: CoordinateWGS84, max: CoordinateWGS84): Array[Array[Long]] = {
    
  } 
}
