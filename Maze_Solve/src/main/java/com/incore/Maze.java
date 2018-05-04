

import java.util.Random;

class Maze extends Thread {
    //this hold Number of path have
    private static int solving_count;
    // this hold number of test have done
    private static int Number_of_test;


    private int [][] maze;
    private int no_of_iteration;

    public Maze(int size_of_maze, int no_of_iteration) {
        this.no_of_iteration= no_of_iteration;
        Number_of_test+=no_of_iteration;
    }



    public void MazeGenarator(int size){
        Random ran = new Random();
        int [][] tmp = new int [size][size];

        for (int i=0;i<size;i++){
            for (int j =0;j<size;j++){
                tmp[i][j]=ran.nextInt(2);
            }
        }
        tmp[0][0]=0;
        tmp[size-1][size-1]=0;
        this.maze= tmp;

    }



    public void show() {
        for(int i=0; i < maze.length; i++) {
            System.out.printf("{");
            for(int j=0; j < maze[i].length; j++)
                System.out.printf("%d%s", maze[i][j],
                        j != maze[i].length - 1 ? ", ": " }\n");
        }
    }


    public boolean findPath(int sx, int sy, int ex, int ey) {
        maze[sx][sy]=1;
        if(sx==ex && sy==ey)
             return  true;
        // when some point check it  check their moving possibilities for 4 direction
        if((sx-1>0) && (maze[sx-1][sy])==0 && findPath(sx-1,sy,ex,ey))
             return true;

        if((sy+1<maze.length) && (maze[sx][sy+1])==0 &&  findPath(sx,sy+1,ex,ey))
             return true;

        if((sx+1<maze.length) && (maze[sx+1][sy])==0 &&  findPath(sx+1,sy,ex,ey))
             return true;

        if((sy-1>0) && (maze[sx][sy-1])==0 &&  findPath(sx,sy-1,ex,ey))
             return true;

        //maze[sx][sy]=0;
        return  false;


    }

    public void showPath(int x, int y, int X, int Y) {
    }


    public void run(){
        int i=0;
        //System.out.println("run");
        while (i<no_of_iteration) {

            MazeGenarator(20);

            if (findPath(0, 0, 19, 19)) {

                synchronized(this) {
                    solving_count++;
                }

            }
            //System.out.println(i);
            i++;

        }
        //System.out.println("erun");
    }


    public static void main(String [] args) throws InterruptedException {
        int size_of_maze= 20;
        int test_case = 10000000;

        Maze m1= new Maze(size_of_maze,test_case);
        Maze m2= new Maze(size_of_maze,test_case);
        Maze m3= new Maze(size_of_maze,test_case);

        m1.start();
        m2.start();
        m3.start();

        m3.join();
        m2.join();
        m1.join();


        System.out.println(solving_count);

        System.out.println(solving_count/(double)Number_of_test);
    }
}