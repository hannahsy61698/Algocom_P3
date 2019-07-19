/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algocom_p3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 *
 * @author Hannah Sy
 */
public class Driver {

    /**
     * @param args the command line arguments
     */

	
    
    //Number 3
    public static class Pt {
        public int x;
	public int y;
    }
    
    public static class Point {
	public int x;
	public int y;
	public int inputIndex;

	public static Comparator<Point> xComparator = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p1.getX() < p2.getX() ? -1 : (p1.getX() == p2.getX() ? 0 : 1));
            }
	};
		
	public static Comparator<Point> yComparator = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p1.getY() < p2.getY() ? -1 : (p1.getY() == p2.getY() ? 0 : 1));
            }
	};
		
	public int getX() {
            return this.x;
	}
		
	public int getY() {
            return this.y;
	}
		
	public void setX(int x) {
            this.x = x;
	}
		
	public void setY(int y) {
            this.y = y;
	}
		
	public void setInputIndex(int inputIndex) {
            this.inputIndex = inputIndex;
	}
		
	public int getInputIndex() {
            return this.inputIndex;
	}
    }
    
    public static void closestPair (Pt[] P) {
	ArrayList<Point> pointX = new ArrayList<Point>();
	ArrayList<Point> pointY = new ArrayList<Point>();
		
	for(int i = 0; i < P.length; i++) {
            Point p = new Point();
            p.setX(P[i].x);
            p.setY(P[i].y);
            p.setInputIndex(i);
            pointX.add(p);
            pointY.add(p);
	}
		
	Collections.sort(pointX, Point.xComparator);
	Collections.sort(pointY, Point.yComparator);
		
	double distance = findClosestPair(pointX, pointY,  P.length);
		
	System.out.println("Smallest Distance: " + distance);
    }	
	private static int[] minIndex = new int[2];
	
	private static double findClosestPair(List<Point> pointX, List<Point> pointY, int size) {
	
		if(size <= 3) {
			return doBruteForce(pointX, size);
		}
		
		int mid = size / 2;
		Point midPoint = pointX.get(mid);
		
		
		List<Point> PYL = new ArrayList<Point>();
		List<Point> PYR = new ArrayList<Point>();
		
		for(int i = 0; i < size; i++) {
			
			if(i < mid) 
				PYL.add(pointY.get(i));
			else if(i > mid)
				PYR.add(pointY.get(i));	
		}
		
		List<Point> shiftedPointX = new ArrayList<Point>();
		for(int i = 0; i < size - mid - 1; i++) {
			shiftedPointX.add(pointX.get(i + mid + 1));
		}
		double distanceLeft = findClosestPair(pointX, PYL, mid);
		double distanceRight = findClosestPair(shiftedPointX, PYR, (size - mid - 1));
		
		double distance = Math.min(distanceLeft, distanceRight);
		
		List<Point> rectangle = new ArrayList<Point>();
		for(int i = 0; i < size; i++) {
			
			if(Math.abs(pointY.get(i).getX() - midPoint.x) < distance) 
				rectangle.add(pointY.get(i));
			
		}
		return Math.min(distance, rectangleClosest(rectangle, distance));	
	}
	
	private static double rectangleClosest(List<Point> rectangle, double distance) {
		
		int size = rectangle.size();
		double min = distance;
		
		for(int i = 0 ; i < size; i++) {
			for(int j = i + 1; j < size; j++) {
				double smallestDistance = calculateDistance(rectangle.get(j), rectangle.get(i));
				
				if(smallestDistance < min) {
					min = smallestDistance;
					minIndex[0] = rectangle.get(i).getInputIndex();
					minIndex[1] = rectangle.get(j).getInputIndex();
				}
				
				if(rectangle.get(j).getY() - rectangle.get(i).getY() < min)
					break;
			}
		}
		
		return min;
	}
	
	private static double doBruteForce(List<Point> pointX, int size) {
		
		double min = Double.MAX_VALUE;
		for(int i = 0; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				double smallestDistance = calculateDistance(pointX.get(i), pointX.get(j));
				if(smallestDistance < min) {
					min = smallestDistance;
					minIndex[0] = pointX.get(i).getInputIndex();
					minIndex[1] = pointX.get(j).getInputIndex();
					
				}
			}
		}
		
 		return min;
	}
	private static double calculateDistance(Point p1, Point p2) {
		return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
				         (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}
	
    public static void main(String[] args) {
		
		Building[] B = new Building[8];
		
		B[0] = new Building();
		B[0].left = 1;
		B[0].height = 11;
		B[0].right = 5;
		
		B[1] = new Building();
		B[1].left = 2;
		B[1].height = 6;
		B[1].right = 7;
		
		B[2] = new Building();
		B[2].left = 3;
		B[2].height = 13;
		B[2].right = 9;
		
		B[3] = new Building();
		B[3].left = 12;
		B[3].height = 7;
		B[3].right = 16;
		
		B[4] = new Building();
		B[4].left = 14;
		B[4].height = 3;
		B[4].right = 25;
		
		B[5] = new Building();
		B[5].left = 19;
		B[5].height = 18;
		B[5].right = 22;
		
		B[6] = new Building();
		B[6].left = 23;
		B[6].height = 13;
		B[6].right = 29;
		
		B[7] = new Building();
		B[7].left = 24;
		B[7].height = 4;
		B[7].right = 28;		
		
		skyline(B);
		
//		int[] A = { 7, 8, 9, 4, 3, 2, 1 };
//		mergesort(A);
//		
//		Pt[] points = new Pt[5];
//		points[0] = new Pt();
//		points[0].x = 0;
//		points[0].y = 0;
//		
//		points[1] = new Pt();
//		points[1].x = 0;
//		points[1].y = 1;
//		
//		points[2] = new Pt();
//		points[2].x = 100;
//		points[2].y = 45;
//		
//		points[3] = new Pt();
//		points[3].x = 2;
//		points[3].y = 3;
//		
//		points[4] = new Pt();
//		points[4].x = 9;
//		points[4].y = 9;
//		
//		closestPair(points);

	}
}


