package com.example.demo;

public class InsertionSort {
    public static void insertionSort(int[] inputArray){

        for(int i=0;i<inputArray.length;i++){
            int redPointer=i-1;
            int tempValue=inputArray[i];
            while(redPointer>=0 && inputArray[redPointer]>tempValue){
                inputArray[redPointer+1]=inputArray[redPointer];
                redPointer=redPointer-1;
            }
            inputArray[redPointer+1]=tempValue;
        }

    }

//    public void insertionSort2(int[] nums){
//        for(int i=0;i<nums.length;i++){
//            int redPointer = i-1;
//            int tempValue = nums[i];
//            while(redPointer>=0 && nums[redPointer]>nums[redPointer+1]){
//
//                nums[redPointer+1] = nums[redPointer];
//            }
//            nums[redPointer]=tempValue;
//        }
//    }
//
//    public void insertionSort3(int[] nums){
//        for(int i=0;i<nums.length;i++){
//            int redPointer= i-1;
//            while(redPointer>=0 && nums[redPointer+1]<nums[redPointer]){
//                nums[redPointer+1]=nums[redPointer];
//            }
//            nums[redPointer]=nums[i];
//        }
//    }
}
