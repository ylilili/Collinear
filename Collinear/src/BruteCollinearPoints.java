import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private final LineSegment[] segments;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		validate_null(points);
		Point[] points_copy = points.clone();
		Arrays.sort(points_copy);
		validate_duplicate(points_copy);
		
		
		//remove(points_copy);
		List<LineSegment> segments_auxiliary = new ArrayList<>();
		for(int p = 0; p < points_copy.length - 3; p++) {
			//Point pointP = points[p];
			for(int q = p + 1; q < points_copy.length - 2; q++) {
				//Point pointQ = points[q];
				double slop_pq = points_copy[p].slopeTo(points_copy[q]);
				for(int r = q + 1; r < points_copy.length - 1; r++) {
					//Point pointR = points[r];
					double slop_pr = points_copy[p].slopeTo(points_copy[r]);
					if(slop_pq != slop_pr)
						continue;
					for(int s = r + 1; s < points_copy.length; s++) {
						//Point pointS = points[s];
						double slop_ps = points_copy[p].slopeTo(points_copy[s]);
						if(slop_pq != slop_ps)
							continue;
						segments_auxiliary.add(new LineSegment(points_copy[p], points_copy[s]));
					}
				}
			}
		}
//		List<LineSegment> newList = new ArrayList<>();
//		for (LineSegment element : segments_auxiliary) { 
//            if (!newList.contains(element))
//                newList.add(element); 
//        }
		segments = segments_auxiliary.toArray(new LineSegment[0]);
	}
	
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
	
	// the number of line segments
	public int numberOfSegments() {
		return segments.length;
	}
	
	// the number of line segments
	public LineSegment[] segments() {
		return segments.clone();
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(19000, 10000);
		Point p2 = new Point(18000, 10000);
		Point p3 = new Point(32000, 10000);
		Point p4 = new Point(19000, 10000);
		Point p5 = new Point(1234, 5678);
		Point p6 = new Point(14000, 10000);
		Point[] test = {p1, p2, p3, p4, p5, p6};
		
		StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : test) {
	        p.draw();
	    }
	    StdDraw.show();
	    
		BruteCollinearPoints collinear = new BruteCollinearPoints(test);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
		
	}
}
