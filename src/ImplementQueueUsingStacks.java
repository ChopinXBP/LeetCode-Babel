import java.util.Stack;

/**
 *
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 */

public class ImplementQueueUsingStacks {
    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(!stack2.isEmpty()){
                return stack2.pop();
            }
            if(stack1.isEmpty()){
                return -1;
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(!stack2.isEmpty()){
                return stack2.peek();
            }
            if(stack1.isEmpty()){
                return -1;
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack2.isEmpty() && stack1.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
