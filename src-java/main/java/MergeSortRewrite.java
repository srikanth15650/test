import java.util.List;

public class MergeSortRewrite {
    public static void merging(int[] inputArray){
        outputArray=new int[inputArray.length];
        mergeSort(inputArray,0,inputArray.length-1);
    }
    static int[] outputArray;
    static void mergeSort(int[] inputArray, int start, int end){
        if (start==end){
            return;
        }
        int mid=start+((end-start)/2);
        mergeSort(inputArray,start,mid);
        mergeSort(inputArray,mid+1,end);

        int i=start;
        int j=mid+1;
        int k=start;
        while (i<=mid && j<=end){
            if (inputArray[i]<=inputArray[j]){
                outputArray[k]=inputArray[i];
                i++;
                k++;
            }else {
                outputArray[k] = inputArray[j];
                j++;
                k++;
            }
        }
        while (i<=mid){
            outputArray[k]=inputArray[i];
            i++;
            k++;
        }
        while (j<=end){
            outputArray[k]=inputArray[j];
            j++;
            k++;
        }
        for (int l = start; l <=end ; l++) {
            inputArray[l]=outputArray[l];
        }
    }

    public static void main(String[] args) {
        merging(new int[]{3,4,5,6,7,2,0,9});
        for (int i = 0; i < outputArray.length; i++) {
            System.out.println(outputArray[i]);
        }

    }
}
