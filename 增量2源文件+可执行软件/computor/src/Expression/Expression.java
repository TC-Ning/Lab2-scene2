package Expression;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
//C4上的第一个修改
public class Expression
{
	/**
	 * 主函数，测试用的
	 * @param args
	 */
	public static void main(String[] args)
	{
		Expression e = new Expression();
		String temp;
		for (int i = 0; i < 100; i++) //生成100个表达式，可以改成其他数量
		{
			temp = e.expression();
			System.out.print(temp + " ");
			System.out.println("答案："+e.result(temp));
		}
	}

	/**
	 * 随机返回一个至少有两个运算符的四则运算表达式，例如：1+9*8或（8-4）*9
	 * @return 返回字符串类型的四则运算表达式
	 */
	public String expression()
	{
		Random random = new Random();
		int number;
		boolean flag = false;
		int result = -1;
		String expression=null, operator;
		while (result < 0)
		{
			int probability=random.nextInt(3);
			expression = "";
			flag = false;
			if(probability==0)	//控制生成带括号表达式的概率为1/3
			{
				expression+="(";
				String temp="";
				int tempResult=-1;
				while(tempResult<0)
				{
					temp="";
					temp+=(random.nextInt(50)+1);
					switch(random.nextInt(2))	//括号中运算符出现+或-的概率均为1/2
					{
						case 0:
							temp+="+";break;
						case 1:
							temp+="-";break;
					}
					temp+=(random.nextInt(50)+1);
					tempResult=Expression.result(temp);
				}
				expression+=(temp+")");
				switch(random.nextInt(5))
				{
					case 0:
					case 1:
						expression+="*";break;	//括号后的运算符为*的概率为2/5
					default:
						expression+=("/"+divisor(tempResult)+"+");	//括号后的运算符为/的概率为3/5
						break;
				}
			}
			int length = random.nextInt(2) + 3;	//再随机产生2~4个操作数
			String[] parse;
			for (int i = 0; i < 2 * length - 1; i++)
			{
				if (i % 2 == 0)
				{
					if (!flag)
						number = random.nextInt(50) + 1;
					else
					{
						parse = expression.split("\\+|-|\\*|/");
						number = divisor(Integer.parseInt(parse[parse.length - 1]));
					}
					expression += number;
					flag = false;
				} else
				{
					operator = operator();
					if (operator.equals("/"))
					{
						flag = true;
						parse = expression.split("0|1|2|3|4|5|6|7|8|9");
						if (parse.length > 0)
						{
							if (parse[parse.length - 1].equals("/"))
								operator = "+";
						}
					}
					expression += operator;
				}
			}
			result = Expression.result(expression);
		}
		return expression;
	}

	/**
	 * 随机产生一个四则运算符(+,-,*,/)
	 * @return 返回一个字符串类型的四则运算符
	 */
	private String operator()
	{
		String result = "";
		Random random = new Random();
		int index = random.nextInt(4);
		switch (index)
		{
			case 0:
				result += '+';
				break;
			case 1:
				result += '-';
				break;
			case 2:
				result += '*';
				break;
			case 3:
				result += "/";
				break;
		}
		return result;
	}

	/**
	 * 随机返回给定整数的一个因数
	 * @param n	int类型
	 * @return	返回参数n的一个因数，int类型
	 */
	private int divisor(int n)
	{
		if(n==0)
			return 1;
		ArrayList<Integer> divisor = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 2; i < n; i++)
		{
			if (n % i == 0)
				divisor.add(i);
		}
		int size = divisor.size();
		if (size > 0)
			return divisor.get(r.nextInt(size));
		divisor.add(1);
		divisor.add(n);
		return divisor.get(r.nextInt(2));
	}

	/**
	 * 求给定四则运算表达式的值
	 * @param str 待求的四则运算表达式，String类型
	 * @return 	求得的表达式的值，int类型
	 */
	public static int result(String str)
	{
		char[] charExpression = str.toCharArray();	//将String类型的表达式转化成字符数组，便于后续处理
		ArrayList<String> expression = new ArrayList<String>();	//将表达式中的每一个操作数或运算符作为一个元素存储在List结构里
		String number = "";
		for (char k : charExpression)
		{
			if (k >= '0' && k <= '9')
				number += k;
			else if (k == '+' || k == '-' || k == '*' || k == '/' || k == '(' || k == ')')
			{
				if (number.length() > 0)
				{
					expression.add(number);
					number = "";
				}
				expression.add("" + k);
			}

		}
		if (number.length() > 0)
			expression.add(number);
		ArrayList<String> suffix = new ArrayList<String>();	//存储后缀表达式
		Stack<String> operStack = new Stack<>();	//运算符栈
		String temp;
		//先将中缀表达式转化为后缀表达式
		for (String s : expression)
		{
			if (s.equals("("))	//遇到左括号直接入栈
				operStack.push(s);
			else if (s.equals(")"))	//遇到右括号，则将栈内元素依次弹出并加入到后缀表达式尾部，直到遇到左括号
			{
				while (!((temp = operStack.pop()).equals("(")))
					suffix.add(temp);
			} else if (s.equals("*") || s.equals("/"))	//遇到*或/时，若栈顶为*或/，则将栈顶元素弹出并加入后缀表达式，
			{											//直到栈顶不是*或/，或栈为空
				while (!operStack.isEmpty())
				{
					temp = operStack.peek();
					if (temp.equals("*") || temp.equals("/"))
					{
						operStack.pop();
						suffix.add(temp);
					} else
					{
						break;
					}
				}
				operStack.push(s);
			} else if (s.equals("+") || s.equals("-"))	//遇到+或-时，只要栈顶不是左括号，就将栈顶元素弹出并加入后缀表达式，
			{											//直到栈顶是左括号，或栈为空
				while (!operStack.isEmpty())
				{
					temp = operStack.peek();
					if (!temp.equals("("))
					{
						operStack.pop();
						suffix.add(temp);
					} else
					{
						break;
					}
				}
				operStack.push(s);
			} else	//其他情况，代表遇到了数字，直接将数字加入后缀表达式即可
			{
				suffix.add(s);
			}
		}
		while (!operStack.isEmpty())	//最后若栈不为空，则将栈内元素依次出栈并加入后缀表达式
		{
			suffix.add(operStack.pop());
		}
		Stack<Integer> numStack = new Stack<Integer>();	//操作数栈
		int num1 = 0, num2 = 0;
		//计算后缀表达式的值
		for (String s : suffix)
		{
			if (s.equals("+"))	//若遇到+或-或*或/，则从栈中取出两个数进行相应运算，并将结果入栈
			{
				num1 = numStack.pop();
				num2 = numStack.pop();
				numStack.push(num1 + num2);
			} else if (s.equals("-"))
			{
				num1 = numStack.pop();
				num2 = numStack.pop();
				numStack.push(num2 - num1);
			} else if (s.equals("*"))
			{
				num1 = numStack.pop();
				num2 = numStack.pop();
				numStack.push(num1 * num2);
			} else if (s.equals("/"))
			{
				num1 = numStack.pop();
				num2 = numStack.pop();
				numStack.push(num2 / num1);
			} else	//其他情况，代表遇到了操作数，直接进栈
			{
				numStack.push(Integer.valueOf(s));
			}
		}
		return numStack.peek();	//最终遍历完整个后缀表达式，栈中唯一元素即为表达式的值
	}
}
