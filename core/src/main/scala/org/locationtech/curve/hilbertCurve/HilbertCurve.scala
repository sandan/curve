package org.locationtech.curve.hilbertCurve

import com.google.common.base.Functions;
import com.google.common.base.Function;
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

import org.locationtech.curve.interface.SpaceFillingCurve;

object HilbertCurve{

 def apply(): HilbertCurve = new HilbertCurve(18)
 def apply(bitsPerDim: Int) : HilbertCurve =  new HilbertCurve(bitsPerDim)

}

class HilbertCurve(bitsPerDimension: Int) extends SpaceFillingCurve {
  lazy val precision = math.pow(2, bitsPerDimension).toLong
  lazy val chc = new CompactHilbertCurve(Array(bitsPerDimension, bitsPerDimension))

  def PointToValue(x: Double, y: Double): Long = {
    val pt = CoordinateWGS84(x,y)
    var p = new Array[BitVector](2)

    for { i <- 0 to 1 } yield {
       p(i) = BitVectorFactories.OPTIMAL.apply(bitsPerDimension)
    }

    var hilbert = BitVectorFactories.OPTIMAL.apply(bitsPerDimension * 2)

    p(0).copyFrom(pt.getNormalLongitude(precision))
    p(1).copyFrom(pt.getNormalLatitude(precision))

    chc.index(p,0,hilbert)
    hilbert.toLong()
  }

  def ValueToPoint(value: Long): (Double, Double) = {

    var h = BitVectorFactories.OPTIMAL.apply(bitsPerDimension*2)
    h.copyFrom(value)
    var p = new Array[BitVector](2)

    for { i <- 0 to 1 } yield{
      p(i) = BitVectorFactories.OPTIMAL.apply(bitsPerDimension)
    }

    chc.indexInverse(h,p)
    val w = CoordinateWGS84(p(0).toLong, p(1).toLong, precision)
    (w.getLatitude(), w.getLongitude())
  }

  def RangeQuery(ll_x: Double, ll_y: Double, ur_x: Double, ur_y: Double): List[Array[Long]] = {
    val min = CoordinateWGS84(ll_x,ll_y)
    val max = CoordinateWGS84(ur_x, ur_y)

    var chc = new CompactHilbertCurve( Array[Int](bitsPerDimension, bitsPerDimension) )
    val maxRanges = Int.MaxValue
    var region = new java.util.ArrayList[LongRange]()

    region.add(LongRange.of(min.getNormalLongitude(precision), max.getNormalLongitude(precision)))

    region.add(LongRange.of(min.getNormalLatitude(precision), max.getNormalLatitude(precision)))

    var zero = new LongContent(0L)

    var LR_id: Function[LongRange, LongRange] = Functions.identity();

    var inspector: RegionInspector[LongRange, LongContent] = SimpleRegionInspector.create(ImmutableList.of(region), new LongContent(1L), LR_id, LongRangeHome.INSTANCE, zero)

    var combiner: PlainFilterCombiner[LongRange, java.lang.Long, LongContent, LongRange] = new PlainFilterCombiner[LongRange, java.lang.Long, LongContent, LongRange](LongRange.of(0, 1))

    var queryBuilder: QueryBuilder[LongRange, LongRange] = BacktrackingQueryBuilder.create(inspector, combiner, maxRanges, true, LongRangeHome.INSTANCE, zero)

    chc.accept(new ZoomingSpaceVisitorAdapter(chc, queryBuilder))

    var query: Query[LongRange, LongRange] = queryBuilder.get()

    var ranges: java.util.List[FilteredIndexRange[LongRange, LongRange]] = query.getFilteredIndexRanges()

    var ranges2: List[Array[Long]] = List[Array[Long]]()
    val itr = ranges.iterator

    while(itr.hasNext()) {
       var l = itr.next()
       ranges2 = Array[Long](l.getIndexRange().getStart(), l.getIndexRange().getEnd()) :: ranges2
    }
    ranges2
  }
}
