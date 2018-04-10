# Shortest Job First (SJF)

In this post we will discuss the preemptive version of SJF known as Shortest Remaining Time First (SRTF).
In this scheduling algorithm, the process with the smallest amount of time remaining until completion is selected to execute. 
Since the currently executing process is the one with the shortest amount of time remaining by definition, 
and since that time should only reduce as execution progresses, 
processes will always run until they complete or a new process is added that requires a smaller amount of time..
<table>
<tr><th>Process</th><th>Duration</th><th>Order</th><th>Arrival Time</th></tr>
<tr><td>P1</td><td>9</td><td>1</td><td>0</td></tr>
<tr><td><P2/td><td>2</td><td>2</td><td>2</td></tr>
</table>

<img src="sjf.png">

<hr>
P1 waiting time: 4-2 = 2<br>
P2 waiting time: 0<br>
The average waiting time(AWT): (0 + 2) / 2 = 1<br>
<hr>

## Advantage:
* Short processes are handled very quickly.
* The system also requires very little overhead since it only makes a decision when a process completes or a new process is added.
* When a new process is added the algorithm only needs to compare the currently executing process with the new process, ignoring all other processes currently waiting to execute.

## Disadvantage:
* Like shortest job first, it has the potential for process starvation.
* Long processes may be held off indefinitely if short processes are continually added.
