package Problems;

import java.util.Arrays;

/**
 *
 * Given an array of integers nums, sort the array in ascending order.
 * 给定一个整数数组 nums，将该数组升序排列。
 *
 */

public class SortInArray {

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static final int QUICKSORTSHOLD = 50;
    private static final int MERGESORTSHOLD = 300;

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2){
            return nums;
        }

        if (nums.length < QUICKSORTSHOLD){
            insertSort(nums);
        }else if (nums.length < MERGESORTSHOLD) {
            quickSort(nums);
        }else{
            mergeSort(nums);
        }
        return nums;
    }

    ///////////////////////////////////////////////////归并排序/////////////////////////////////////////////////////////

    //归并排序：最坏复杂度o(nlogn)，平均复杂度o(nlogn)，空间复杂度o(n)，稳定
    public int[] mergeSort(int[] nums) {
        Merge(nums, 0, nums.length - 1);
        return nums;
    }

    private void Merge(int[] nums, int begin, int end){
        if(begin >= end){
            return;
        }
        int mid = (begin + end) >> 1;
        Merge(nums, begin, mid);
        Merge(nums, mid + 1, end);

        int[] newNums = new int[end - begin + 1];
        int loc = 0;
        int loc1 = begin;
        int loc2 = mid + 1;
        while(loc1 <= mid && loc2 <= end){
            newNums[loc++] = nums[loc1] < nums[loc2] ? nums[loc1++] : nums[loc2++];
        }
        while(loc2 <= end){
            newNums[loc++] = nums[loc2++];
        }
        while(loc1 <= mid){
            newNums[loc++] = nums[loc1++];
        }
        while(begin <= end){
            nums[end--] = newNums[--loc];
        }
    }

    ///////////////////////////////////////////////////快速排序/////////////////////////////////////////////////////////

    //快速排序：最坏复杂度o(n^2)，平均复杂度o(nlogn)，空间复杂度o(logn)，不稳定
    //STL优化方法：当数据量较大时采用快速排序，分段递归。一旦分段后的数据量小于某个阀值，为避免递归调用带来过大的额外负荷，便会改用插入排序。而如果递归层次过深，有出现最坏情况的倾向，还会改用堆排序。
    public int[] quickSort(int[] nums) {
        quickSortSolution(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSortSolution(int[] nums, int begin, int end){
        if(begin < end){
            int pivot = partition(nums, begin, end);
            quickSortSolution(nums, begin, pivot - 1);
            quickSortSolution(nums, pivot + 1, end);
        }
    }

    private int partition(int[] nums, int begin, int end){
        int pivot = begin + (int)(Math.random() * (end - begin));
        swap(nums, begin, pivot);
        pivot = begin;
        while(begin <= end){
            while(begin <= end && nums[begin] <= nums[pivot]){
                ++begin;
            }
            while(begin <= end && nums[end] > nums[pivot]){
                --end;
            }
            if(begin <= end){
                swap(nums, begin, end);
            }
        }
        //此时end的右边数字均比nums[pivot]大，交换后pivot对应数字位于正确位置end
        swap(nums, pivot, end);
        return end;
    }

    ///////////////////////////////////////////////////堆排序///////////////////////////////////////////////////////////

    //堆排序：最坏复杂度o(nlogn)，平均复杂度o(nlogn)，空间复杂度o(1)，不稳定
    public int[] heapSort(int[] nums) {
        //构建初始堆，从第一个非叶子结点至根节点进行堆调整，构建初始堆，此时最大值位于堆顶，且每一个结点均比子结点大。
        int firstNotLeaves = (nums.length >> 1) - 1;
        for(int i = firstNotLeaves; i >= 0; i--){
            heapAdjust(nums, nums.length, i);
        }
        //每次循环将堆顶元素（最大值）交换至最尾，再对剩余元素进行一次堆调整
        for(int tail = nums.length - 1; tail >= 1; tail--){
            swap(nums, 0, tail);
            heapAdjust(nums, tail, 0);
        }
        return nums;
    }

    //每次堆调整将rootIdx所在左右子结点的较大值交换至rootIdx，并且自上而下进行至不能再交换。
    private void heapAdjust(int[] nums, int length, int rootIdx){
        int maxIdx = rootIdx;
        //通过左子结点是否超出length判断根节点是否在length内有子树
        int leftIdx = (maxIdx << 1) + 1;
        int rightIdx = leftIdx + 1;
        while(leftIdx < length){
            maxIdx = nums[leftIdx] > nums[maxIdx] ? leftIdx : maxIdx;
            maxIdx = rightIdx < length && nums[rightIdx] > nums[maxIdx] ? rightIdx : maxIdx;
            if(maxIdx == rootIdx){
                break;
            }
            swap(nums, rootIdx, maxIdx);
            rootIdx = maxIdx;
            leftIdx = (maxIdx << 1) + 1;
            rightIdx = leftIdx + 1;
        }
    }

    ///////////////////////////////////////////////////插入排序/////////////////////////////////////////////////////////

    //普通插入排序：最坏复杂度o(n^2)，平均复杂度o(n^2)，空间复杂度o(1)，稳定（最好情况o(n)）
    public int[] insertSort(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int temp = nums[i];
            int j = i - 1;
            //将nums[i]插入i之前第一个不大于它的值j的位置之后
            while(j >= 0 && nums[j] > temp){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
        return nums;
    }

    ///////////////////////////////////////////////////希尔排序/////////////////////////////////////////////////////////

    //希尔排序：最坏复杂度o(n^2)，平均复杂度o(n^1.3-2)（取决于增量序列），空间复杂度o(1)，不稳定（跳跃插入机制破坏）
    public int[] shellSort(int[] nums) {
        for(int dk = nums.length / 2; dk >= 1; dk /= 2){
            for(int i = dk; i < nums.length; i++){
                if(nums[i] < nums[i - dk]){
                    int temp = nums[i];
                    int j = i - dk;
                    while(j >= 0 && nums[j] > temp){
                        nums[j + dk] = nums[j];
                        j -= dk;
                    }
                    nums[j + dk] = temp;
                }
            }
        }
        return nums;
    }

    /////////////////////////////////////////////////折半插入排序///////////////////////////////////////////////////////

    //折半插入排序：最坏复杂度o(n^2)，平均复杂度o(n^2)，空间复杂度o(1)，稳定（减少了比较次数，但是元素的移动次数不变）
    public int[] halfInsertSort(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int temp = nums[i];
            int begin = 0;
            int end = i - 1;
            while(begin <= end){
                int mid = (begin + end) >> 1;
                if(nums[mid] > temp){
                    end = mid - 1;
                }else{
                    begin = mid + 1;
                }
            }
            for(int j = i - 1; j >= end + 1; j--){
                nums[j + 1] = nums[j];
            }
            nums[end + 1] = temp;
        }
        return nums;
    }

    ///////////////////////////////////////////////////冒泡排序/////////////////////////////////////////////////////////

    //冒泡排序：最坏复杂度o(n^2)，平均复杂度o(n^2)，空间复杂度o(1)，稳定（最好情况o(n)）
    //改进方案：1.若本次无交换，说明元素有序，直接跳出；2.记录本次最后一个交换的位置lastExchangeIndex，该位置之后的元素已经有序，不需要交换。
    public int[] bubbleSort(int[] nums) {
        int lastExchangeIndex = nums.length - 1;
        int sortOrder = nums.length - 1;
        for(int i = 0; i < nums.length - 1; i++){
            boolean flag = false;
            for(int j = 0; j < sortOrder; j++){
                if(nums[j] > nums[j + 1]){
                    swap(nums, j + 1, j);
                    lastExchangeIndex = j;
                    flag = true;
                }
            }
            sortOrder = lastExchangeIndex;
            if(!flag){
                return nums;
            }
        }
        return nums;
    }

    ///////////////////////////////////////////////////选择排序/////////////////////////////////////////////////////////

    //选择排序：最坏复杂度o(n^2)，平均复杂度o(n^2)，空间复杂度o(1)，不稳定
    public int[] selectionSort(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < nums.length; j++){
                min = nums[j] < nums[min] ? j : min;
            }
            if(min != i){
                swap(nums, i, min);
            }
        }
        return nums;
    }

    ///////////////////////////////////////////////////基数排序/////////////////////////////////////////////////////////

    //基数排序/桶排序：最坏复杂度o(d(n+r))，平均复杂度o(d(n+r))，空间复杂度o(r)，稳定（d个关键码/位数，关键码的取值范围/桶数r）
    //以下算法无法对负数排序
    public int[] radixSort(int[] nums) {
        //增加对负数适应性
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        if(min < 0){
            for(int i = 0; i < nums.length; i++){
                nums[i] += (-min + 1);
            }
        }
        //从个位开始，对数组a按"指数"进行排序
        //当对数组按个位进行排序时，exp=1；按十位进行排序时，exp=10；...
        for (int exp = 1; (max - min + 1) / exp > 0; exp *= 10) {
            countSort(nums, exp);
        }

        if(min < 0){
            for(int i = 0; i < nums.length; i++){
                nums[i] -= (-min + 1);
            }
        }
        return nums;
    }

    private static void countSort(int[] nums, int exp) {
        // 存储"被排序数据"的临时数组
        int[] output = new int[nums.length];
        int[] buckets = new int[10];

        // 将数据出现的次数存储在buckets[]中
        for (int i = 0; i < nums.length; i++){
            buckets[(nums[i] / exp) % 10]++;
        }

        // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
        for (int i = 1; i < 10; i++){
            buckets[i] += buckets[i - 1];
        }

        // 将数据存储到临时数组output[]中
        for (int i = nums.length - 1; i >= 0; i--) {
            output[buckets[(nums[i] / exp) % 10] - 1] = nums[i];
            buckets[(nums[i] / exp) % 10]--;
        }

        // 将排序好的数据赋值给nums
        for (int i = 0; i < nums.length; i++){
            nums[i] = output[i];
        }
    }

    ///////////////////////////////////////////////////计数排序/////////////////////////////////////////////////////////

    //计数排序：最坏复杂度o(n+k)，平均复杂度o(n+k)，空间复杂度o(k)，稳定（k为数据的取值范围）
    //将所有元素的频次放入对应的桶中，再进行排序。计数排序是桶排序的一种特殊情况。
    public int[] countingSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = num > max ? num : max;
            min = num < min ? num : min;
        }

        int[] buckets = new int[max - min + 1];
        for (int i : nums) {
            buckets[i - min] += 1;
        }

        int[] result = new int[nums.length];
        int resultIdx = 0;

        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i]-- != 0) {
                result[resultIdx++] = i + min;
            }
        }
        return result;
    }


}



