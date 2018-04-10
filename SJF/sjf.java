/*
		PROCESS SCHEDULING ALGORITHMS
		
	LANGUAGE : Java
	TOPIC :	SHORTEST JOB FIRST (SJF) / SHORTEST JOB NEXT (N) 
	AUTHOR : AJINKYA V. SONAWANE
*/
import java.util.*;

class Process
{
	String name;	//To store the process name
	int arrival;	//To store the arrival time of the process
	int burst;	//To store the cpu/burst time of the process
	int waiting;	//To store the waiting time of the process
	int remaining;	//To storet the reamining time incase process is waiting
	int finish;	//To store the time when the process finishes execution
	int turnaround;	//To store the turnaround time of the process
	
	public Process(String p,int a,int b)
	{
		this.name = p;
		this.arrival = a;
		this.burst = b;
		this.waiting = 0;
		this.remaining = b;
	}
	//TODO : Getter method for Arrival time
	int getArrival()
	{
		return this.arrival;
	}
	//TODO : Getter method for Remaining time
	int getRemaining()
	{	
		return this.remaining;
	}
}

public class sjf
{
	int total_p;		//To store total number of processes
	int total_burst;	//To store total burst time 
	Scanner scan;		//Scanner object to accept user input
	ArrayList<Process> process_list;	//To store all the incoming processes
	ArrayList<Process> wait_list;		//To store all the processes in waiting state
	
	public sjf()
	{
		total_burst = 0;		//Initialize total_burst time to 0
		scan = new Scanner(System.in);	//Initialize Scanner object to System Input
		process_list = new ArrayList<Process>();	//Initialize process list
		wait_list = new ArrayList<Process>();		////Initialize wait list
	}
	//TODO : Accept Process details
	void accept()
	{
		int a,b;
		System.out.print("\nEnter the total number of processes : ");
		total_p = scan.nextInt();
		
		for(int i=0;i<total_p;i++)
		{
			System.out.print("\nEnter Arrival time for P"+(i+1)+" : ");
			a = scan.nextInt();
			
			System.out.print("\nEnter Burst time for P"+(i+1)+" : ");
			b = scan.nextInt();
			//TODO : Add all burst times to get total burst time
			total_burst += b;
			//TODO : Create Process object and add to process list
			process_list.add(new Process("p"+(i+1),a,b));	
		}
		//TODO : Sort the process list to get min arrival time and add it to total burst time incase cpu is idle
		process_list.sort(Comparator.comparing(Process::getArrival));
		total_burst += process_list.get(0).arrival;
	}
	//TODO : Find the process with shortest burst time
	Process find(int ar)
	{
		Process ob;	//Process object to traverse and check arrival time
		//TODO : If arrival time matches with the argument to the function store the processes in wait list
		for(int i=0;i<process_list.size();i++)
		{	
			ob = process_list.get(i);
			if(ob.arrival == ar)
			{
				wait_list.add(ob);
			}
		}
		//TODO : Remove all processes in wait list from process list
		process_list.removeAll(wait_list);
		//TODO : if no process is in wait state return null
		if(wait_list.size() == 0)
			return null;
		//TODO : fetch the shortest process by sorting by remaining time and return
		wait_list.sort(Comparator.comparing(Process::getRemaining));
		return wait_list.get(0);
	}
	//TODO : Main Process starts here
	void process()
	{
		accept();	//TODO : Accept the process details
		Process cur;
		ArrayList<Process> done = new ArrayList<Process>();	//To store the proecesses finished 
		//TODO : Time line of all processes
		for(int i=0;i<total_burst;i++)
		{
			cur = find(i);	//Find the shortest process
			if(cur == null)	//If no process is available & cpu is idle then continue
				continue;
			cur.remaining--;	//Decrement the remaining time of current process
			//TODO : If current process finishes execution then calculate all the time information required and remove it from the wait list and store in the finished processes list
			if(cur.remaining == 0)
			{
				cur.finish = (i+1);	//Adding extra '1' as index started with '0'
				cur.turnaround = cur.finish - cur.arrival;
				cur.waiting = cur.turnaround - cur.burst;
				done.add(cur);	
				wait_list.remove(cur);
			}
		}
		//Print the Process Details after Execution
		System.out.println("Process\tArrival\tBurst\tFinish\tTurn Around\tWaiting");
		for(int i=0;i<done.size();i++)
		{
			cur = done.get(i);
			System.out.println(cur.name+"\t"+cur.arrival+"\t"+cur.burst+"\t"+cur.finish+"\t\t"+cur.turnaround+"\t"+cur.waiting);
		}
	}
	
	public static void main(String args[])
	{
		sjf ob = new sjf();
		ob.process();		//Call the processs function
	}
}
