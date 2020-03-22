package com.ssm.activity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ActivityApplicationTests {

    @Test
    void contextLoads() {
    }


//    public static void main(String[] args) {
//
//        int[] num=new int[4];//存放四位数的个十百千位
//        System.out.println("请输入一个四位数：");
//        Scanner scanner=new Scanner(System.in);
//        int input=scanner.nextInt();
////        int input = 6636;
//        for (int i = 0; i < 4;i++) {//逐次取位，从个位开始,并按要求替换
//            num[i]=(input%10+5)%10;
//            input/=10;
//        }
//        //按要求替换,交换1、4位
//        int temp=0;//交换数
//        temp=num[0];
//        num[0]=num[3];
//        num[3]=temp;
//        //交换2、3位
//        temp=num[1];
//        num[1]=num[2];
//        num[2]=temp;
//        System.out.println(""+num[3]+num[2]+num[1]+num[0]);
//        scanner.close();
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String arr = scanner.next();
////        String arr = "2,5,3,6,9,8";
//        int start = scanner.nextInt();;
//        int end = scanner.nextInt();;
//        String[] strArr = arr.split(",");
//        int[] intArr = new int[strArr.length];
//        for (int i = 0; i < strArr.length; i++) {
//            intArr[i] = Integer.parseInt(strArr[i]);
//        }
//        QuickSort(intArr, start, end);
//        for(int i = 0; i < intArr.length; i++){
//            System.out.print(intArr[i]);
//            if(i<intArr.length-1){
//                System.out.print(",");
//            }
//        }
//    }
//
//    public static void QuickSort(int arr[], int start, int end) {
//        if (start >= end) {
//            return;
//        }
//        int i = start;
//        int j = end;
//        // 基准数
//        int baseval = arr[start];
//        while (i < j) {
//            // 从右向左找比基准数小的数
//            while (i < j && arr[j] >= baseval) {
//                j--;
//            }
//            if (i < j) {
//                arr[i] = arr[j];
//                i++;
//            }
//            // 从左向右找比基准数大的数
//            while (i < j && arr[i] < baseval) {
//                i++;
//            }
//            if (i < j) {
//                arr[j] = arr[i];
//                j--;
//            }
//        }
//        // 把基准数放到i的位置
//        arr[i] = baseval;
//        // 递归
//        QuickSort(arr, start, i - 1);
//        QuickSort(arr, i + 1, end);
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String arr = scanner.next();
////        String arr = "1,2,-3,3,1,2,1,2,4";
//        String[] strArr = arr.split(",");
//        int[] intArr = new int[strArr.length];
//        for (int i = 0; i < strArr.length; i++) {
//            intArr[i] = Integer.parseInt(strArr[i]);
//        }
//
//        int result = maxSubArray(intArr);
//        System.out.println(result);
//    }
//
//        public static int maxSubArray(int[] nums) {
//            int res = nums[0];
//            int sum = 0;
//            for (int num : nums) {
//                if (sum > 0)
//                    sum += num;
//                else
//                    sum = num;
//                res = Math.max(res, sum);
//            }
//            return res;
//        }


    /**
     * 被搜索数据的大小
     */
//    private static final int size = 5;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String arr = scanner.next();
////        String arr = "21,2,21,3,41,45,42";
//        String[] strArr = arr.split(",");
//        int[] intArr = new int[strArr.length];
//        for (int i = 0; i < strArr.length; i++) {
//            intArr[i] = Integer.parseInt(strArr[i]);
//        }
//
//        findTheSameNum(intArr);
//    }
//
//    /**
//     * 调用二分搜索算法的方法实现查找相同元素
//     */
//    public static void findTheSameNum(int data[]) {
//        Arrays.sort(data);
//        for (int i = 0; i < data.length; i++) {
//            int target = data[i];
//            data[i] = 0;
//            int sameNumIndex = binarySearch(data, target);
//            if (sameNumIndex != -1) {
//                System.out.println("" + data[sameNumIndex]);
//                break;
//            }
//        }
//    }
//
//    /**
//     * 二分搜索算法实现
//     *
//     * @param data   数据集合
//     * @param target 搜索的数据
//     * @return 返回找到的数据的位置，返回-1表示没有找到。
//     */
//    public static int binarySearch(int[] data, int target) {
//        int start = 0;
//        int end = data.length - 1;
//        while (start <= end) {
//            int middleIndex = (start + end) / 2;
//            if (target == data[middleIndex]) {
//                return middleIndex;
//            }
//            if (target >= data[middleIndex]) {
//                start = middleIndex + 1;
//            } else {
//                end = middleIndex - 1;
//            }
//        }
//        return -1;
//    }

//    static class WorkerThread extends Thread {
//        public WorkerThread(String message) {
//            System.out.println(message);
//        }
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String arr = scanner.next();
//        new WorkerThread(arr).start();
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arr = scanner.next();
//        String arr = "a,2,c,4,d,a,A,C,1";
        String[] strArr = arr.split(",");

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < strArr.length; i++){
            String temp = strArr[i];
            Integer count = map.get(temp);
            if (count == null) {
                map.put(temp, 1);
            }else{
                count++;
                map.put(temp, count);
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key+":"+value);

        }
    }

//    public static int[] bubbleSort(int[] array) {
//        if (array.length == 0)
//            return array;
//        for (int i = 0; i < array.length; i++)
//            for (int j = 0; j < array.length - 1 - i; j++)
//                if (array[j + 1] < array[j]) {
//                    int temp = array[j + 1];
//                    array[j + 1] = array[j];
//                    array[j] = temp;
//                }
//        return array;
//    }


}
