package user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class mainInterface extends JFrame {
    private final int[][] grid = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,1,1,1,1,1,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,1,1,1,1},
            {1,0,1,1,1,1,0,0,1,1,1,1,1},
            {1,0,1,1,1,1,1,0,0,1,1,1,1},
            {1,1,1,1,1,1,1,1,0,1,1,1,1},
            {1,0,1,1,0,1,1,1,0,0,1,1,1},
            {1,0,0,0,1,0,0,1,1,0,1,1,1},
            {1,0,1,0,0,1,1,0,0,0,0,9,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}};

    private ArrayList<int[]> path = new ArrayList<>();
    private final int[][] coord = {{1,0},{-1,0},{0,1},{0,-1}};

    public mainInterface() {
        setTitle("Maze Solver");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int n = grid.length, m = grid[0].length;
        solveGrid(1,1,n,m,new boolean[n][m]);
    }

    private boolean solveGrid(int i, int j, int n, int m, boolean[][] vis) {
        if (i<0 || j<0 || i==n || j==m) return false;
        if (grid[i][j]==1) return false;

        if (grid[i][j]==9) {
            vis[i][j] = true;
            path.add(new int[]{i,j});
            return true;
        }

        boolean ans = false;
        for (int k=0;k<4;k++) {
            int u = i+coord[k][0];
            int v = j+coord[k][1];

            if (!vis[u][v]) {
                vis[u][v] = true;
                ans = ans || solveGrid(u,v,n,m,vis);
            }
        }

        if (ans) path.add(new int[]{i,j});
        return ans;
    }

    @Override
    public void paint(Graphics canvas) {

        int n = grid.length, m = grid[0].length;

        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {

                Color c = switch (grid[i][j]) {
                    case 1 -> Color.BLACK;
                    case 9 -> Color.RED;
                    default -> Color.WHITE;
                };

                canvas.setColor(c);
                canvas.fillRect(30*j,30*i,30,30);
                canvas.setColor(Color.CYAN);
                canvas.drawRect(30*j,30*i,30,30);
            }
        }

        for (int[] c : path) {
            canvas.setColor(Color.GREEN);
            canvas.fillRect(30 * c[1], 30 * c[0], 30, 30);
        }
    }
}
