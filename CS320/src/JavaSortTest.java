import java.util.Scanner;

public class JavaSortTest
{
private static Scanner scan;

public static void main(String[] args)
{
		
		
		JavaSort newArray = new JavaSort(13);

		newArray.generateRandomArray();
		System.out.println("Command Options: ");
		System.out.println("a: Bubble sort");
		System.out.println("b: Shell sort");
		System.out.println("c: insertion sort");
		System.out.println("d: quick sort");
		System.out.println("e: selection sort");
		System.out.println("f: merge sort");
		System.out.println("q: Quit");
		scan = new Scanner(System.in);
		String choice = scan.nextLine();
		do
		{
		 switch(choice)
         {
         case "a":
           
        	 
        	 newArray.bubbleSort();
        	 
        	 break;
         case "b":
        	  
        	  newArray.shellsort();
        	 
        	 break;
         case "c":
            
             newArray.insertionSort();
             break;
         case "d":
           
    		
             newArray.quickSort(0, 12);
             
             break;
         case "e":
            
             newArray.selectionSort();
         break;
         case "f":	
        	 int[] inputArr = new int[13];
     		int arraysize1=13;
     		for(int i = 0; i < arraysize1; i++)
     		{
     			
     		inputArr[i] = (int)(Math.random()*90)+10;			
     		}
             newArray.mergesort(inputArr);
             for(int i:inputArr)
             {
                System.out.print(i);
                 System.out.print(" ");
             }
             
         break;    
         case "?":
        	 System.out.println("Command Options: ");
     		System.out.println("a: Bubble sort");
     		System.out.println("b: Shell sort");
     		System.out.println("c: insertion sort");
     		System.out.println("d: quick sort");
     		System.out.println("e: selection sort");
     		System.out.println("f: merge sort");
     		System.out.println("q: Quit");
         break;
         }
		
        }
		while (choice != "q");
	}
}