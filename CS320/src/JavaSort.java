
class JavaSort
{
	  private int[] array;
	    private int[] tempMergArr;
	    private int length; 
	int[] theArray = new int[50];
	
	private int arraySize = 13;
	
	public void generateRandomArray()
	{
		
		for(int i = 0; i < arraySize; i++)
		{
			
			theArray[i] = (int)(Math.random()*90)+10;
			
		}
		
	}
	
	
public void printHorzArray(int i, int j)
{
		
		for(int n = 0; n < 69; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < arraySize; n++)
		{
			
			System.out.print("| " + n + "  ");
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 69; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < arraySize; n++)
		{
			
			System.out.print("| " + theArray[n] + " ");
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 69; n++)System.out.print("-");
		
		System.out.println();
		
		// END OF FIRST PART
		
		
		// ADDED FOR BUBBLE SORT
		
	if(j != -1)
		{
		
	//		 ADD THE +2 TO FIX SPACING
			
			for(int k = 0; k < ((j*5)+2); k++)System.out.print(" ");
			
			System.out.print(j);
			
		}
		
		
		// THEN ADD THIS CODE
		
		if(i != -1){
			
			// ADD THE -1 TO FIX SPACING
			
			for(int l = 0; l < (5*(i - j)-1); l++)System.out.print(" ");
			
			System.out.print(i);
			
		}
		
		System.out.println();
		
	}
      public void printHorzArray(int i, int j, int h) {

	if (i == j)
		i = i - h;

	for (int n = 0; n < 51; n++)
		System.out.print("-");

	System.out.println();

	for (int n = 0; n < arraySize; n++) {

		System.out.print("| " + n + "  ");

	}

	System.out.println("|");

	for (int n = 0; n < 51; n++)
		System.out.print("-");

	System.out.println();

	for (int n = 0; n < arraySize; n++) {

		System.out.print("| " + theArray[n] + " ");

	}

	System.out.println("|");

	for (int n = 0; n < 51; n++)
		System.out.print("-");

	System.out.println();

	if (i != -1) {

		// Number of spaces to put before the F

		int spacesBeforeFront = 5 * i + 1;

		for (int k = 0; k < spacesBeforeFront; k++)
			System.out.print(" ");

		System.out.print("I");

		// Number of spaces to put before the R

		int spacesBeforeRear = (5 * j + 1 - 1) - spacesBeforeFront;

		for (int l = 0; l < spacesBeforeRear; l++)
			System.out.print(" ");

		System.out.print("O");

		System.out.println("\n");

	}

}


	// This bubble sort will sort everything from 
		// smallest to largest
		
		public void bubbleSort(){
			
			// i starts at the end of the Array
			// As it is decremented all indexes greater
			// then it are sorted
			
			for(int i = arraySize - 1; i > 1; i--){
				
				// The inner loop starts at the beginning of 
				// the array and compares each value next to each 
				// other. If the value is greater then they are 
				// swapped
				
				for(int j = 0; j < i; j++){
					
					// To change sort to Descending change to <
					
					if(theArray[j] > theArray[j + 1]){
						
						swapValues(j, j+1);
						
						printHorzArray(i, j);
						
					}
					
				}
				
			}
			
		}
		
		public void swapValues(int indexOne, int indexTwo){
			
			int temp = theArray[indexOne];
			theArray[indexOne] = theArray[indexTwo];
			theArray[indexTwo] = temp;
			
		}
		
		
		
		
		public void insertionSort()
		{
			
			for (int i = 1; i < arraySize; i++)
			{
				  int j = i;
				  int toInsert = theArray[i];
				  while ((j > 0) && (theArray[j-1] > toInsert))
				  {
					  theArray[j] = theArray[j-1];
					  j--;
					  
					  printHorzArray(i, j);
					  
				  }
				  theArray[j] = toInsert;
				  
				  printHorzArray(i, j);
				  
				  System.out.println("\nArray[i] = " + theArray[i] + " Array[j] = " + theArray[j] + " toInsert = " + toInsert + "\n");
				  
			}
			
		}
public void selectionSort()
{
			
			for(int x=0; x < arraySize; x++){
				  int minimum = x;
				  
				  for(int y=x; y < arraySize; y++){
				  
					  // To change direction of sort just change 
					  // this from > to <
					  
					  if(theArray[minimum]>theArray[y]){
						  minimum = y;
					  }
				  }
				  
				  swapValues(x, minimum);
				  
				  printHorzArray(x, -1);
			}
			
		}
public void QuickSort(int newArraySize) 
{

	arraySize = newArraySize;

	theArray = new int[arraySize];

	generateRandomArray();

}

public void quickSort(int left, int right) {

	if (right - left <= 0)
		return; 

	else {


		int pivot = theArray[right];

	System.out.println("Value in right " + theArray[right]
				+ " is made the pivot");

		System.out.println("left = " + left + " right= " + right
			+ " pivot= " + pivot );

		int pivotLocation = partitionArray1(left, right, pivot);

	System.out.println("Value in left " + theArray[left]
			+ " is made the pivot");

		quickSort(left, pivotLocation - 1); // Sorts the left side

		quickSort(pivotLocation + 1, right);

	}

}

public int partitionArray1(int left, int right, int pivot) {

	int leftPointer = left - 1;

	int rightPointer = right;

	while (true) {

		while (theArray[++leftPointer] < pivot)
			;

		printHorzArray(leftPointer, rightPointer);

		System.out.println(theArray[leftPointer] + " in index "
				+ leftPointer + " is bigger than the pivot value " + pivot);

		while (rightPointer > 0 && theArray[--rightPointer] > pivot)
			;

		printHorzArray(leftPointer, rightPointer);

		System.out.println(theArray[rightPointer] + " in index "
				+ rightPointer + " is smaller than the pivot value "
				+ pivot);

		printHorzArray(leftPointer, rightPointer);

		if (leftPointer >= rightPointer) {

			System.out.println("left is >= right so start again");

			break;

		}

		else {

			swapValues(leftPointer, rightPointer);

			System.out.println(theArray[leftPointer] + " was swapped for "
					+ theArray[rightPointer]);

		}

	}

	swapValues(leftPointer, right);

	return leftPointer;

}

public void shellsort()
{

	int inner, outer, temp;

	int interval = 1;
	while (interval <= arraySize / 3)
	{

		// Define an interval sequence

		interval = interval * 3 + 1;
	}
	// Keep looping until the interval is 1
	// Then this becomes an insertion sort

	while (interval > 0) {

		// Continue incrementing outer until the end of the array is reached

		for (outer = interval; outer < arraySize; outer++) {

			// Store the value of the array in temp unless it has to be
			// copied to a space occupied by a bigger number closer to the
			// beginning of the array

			temp = theArray[outer];

			System.out.println("Copy " + theArray[outer] + " into temp");

			// inner is assigned the value of the highest index to check
			// against all values the proceed it. Along the way if a
			// number is greater than temp it will be moved up in the array

			inner = outer;

			System.out.println("Checking if " + theArray[inner - interval]
					+ " in index " + (inner - interval)
					+ " is bigger than " + temp);

			// While there is a number bigger than temp move it further
			// up in the array

			while (inner > interval - 1 && theArray[inner - interval] >= temp) {

				System.out.println("In While Checking if "
						+ theArray[inner - interval] + " in index "
						+ (inner - interval) + " is bigger than " + temp);

				printHorzArray(inner, outer, interval);

				// Make room for the smaller temp by moving values in the
				// array
				// up one space if they are greater than temp

				theArray[inner] = theArray[inner - interval];

				System.out.println(theArray[inner - interval]
						+ " moved to index " + inner);

				inner -= interval;

				System.out.println("inner= " + inner);

				printHorzArray(inner, outer, interval);

				System.out.println("outer= " + outer);
				System.out.println("temp= " + temp);
				System.out.println("interval= " + interval);

			}

			// Now that everything has been moved into place put the value
			// stored in temp into the index above the first value smaller
			// than it is

			theArray[inner] = temp;

			System.out.println(temp + " moved to index " + inner);

			printHorzArray(inner, outer, interval);

		}

		// Once we get here we have interval sorted our array
		// so we decrement interval and go again

		interval = (interval - 1) / 3;
	}
	}

JavaSort(int arraySize)
{

	this.arraySize = arraySize;

	theArray = new int[arraySize];

	generateRandomArray();

}

 

public void mergesort(int inputArr[]) {
    this.array = inputArr;
    this.length = inputArr.length;
    this.tempMergArr = new int[length];
    doMergeSort(0, length - 1);
}

private void doMergeSort(int lowerIndex, int higherIndex) {
     
    if (lowerIndex < higherIndex) {
        int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
        // Below step sorts the left side of the array
        doMergeSort(lowerIndex, middle);
        // Below step sorts the right side of the array
        doMergeSort(middle + 1, higherIndex);
        // Now merge both sides
        mergeParts(lowerIndex, middle, higherIndex);
    }
}

private void mergeParts(int lowerIndex, int middle, int higherIndex) {

    for (int i = lowerIndex; i <= higherIndex; i++) {
        tempMergArr[i] = array[i];
    }
    int i = lowerIndex;
    int j = middle + 1;
    int k = lowerIndex;
    while (i <= middle && j <= higherIndex) {
        if (tempMergArr[i] <= tempMergArr[j]) {
            array[k] = tempMergArr[i];
            i++;
        } else {
            array[k] = tempMergArr[j];
            j++;
        }
        k++;
    }
    while (i <= middle) {
        array[k] = tempMergArr[i];
        k++;
        i++;
    }

}
}
