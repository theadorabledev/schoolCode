public class Stopwatch{

    private long startTime;
    private long endTime;
    
    public Stopwatch(){
		startTime = endTime = 0;
    }


    public void start(){
		startTime = System.currentTimeMillis();
    }

    public void stop(){
		endTime = System.currentTimeMillis();
    }
	//public voi
    public long elapsedTime(){
		return endTime - startTime;
    }




    public static void main(String [] args){
		Stopwatch timer = new Stopwatch();
		long stop = 100;
		for(int trial = 0; trial < 10; trial++){
			timer.start();
			for (long i = 0; i < stop; i++) ;
			timer.stop();
			System.out.println("iterations: " + stop);
			System.out.println("elapsed time: " + timer.elapsedTime());
			stop *= 10;
		}
    }
}

    
