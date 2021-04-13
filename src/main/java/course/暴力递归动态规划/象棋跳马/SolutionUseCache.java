package course.暴力递归动态规划.象棋跳马;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 4:38 下午
 */
public class SolutionUseCache {

    //请同学们自行搜索或者想象一个象棋的棋盘，
    //然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
    //那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
    //给你三个 参数 x，y，k
    //返回“马”从(0,0)位置出发，必须走k步
    //最后落在(x,y)上的方法数有多少种?

    private static final int MAX_X = 8;
    private static final int MAX_Y = 9;
    private int aimX;
    private int aimY;

    public int horseJump(int leftStep, int aimX, int aimY) {
        this.aimX = aimX;
        this.aimY = aimY;
        int[][][] cache = new int[MAX_X + 1][MAX_Y + 1][leftStep + 1];
        for (int i = 0; i <= MAX_X; i++) {
            for (int j = 0; j <= MAX_Y; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
        return process(0, 0, leftStep, cache);
    }

    public int process(int startX, int startY, int leftStep, int[][][] cache) {
        // 正好到达目标位置
        if (startX == aimX && startY == aimY && leftStep == 0) {
            return 1;
        }
        // 跳出棋盘
        if (startX < 0 || startX > MAX_X || startY < 0 || startY > MAX_Y) {
            return 0;
        }
        // 未跳出棋盘,没有剩余步数
        if (leftStep == 0) {
            return 0;
        }
        int ans = cache[startX][startY][leftStep];
        if (ans != -1) {
            return ans;
        }
        int ways = 0;
        ways += process(startX + 1, startY + 2, leftStep - 1, cache);
        ways += process(startX + 1, startY - 2, leftStep - 1, cache);
        ways += process(startX - 1, startY + 2, leftStep - 1, cache);
        ways += process(startX - 1, startY - 2, leftStep - 1, cache);
        ways += process(startX + 2, startY + 1, leftStep - 1, cache);
        ways += process(startX + 2, startY - 1, leftStep - 1, cache);
        ways += process(startX - 2, startY + 1, leftStep - 1, cache);
        ways += process(startX - 2, startY - 1, leftStep - 1, cache);
        cache[startX][startY][leftStep] = ways;
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int aimX = 3;
            int aimY = 3;
            int leftStep = 2;
            SolutionUseCache solution = new SolutionUseCache();
            int ways = solution.horseJump(leftStep, aimX, aimY);
            System.out.println("方法数:" + ways);
        }
    }
}
