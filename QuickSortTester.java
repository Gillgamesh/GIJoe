public class QuickSortTester {
    public static void main( String [] args ) {
    System.out.print("NOTE: if superTests do not work, please use the -Xmx tag (i.e. java -Xmx10G MergeTester) to allocate more memory");
    System.out.println("Generating times for given array lengths:");
    //lengths that you wish to try out.
    int[] testcases = {1,2,4,8,16,32,64,128,256,512,1024,2048,3072,4096,8192};
    System.out.println("Length\tTime");
    for (int test: testcases) {
        System.out.println(""+test+","+avgTime(test));
    }
    //these testcases have a lower amount of tests but are much bigger
    int test15 = (int)Math.pow(2,15);
    int test20 = (int)Math.pow(2,20);
    int test25 = (int)Math.pow(2,25);
    int test26 = (int)Math.pow(2,26);
    int test27 = (int)Math.pow(2,27);
    int test28 = (int)Math.pow(2,28);
    int test29 = (int)Math.pow(2,29);
    int[] superTests = {test15,test20,test25,test26,test27,test28,test29};
    System.out.println("SUPERTESTS:");
    System.out.println("Length\tTime");
    for (int test: superTests) {
        System.out.println(""+test+","+avgTime(test,10));
    }
}//end main()
//-------------------HELPERS-------------------------
//tester function for exploring how arrays are passed
//usage: print array, mess(array), print array. Whaddayasee?
//MODIFIED VERSION: mess a preexisitng array
    public static void mess( int[] a, int nums) {
        for( int i = 0 ; i<a.length; i++ )
            a[i] = (int) Math.random()*nums;
    }

    public static void mess( int[] a) {
        for( int i = 0 ; i<a.length; i++ ) {
            a[i] = 0;
        }
    }


    //helper method for displaying an array
    public static void printArray( int[] a ) {
        System.out.print("[");
        for( int i : a )
            System.out.print( i + ",");
        System.out.println("]");
    }


    //---------------------------------------------------
//generate random test cases for performance
    public static int[] randomArray(int length, int nums) {
        int[] arr =  new int[length];
        //populate array in range [0,nums)
        for (int i = 0; i < length; i++) {
            arr[i]=(int) (Math.random()*nums);
        }
        return arr;
    }
    public static int[] randomArray(int length) {
        //since the length of specific elements doesnt matter, limit it to 3 digits max
        return randomArray(length,1000);
    }


    //returns run time as an array with a certain amount of tries
    public static double[] runTime(int length, int tries) {
        int[] arr; //array to be sorted, changed every time
        int[] ans;
        long startTime;
        long endTime;
        double time;
        //create an array that is as long as the specified length
        arr = randomArray(length);
        //array controlling runtime of each try
        double[] times = new double[tries];
        //define start time and end time:
        for (int i=0; i < tries; i++) {
            //rescramble the array and try again
            mess(arr);
            startTime = System.nanoTime();
            QuickSort.qsort(arr);
            ans = arr;
            endTime = System.nanoTime();
            times[i] = ((double)(endTime-startTime))/1000000000.0; //add the time it took to run in seconds
        }
        return times;
    }



    public static double avgTime (int length, int tries) {
    double[] times = runTime(length, tries);
    double total = 0;
    //add the totals together in a for loop
    for (int i = 0; i < times.length; i++) {
        total+=times[i];
    }
    //return the arithmetic average:
    return total/times.length;
    }
    //returns avg time for 10K tests
    public static double avgTime (int length) {
        return avgTime(length, 10000);
    }



}
