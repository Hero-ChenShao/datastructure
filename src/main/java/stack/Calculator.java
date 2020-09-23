package stack;

public class Calculator {

    public static void main(String[] args) {

        String expression = "300+5*6";
        ArrayStack2 numStack = new ArrayStack2(5);
        ArrayStack2 operStack = new ArrayStack2(5);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';

        while (true){

            ch = expression.substring(index, index + 1).charAt(0);
            index++;
            String totalNum = "" + ch;

            if(!numStack.isOper(ch)){
                while(!numStack.isOper(expression.substring(index, index + 1).charAt(0))){
                    totalNum = totalNum + expression.substring(index, index + 1).charAt(0);
                    index++;
                }

                numStack.push(Integer.parseInt(totalNum));
            }else{
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else {
                    if(operStack.priority(ch) >= operStack.priority(operStack.apparent())){
                        operStack.push(ch);
                    }else{
                        while(true){
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = numStack.cal(num1, num2, oper);
                            numStack.push(res);
                            if(operStack.priority(ch) >= operStack.priority(operStack.apparent())){
                                operStack.push(ch);
                                break;
                            }

                        }
                    }
                }
            }
            if(index == expression.length() - 1){
                numStack.push(Integer.parseInt(expression.substring(expression.length() - 1)));
                break;
            }
        }
        while(!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.println(numStack.pop());

    }

}
//定义一个 ArrayStack2 表示栈
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int apparent() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        return value;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级，优先级是程序员确定的，优先级使用数字表示, 数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1; // 假定目前的表达式只有 + - * /
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val){

//        if(val == '+' || val == '-' || val == '*' || val == '/'){
//            return true;
//        }
//        return false;

        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0; // 用于存放计算的结果
        switch (oper){
            case 43:
                res = num1 + num2;
                break;
            case 45:
                res = num2 - num1;
                break;
            case 42:
                res = num1 * num2;
                break;
            case 47:
                res = num2 / num1;
        }

        return res;
    }

}
