import java.util.LinkedList;

/**
 *
 * Implement the following operations of a stack using queues.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 */

public class ImplementStackUsingQueues {
    class MyStack {
        LinkedList<Integer> list1;
        LinkedList<Integer> list2;
        int top;
        /** Initialize your data structure here. */
        public MyStack() {
            list1 = new LinkedList<>();
            list2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            list1.add(x);
            top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(list1.size() > 1){
                top = list1.pollFirst();
                list2.add(top);
            }
            int result = list1.pollFirst();
            LinkedList<Integer> temp = list1;
            list1 = list2;
            list2 = temp;
            return result;
        }

        /** Get the top element. */
        public int top() {
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return list1.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
