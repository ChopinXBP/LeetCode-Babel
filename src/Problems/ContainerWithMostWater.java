package Problems;

/**
 * 
 * @author ChopinXBP
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n的值至少为 2。
 *
 */

public class ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] height = {1,8,6,2,5,4,8,3,7};
		int[] height2 = {1,2,4,3};
		System.out.println(maxArea(height));
		System.out.println(maxArea(height2));
		System.out.println(maxArea2(height));
		System.out.println(maxArea2(height2));
	}

	//对每一条线段i，找左右两端距离i最长的不小于i的长度的线段j。j作为长，i作为宽，ij构成的矩形是包含线段i的面积最大的矩形。
    public static int maxArea(int[] height) {
        int result = 0;
        
        for(int i = 0; i < height.length; i++) {
        	int num = height[i];
        	int longest = 0;
        	
        	int idx = 0;
        	while(idx < i) {
        		if(height[idx] >= num) {
        			longest = i - idx;
        			break;
        		}
        		idx++;
        	}
        	idx = height.length - 1;
        	while(idx > i && idx - i > longest) {
        		if(height[idx] >= num) {
        			longest = idx - i;
        			break;
        		}
        		idx--;
        	}
        	
        	if(num * longest > result) {
        		result = num * longest;
        	}
        }
        
        return result;
    }
    
    //设置left和right从两端逼近，每次计算其和较短边围成的矩形的面积，之后移动较短边。逼近过程中最大的矩形面积即为所求。
    public static int maxArea2(int[] height) {
        int result = 0;
        
        int right = height.length - 1;
        int left = 0;
        
        while(left < right) {
        	int shorter = height[right] < height[left] ? height[right] : height[left];
        	if((right - left) * shorter > result) {
        		result = (right - left) * shorter;
        	}
        	//每次移动较短边（移动较长边可能会导致下一个矩形因为短边限制取不到最优）
        	if(height[right] < height[left]) {
        		right--;
        	}else {
        		left++;
        	}
        }
        
        return result;
    }
}
