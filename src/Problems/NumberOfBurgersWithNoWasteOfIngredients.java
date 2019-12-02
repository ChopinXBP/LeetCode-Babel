package Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0.
 * If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
 * 圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。
 * 给你两个整数 tomatoSlices 和 cheeseSlices，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：
 * 巨无霸汉堡：4 片番茄和 1 片奶酪
 * 小皇堡：2 片番茄和 1 片奶酪
 * 请你以 [total_jumbo, total_small]（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量都是 0。
 * 如果无法使剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量为 0，就请返回 []。
 *
 */

public class NumberOfBurgersWithNoWasteOfIngredients {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if(tomatoSlices == 0 && cheeseSlices == 0){
            return Arrays.asList(0, 0);
        }
        if((tomatoSlices & 1) == 1 || cheeseSlices < 0){
            return new ArrayList<>();
        }
        int bighub = (tomatoSlices - 2 * cheeseSlices) >> 1;
        int smallhub = cheeseSlices - bighub;
        return bighub >= 0 && smallhub >= 0 ? Arrays.asList(bighub, smallhub) : new ArrayList<>();
    }
}
