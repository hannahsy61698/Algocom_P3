import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P3 {
	
//Number 1
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
	
        int x = leftSkylinePointList.size();
	int c = rightSkylinePointList.size();
		
	int i = 0; 
	int j = 0; 
        
	while(i < x && j < c) {
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
		
	while(i < x) {
            mergedSkylinePoints.add(leftSkylinePointList.get(i));
            i++;
	}
		
	while(j < c) {
            mergedSkylinePoints.add(rightSkylinePointList.get(j));
            j++;
	}
		
	return mergedSkylinePoints;
    }
    
    public static ArrayList<SkylinePoint> getSkylines(Building[] B, int l, int r) {
	ArrayList<SkylinePoint> skyLinePointList = new ArrayList<SkylinePoint>();
	
        if(l < r) {
            int mid = l + ((r - l) / 2);
            
            ArrayList<SkylinePoint> leftSkylinePointList = getSkylines(B, l, mid);
            ArrayList<SkylinePoint> rightSkylinePointList = getSkylines(B, mid + 1, r);
            
            return mergeSkylines(leftSkylinePointList, rightSkylinePointList);
	} else if (l == r) { 
                SkylinePoint s1 = new SkylinePoint(B[l].left, B[l].height);
                SkylinePoint s2 = new SkylinePoint(B[l].right, 0);
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
    
    
   private static void addNewSkylinePoint(List<SkylinePoint> mergedSkylinePoints,SkylinePoint newSkylinePoint) {
		
       
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

	
    //Number 2
    
    public static void mergesort (int[] A) {
        int mid, right;
        
        for(int a = 1; a < A.length; a = 2 * a) {
            for(int left = 0; left < A.length; left = left +  2 * a) {
        	mid = Math.min(left + a - 1,  A.length -1); 
                right = Math.min(left + 2 * a - 1,  A.length -1); 
        	merge(A, left, right, mid);
            }
        }
        int x = 0;
        while(x<A.length)
        {
            System.out.print(A[x] + " ");
       
            x++;
        }
        System.out.println();
    }
    
        public static void merge(int[] A, int lhs, int rhs, int mid) {
	int n1 = mid - lhs + 1;
	int n2 = rhs - mid;
        
        int l = 0; 
	int r = 0; 
		
	int[] lA = new int[n1 + 1];
	int[] rA = new int[n2 + 1];
		
        int i=0;
        while (i < n1)
        {
            lA[i] = A[lhs + i];
            i++;
        }
        int j=0;
        while(j<n2)
        {
            rA[j] = A[mid + j + 1];
            j++;
        }
		
	lA[n1] = Integer.MAX_VALUE;
	rA[n2] = Integer.MAX_VALUE;
	int a = lhs;
        while(a<=rhs)
        {
            if(lA[l] <= rA[r]) {
		A[a] = lA[l];
                l++;
            }else {
		A[a] = rA[r]; 
		r++;
            }
            a++;
        }
    }
        
        
        //Number 3
        
        public class Pt {
            public int x;  //X-coordinate
            public int y; // Y-coordinate}
        }
        
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

        public static void closestPair (Pt[] P) {
           //put your code here and the print statements for the output.
        }
        
        private static double calculateDistance(Pt p1, Pt p2) {
		return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
				         (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}
	

	
}
