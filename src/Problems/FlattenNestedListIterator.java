package Problems;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的项或者为一个整数，或者是另一个列表。
 *
 */

public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    //记忆栈
    public class NestedIterator implements Iterator<Integer> {

        Stack<NestedInteger> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            //初始化迭代器时，倒序将所有元素入栈
            for(int i = nestedList.size() - 1; i >= 0; i--){
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        //将迭代器指向的下一元素进行解套并入栈
        public boolean hasNext() {
            while(!stack.isEmpty()){
                NestedInteger cur = stack.peek();
                //如果当前元素没有嵌套，则直接返回true;否则，弹出该元素并将解套后的子序列入栈
                if(cur.isInteger()){
                    return true;
                }
                stack.pop();
                for(int i = cur.getList().size() - 1; i >= 0; i--){
                    stack.push(cur.getList().get(i));
                }
            }
            //栈空时返回false
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
