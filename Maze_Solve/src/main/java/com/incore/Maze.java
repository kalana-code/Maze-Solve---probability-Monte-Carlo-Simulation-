package com.incore;

import java.util.Random;

class Maze {

    private int [][] maze;

    public Maze() {

        MazeGenarator(30);
    }

    public void MazeGenarator(int size){
        Random ran = new Random();
        int [][] tmp = new int [size][size];

        for (int i=0;i<size;i++){
            for (int j =0;j<size;j++){
                tmp[i][j]=ran.nextInt(999999999)%2;
            }
        }
        tmp[0][0]=0;
        tmp[size-1][size-1]=0;
        maze= tmp;

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
        maze[sx][sy]=3;
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

        maze[sx][sy]=0;
        return  false;

    }

    public void showPath(int x, int y, int X, int Y) {
    }


    public static void main(String [] args) {


            Maze m = new Maze();
            m.show();

            if (m.findPath(0, 0, 30, 30)) {
                System.out.println("There is a path");
                m.showPath(0, 0, 30, 30);
                m.show();
            } else
                //m.show();
                System.out.println("There is no path");

    }
}
