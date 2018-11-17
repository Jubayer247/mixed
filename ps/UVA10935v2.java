import java.util.*;

public class Main {
  public static void main(final String...args) {
			int start=0;
    int end=0;
    Scanner scanner=new Scanner(System.in);
		while(true) {
      start=0;
      end=0;
		int n=scanner.nextInt();
		if(n==0) {
			break;
		}
		int[] array=new int[n];
		for(int i=0;i<n;i++) {
			array[i]=i+1;
		}
		
		int len=array.length;
		if(len!=1) {
			System.out.print("Discarded cards: ");
	
    int control=len-2;
    if(len!=2) 
    while(end<control) {
			int discarded=array[start];
			int poped=array[(start+1)%array.length];
			array[end]=poped;
			System.out.print(discarded+", ");
      start=(start+2)%array.length;
      end=end+1;
		}
		  int discarded=array[start];
			int poped=array[(start+1)%array.length];
			array[end]=poped;
			System.out.print(discarded);
      start=(start+2)%array.length;
      end=end+1;
		}
		else
		{
			System.out.print("Discarded cards:");
		}
				System.out.println();
		System.out.println("Remaining card: "+array[start]);
		}
		
	}
}
