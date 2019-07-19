import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Driver {
	
    public static class Building {
        public int left;
        public int right; 
        public int height; 
    }
    
    public static class SkylinePoint {
        public int x;
	public int y;
        
        public SkylinePoint() {
	}
        
        public SkylinePoint(int left, int height) {
            this.x = left;
            this.y = height;
	}
        
        public int getSkyX(){
            return x;
        }
        
        public int getSkyY(){
            return y;
        }
        
        public void setSkyX(int a){
            x = a;
        }
        
        public void setSkyY(int a){
            y = a;
        }
    }
    
    public static ArrayList<SkylinePoint> mergeSkylines(ArrayList<SkylinePoint> leftSkylinePointList, ArrayList<SkylinePoint> rightSkylinePointList) {
		
	ArrayList<SkylinePoint> mergedSkylinePoints = new ArrayList<SkylinePoint>();
	int leftHeight = 0; 
	int rightHeight = 0;
	
        int b = leftSkylinePointList.size();
	int c = rightSkylinePointList.size();
		
	int i = 0; 
	int j = 0; 
        
	while(i < b && j < c) {
            SkylinePoint newSkylinePoint = new SkylinePoint();
            
            if(leftSkylinePointList.get(i).getSkyX() < rightSkylinePointList.get(j).getSkyX()) {
                newSkylinePoint.setSkyX(leftSkylinePointList.get(i).getSkyX());
                leftHeight = leftSkylinePointList.get(i).getSkyY();		
                newSkylinePoint.setSkyY(Math.max(leftHeight, rightHeight));
                i++;
            }else {
		newSkylinePoint.setSkyX(rightSkylinePointList.get(j).getSkyX());
		rightHeight = rightSkylinePointList.get(j).y;
		newSkylinePoint.setSkyY(Math.max(leftHeight, rightHeight));
		j++;
            }
			
            addNewSkylinePoint(mergedSkylinePoints, newSkylinePoint);
        }
		
	while(i < b) {
            mergedSkylinePoints.add(leftSkylinePointList.get(i));
            i++;
	}
		
	while(j < c) {
            mergedSkylinePoints.add(rightSkylinePointList.get(j));
            j++;
	}
		
	return mergedSkylinePoints;
    }
    
    public static ArrayList<SkylinePoint> getSkylines(Building[] B, int lhs, int rhs) {
	ArrayList<SkylinePoint> skyLinePointList = new ArrayList<SkylinePoint>();
	
        if(lhs < rhs) {
            int mid = lhs + ((rhs - lhs) / 2);
            
            ArrayList<SkylinePoint> leftSkylinePointList = getSkylines(B, lhs, mid);
            ArrayList<SkylinePoint> rightSkylinePointList = getSkylines(B, mid + 1, rhs);
            
            return mergeSkylines(leftSkylinePointList, rightSkylinePointList);
	} else if (lhs == rhs) { 
                SkylinePoint s1 = new SkylinePoint(B[lhs].left, B[lhs].height);
                SkylinePoint s2 = new SkylinePoint(B[lhs].right, 0);
                skyLinePointList.add(s1);
                skyLinePointList.add(s2);
	}
	
        return skyLinePointList;
    }
    
    public static void skyline(Building[] B) {
	ArrayList<SkylinePoint> skylinePoints;
	skylinePoints = getSkylines(B, 0, B.length - 1);

	for(int a = 0; a < skylinePoints.size(); a++) {
            System.out.print(skylinePoints.get(a).getSkyX() + " " + skylinePoints.get(a).getSkyY() + " ");
	}
	
        System.out.println();
    }
	
    //#2
    public static void combine(int[] A, int lhs, int rhs, int mid) {
	int n1 = mid - lhs + 1;
	int n2 = rhs - mid;
        
        int l = 0; 
	int r = 0; 
		
	int[] leftArray = new int[n1 + 1];
	int[] rightArray = new int[n2 + 1];
		
	for(int i = 0; i < n1; i++) {
            leftArray[i] = A[lhs + i];
	}
		
	for(int j = 0; j < n2; j++) {
            rightArray[j] = A[mid + j + 1];
	}
		
	leftArray[n1] = Integer.MAX_VALUE;
	rightArray[n2] = Integer.MAX_VALUE;
		
	for(int a = lhs; a <= rhs; a++) {
            if(leftArray[l] <= rightArray[r]) {
		A[a] = leftArray[l];
                l++;
            }else {
		A[a] = rightArray[r]; 
		r++;
            }
	}
    }
    
    public static void mergesort (int[] A) {
        int m, rhs;
        
        for(int a = 1; a < A.length; a = 2 * a) {
            for(int lhs = 0; lhs < A.length; lhs = lhs +  2 * a) {
        	m = Math.min(lhs + a - 1,  A.length -1); 
                rhs = Math.min(lhs + 2 * a - 1,  A.length -1); 
        	combine(A, lhs, rhs, m);
            }
        }
        
        for (int a = 0; a < A.length; a++) {
        	System.out.print(A[a] + " ");
        }
        
        System.out.println();
    }
    
    //#3
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
// ------------------------------------------------------------------------------------	
	//check previous element and dont add unecessary skyline points (when new x or height is same as the recently added skyline point's x or height.)
	private static void addNewSkylinePoint(List<SkylinePoint> mergedSkylinePoints, 
			                        SkylinePoint newSkylinePoint) {
		int n = mergedSkylinePoints.size();

		
		if(n > 0) {
			if(mergedSkylinePoints.get(n - 1).y == newSkylinePoint.y)
				return;
			
			else if(mergedSkylinePoints.get(n - 1).x == newSkylinePoint.x) {
				mergedSkylinePoints.get(n - 1).y = Math.max(mergedSkylinePoints.get(n - 1).y, newSkylinePoint.y);
				return;
			}
		}
		//safetly add new skyline point.
		mergedSkylinePoints.add(newSkylinePoint);
	}
	
	private static int[] minIndex = new int[2];
	
	private static double findClosestPair(List<Point> pointX, List<Point> pointY, int size) {
	
		if(size <= 3) {
			return doBruteForce(pointX, size);
		}
		
		int mid = size / 2;
		Point midPoint = pointX.get(mid);
		
//		System.out.println("size: " + size);
//		System.out.println("pointX size:  " + pointX.size());
//		System.out.println("pointY size: " + pointY.size());
//		System.out.println("Mid: " + mid);
		
		
		List<Point> PYL = new ArrayList<Point>();
		List<Point> PYR = new ArrayList<Point>();
		
		//separate the points to PL and PR (sorted in Y)
		for(int i = 0; i < size; i++) {
			
			if(i < mid) 
				PYL.add(pointY.get(i));
			else if(i > mid)
				PYR.add(pointY.get(i));	
		}
		
		//shift point x so that it will access the other half of the points
		List<Point> shiftedPointX = new ArrayList<Point>();
		for(int i = 0; i < size - mid - 1; i++) {
			shiftedPointX.add(pointX.get(i + mid + 1));
		}
		
//		System.out.println("PYL: " + PYL.size());
//		System.out.println("PYR: " + PYR.size());
//		System.out.println();
		
		//find closest pair of points on the left and right of the midpoint.
		double distanceLeft = findClosestPair(pointX, PYL, mid);
		double distanceRight = findClosestPair(shiftedPointX, PYR, (size - mid - 1));
		
		//Find the smallest distance between the left and right.
		double distance = Math.min(distanceLeft, distanceRight);
		
		//Optimized combined step
		List<Point> rectangle = new ArrayList<Point>();
		for(int i = 0; i < size; i++) {
			
			//If true, there's a special case of Delta(SL,SR)
			if(Math.abs(pointY.get(i).getX() - midPoint.x) < distance) 
				rectangle.add(pointY.get(i));
			
		}
		
		//Find the minimum between the smallest distance and the possible smallest distance on the special case.
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
	
	//Euclidean Distance formula
	private static double calculateDistance(Point p1, Point p2) {
		return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
				         (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}
	
}
