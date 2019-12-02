package Problems; /**
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 */


import java.util.Stack;

/**
 * Your Problems.MinStack object will be instantiated and called as such:
 * Problems.MinStack obj = new Problems.MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

public class MinStack {

    /** initialize your data structure here. */
    public Stack<Integer> mystack;
    public Stack<Integer> minstack;
    public int mindata;

    public MinStack() {
        mystack = new Stack<>();
        minstack = new Stack<>();
        mindata = Integer.MAX_VALUE;
    }

    public void push(int x) {
        mindata = mystack.isEmpty() ? x : Math.min(x, mindata);
        mystack.push(x);
        minstack.push(mindata);
    }

    public void pop() {
        if(mystack.empty())
            return;
        mystack.pop();
        minstack.pop();
        mindata = getMin();
    }

    public int top() {
        return mystack.empty() ? Integer.MAX_VALUE : mystack.peek();
    }

    public int getMin() {
        return minstack.empty() ? Integer.MAX_VALUE : minstack.peek();
    }

}
