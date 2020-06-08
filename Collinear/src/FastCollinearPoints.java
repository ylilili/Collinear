import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final LineSegment[] segments;
	
	public FastCollinearPoints(Point[] points) {
		validate_null(points);
		Point[] points_copy = points.clone();
		Arrays.sort(points_copy);
		validate_duplicate(points_copy);
		//remove(points_copy);
		
		List<LineSegment> auxilliary_segments = new LinkedList<>();
		//List<Point[]> collinearPointsSet = new LinkedList<>();
		
		B: for(int p = 0; p < points_copy.length; p++) {    // set every point as original point p in points
			if(points_copy.length < 4)
				break;
			Arrays.sort(points_copy);
			Point pointP = points_copy[p];
			
			Arrays.sort(points_copy, pointP.slopeOrder());    // sorting points in ascending order of slop

			LinkedList<Point> collinearPoints = new LinkedList<>();
			collinearPoints.add(points_copy[0]);
			
			int n = 0;
			
			A: for(int refer = 1; refer < points_copy.length; refer += (n - 1)) {
				collinearPoints.add(points_copy[refer]);
				double refer_slope = points_copy[0].slopeTo(points_copy[refer]);
				for(int i = refer + 1; i < points_copy.length; i++) {
					double i_slope = points_copy[0].slopeTo(points_copy[i]);
					if(i_slope == refer_slope) {
						collinearPoints.add(points_copy[i]);
						if(collinearPoints.size() >= 4 && i == points_copy.length - 1) {
							if(collinearPoints.get(1).compareTo(collinearPoints.get(0)) > 0) {
								auxilliary_segments.add(new LineSegment(collinearPoints.get(0), collinearPoints.getLast()));
							} 
							continue B;					
						}
					}
					else {
						n = collinearPoints.size();
						if(n >= 4 && collinearPoints.get(1).compareTo(collinearPoints.get(0)) > 0) {
							//collinearPointsSet.add(temp);
							auxilliary_segments.add(new LineSegment(collinearPoints.get(0), collinearPoints.getLast()));
						}
						collinearPoints = new LinkedList<>();
						collinearPoints.add(points_copy[0]);
						continue A;
					}
				}
			}
		}
		//for(int i = 0; i < collinearPoints)
		//System.out.println(collinearPointsSet);
//		List<LineSegment> newList = new ArrayList<>();
//		for (LineSegment element : auxilliary_segments) { 
//            if (!newList.contains(element))
//                newList.add(element); 
//        } 
		
		segments = auxilliary_segments.toArray(new LineSegment[0]);
	}
	
//	private boolean checkEquals(Point[] v, Point[] w) {
//		if(v.length != w.length)
//			return false;
//		for(int i = 0; i < v.length; i++) {
//			if(v[i].compareTo(w[i]) != 0)
//				return false;
//		}
//		return true;
//	}
	
	private void validate_null(Point[] points) {
		if(points == null)
			throw new IllegalArgumentException();
		for(int i = 0; i < points.length; i++) {
			if(points[i] == null)
				throw new IllegalArgumentException();
		}
	}
	
	private void validate_duplicate(Point[] points) {
		for(int i = 0; i < points.length - 1; i ++) {
			//if(points[i + 1] == points[i])
			if(points[i].compareTo(points[i + 1]) == 0)
				throw new IllegalArgumentException();
		}  
	}
	
//	private Point[] remove(Point[] points) {
//		Arrays.sort(points);
//		List<Point> temp1 = Arrays.asList(points);
//		List<Point> temp2 = new ArrayList<>();
//		for (Point element : temp1) { 
//            if (!temp2.contains(element) && element != null)
//                temp2.add(element); 
//        } 
//		return temp2.toArray(new Point[0]);
//	}
	
	public int numberOfSegments() {
		return segments.length;
	}
	
	public LineSegment[] segments() {
		return segments.clone();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(10000, 0);
		Point p2 = new Point(0, 10000);
		Point p3 = new Point(3000, 7000);
		Point p4 = new Point(7000, 3000);
		Point p5 = new Point(20000, 21000);
		Point p6 = new Point(3000, 4000);
		Point p7 = new Point(14000, 15000);
		Point p8 = new Point(6000, 7000);
//		Point p9 = new Point(30000, 0);
//		Point p10 = new Point(0, 30000);
//		Point p11 = new Point(20000, 10000);
//		Point p12 = new Point(13000, 0);
//		Point p13 = new Point(11000, 3000);
//		Point p14 = new Point(5000, 12000);
//		Point p15 = new Point(9000, 6000);
		Point[] test = {p1, p2, p3, p4,p5,p6,p7,p8};
		
		StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : test) {
	        p.draw();
	    }
	    StdDraw.show();
	    
	    FastCollinearPoints collinear = new FastCollinearPoints(test);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();

	}

}
