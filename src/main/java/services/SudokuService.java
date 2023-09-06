package services;



import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
public class SudokuService {


    public int[][] getBoard(String level) {
        int[][] p = new int[9][9];
        createSolution(p);
        createBoard(p,level);
        return p;
    }

    private void createBoard(int[][] board,String level) {
        int clues = 45;
        if(level.equals("medium")){
            clues = 36;
        }
        if(level.equals("hard")){
            clues = 27;
        }
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<9;i++){
            map.put("row"+(i+1),1);
            map.put("column"+(i+1),  1);
            map.put("box"+(i+1),1);
        }
        int[][] k = new int[9][9];
        int count = 0;
        while(count < clues){
            int i = (int)(Math.random()*9);
            Integer row = map.get("row"+(i+1));
                int j = (int)(Math.random()*9);
                Integer column = map.get("column"+(j+1));
                Integer box = map.get("box"+(((3*(i/3))+(j/3))+1));
                if(row!=null && column!=null && box!=null && k[i][j]==0 && row<(clues/9)+1 && column<(clues/9)+1 && box<(clues/9)+1){
                    k[i][j] = 1;
                    map.put("row"+(i+1),row+1);
                    //map.put("column"+(j+1),column+1);
                    //map.put("box"+(((3*(i/3))+(j/3))+1),box+1);
                    count++;
                }
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(k[i][j]==1){
                    continue;
                }
                else{
                    board[i][j] = 0;
                }
            }
        }

    }

    private boolean createSolution(int[][] board) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    int[] p = getNumbers();
                    for(int k=0;k<9;k++){
                        int num = p[k];
                        if(isValid(i,j,num,board) && board[i][j]==0){
                            board[i][j] = num;
                            boolean result = createSolution(board);
                            if(result){
                                return true;
                            }
                            board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private int[] getNumbers() {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        for(int i=0;i<9;i++){
            int j = (int) (Math.random()*9);
            int k = (int) (Math.random()*9);
            int p = nums[j];
            nums[j] = nums[k];
            nums[k] = p;
        }
        return nums;
    }



    private boolean isValid(int row,int column,int num,int[][] board){
        for(int i=0;i<9;i++){
            if(board[row][i] == num || board[i][column] == num) return false;
        }
        int box_row = 3*(row/3);
        int box_column = 3*(column/3);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[box_row+i][box_column+j]==num) return false;
            }
        }
        return true;
    }
}
