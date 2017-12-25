/*
    Author - Ajinkya Sonawane
    Program to generate test cases given factors and their paramters
*/

import java.util.Scanner;


public class Case {
    int total_f;        //Total number of factors 
    int total_p[];      //Number of paramters per factor
    int comb =1 ;       //Swarm Size
    String[][] params;  //Matrix to store parameter values
    String[][] swarm;   //Matrix to store test cases
    
    public static void main(String args[])
    {
        Case m = new Case();
        
        //Accept the factors and paramters
        m.accept();
           
        //Generate the test cases
        m.generate_comb();
        
        //Print the test cases
        m.print_cases();
    }
    
    public void accept()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the total number of factors : ");
        total_f = scan.nextInt();
        total_p = new int[total_f];
        
        params = new String[total_f][];
        
        //Accept the parameters for each factor
        for(int i=0;i<total_f;i++)
        {
            System.out.print("\nNumber of parameters for Factor "+(i+1)+" : ");
            total_p[i] = scan.nextInt();
            params[i] = new String[total_p[i]];
            for(int j=0;j<total_p[i];j++)
            {
                System.out.print("Enter value for parameter "+(j+1)+" : ");
                params[i][j] = scan.next();
            }
            comb = comb * total_p[i];
        }
        
        System.out.print("\nSwarm Size : "+comb+"\n");
        
        //Create swarm matrix
        swarm = new String[comb][total_f];
    
    }
    
    //Function to print the test cases
    public void print_cases()
    {
        System.out.print("\t\tSwarm\n");
        for(int i=0;i<comb;i++)
        {
            System.out.print("Case "+(i+1)+" : \t");
            for(int j=0;j<total_f;j++)
            {
                System.out.print(swarm[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }
    
    //Function to generate combinations and store it in SWARM MATRIX
    public void generate_comb()
    {
       int pk=0,k=0,m = 0;
 
       for(int i=0;i<total_f;i++)
       {
		   //Traverse the factors using 'i'
		   
           for(int l=0;l<next_comb(m);l++)
           {
			   //'l' decides the number of times all the parameters should be printed
			   
                for(int j=0;j<total_p[i];j++)
                {
					//Traverse the parameters using 'j'
					
                    for(k=0;k<comb(i);k++)
                    {
						//'k' decides the number of times a single parameter should be printed
						
                        swarm[pk][i] = params[i][j];
                        pk++;
                    }
                }
            }
           m++;
           pk=0;
       }
    }
    
    //this function returns how many times a particular parameter appears 
    public int comb(int f)
    {
        int temp = 1;
        for(int i=f+1;i<total_f;i++)
        {
            temp *= total_p[i];
        }
        return temp;
 
    }
    
    //this function returns how many times the sequence of parameters should be printed
    public int next_comb(int f)
    {
        int temp = 1;
        if(f == 0)
            return 1;
        for(int i=0;i<f;i++)
        {
            temp *= total_p[i];
        }
        return temp;
    }    
}