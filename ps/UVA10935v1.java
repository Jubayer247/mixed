import java.util.*;

public class Main {

	
	public static void main(final String...args) {
		Scanner scanner=new Scanner(System.in);
		while(true) {
		int n=scanner.nextInt();
		if(n==0) {
			break;
		}
		LinkedList<Integer> list=new LinkedList<Integer>();
		
		for(int i=0;i<n;i++) {
			list.add(i+1);
		}
		
		int len=list.size();
		if(len!=1) {
			System.out.print("Discarded cards: ");
		while(len>2) {
			int discarded=list.pollFirst();
			int poped=list.pollFirst();
			list.add(poped);
			System.out.print(discarded+", ");
			len--;
		}
		int discarded=list.pollFirst();
		int poped=list.pollFirst();
		list.add(poped);
		System.out.print(discarded);
		}
		else
		{
			System.out.print("Discarded cards:");
		}
		
		System.out.println();
		System.out.println("Remaining card: "+list.get(0));
		}
		
	}
	
	
}
