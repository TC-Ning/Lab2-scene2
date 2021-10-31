package MyFrame;


import image.demoimage;
import Expression.Expression;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class compute extends JFrame {
    JTextField jtf=null;
    JTextField jtjudge=null;
    JButton num0;
    JButton num1;
    JButton num2;
    JButton num3;
    JButton num4;
    JButton num5;
    JButton num6;
    JButton num7;
    JButton num8;
    JButton num9;
    JButton restart;//先选择开始再选择题型
    JButton delete;//回退一位
    JButton clear;//重新输入
    JButton confirm;//确认答案
    JButton answer;//显示正确答案
    JButton next;//下一题
    JButton exp;//选择更高难度

    String jtft="";//显示再JTextField中的字符串
    String ans="";
    int Ans;
    String Num1="";
    String Num2="";
    String Operater="";
    boolean choose=false;//选择难度
    JComboBox status=null;

    public compute(String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(25,100,1500,646);
        JPanel jp=new JPanel();
        jp.setBackground(Color.WHITE);
        jp.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(jp);
        jp.setLayout(null);

        jtf=new JTextField("开始做题");
        jtf.setFont(new Font("宋体",Font.PLAIN,18));
        jtf.setBounds(14,13,428,55);
        jp.add(jtf);
        jtf.setColumns(20);
        jtf.setHorizontalAlignment(JTextField.RIGHT);//右对齐
        jtf.setEditable(false);

        jtjudge =new JTextField("加油，别偷懒哦");
        jtjudge.setFont(new Font("宋体",Font.PLAIN,18));
        jtjudge.setBounds(514,13,400,55);
        jp.add(jtjudge);
        jtjudge.setColumns(20);
        jtjudge.setHorizontalAlignment(JTextField.RIGHT);
        jtjudge.setEditable(false);

        num1=new JButton("1");
        num1.setBounds(54,288,97,78);
        jp.add(num1);

        num2 = new JButton("2");
        num2.setBounds(165, 288, 97, 78);
        jp.add(num2);

        num3 = new JButton("3");
        num3.setBounds(276, 288, 97, 78);
        jp.add(num3);

        num4 = new JButton("4");
        num4.setBounds(54, 197, 97, 78);
        jp.add(num4);

        num5 = new JButton("5");
        num5.setBounds(165, 197, 97, 78);
        jp.add(num5);

        num6 = new JButton("6");
        num6.setBounds(276, 197, 97, 78);
        jp.add(num6);

        num7 = new JButton("7");
        num7.setBounds(54, 106, 97, 78);
        jp.add(num7);

        num8 = new JButton("8");
        num8.setBounds(165, 106, 97, 78);
        jp.add(num8);

        num9 = new JButton("9");
        num9.setBounds(276, 106, 97, 78);
        jp.add(num9);

        num0 = new JButton("0");
        num0.setBounds(165, 379, 97, 78);
        jp.add(num0);

        restart = new JButton("开始/重新开始");
        restart.setBounds(480, 106, 117, 78);
        jp.add(restart);

        delete = new JButton("回退");
        delete.setBounds(600,106,87,78);
        jp.add(delete);

        clear = new JButton("清除");
        clear.setBounds(700,106,87,78);
        jp.add(clear);

        confirm = new JButton("提交");
        confirm.setBounds(600,200,87,78);
        jp.add(confirm);

        answer = new JButton("显示答案");
        answer.setBounds(500,200,87,78);
        jp.add(answer);

        next = new JButton("下一题");
        next.setBounds(700,200,87,78);
        jp.add(next);

        exp = new JButton("更高难度");
        exp.setBounds(500,300,87,78);
        jp.add(exp);

        status=new JComboBox();
        status.setFont(new Font("宋体",Font.PLAIN,28));
        status.addItem("历史战绩");

        demoimage.setAdjustmentWindow(this);
        status.setBounds(400,400,400,50);
        jp.add(status);
        registerlistener();
        setVisible(true);


    }
    public void registerlistener(){
        btnlistener listener=new btnlistener();
        num0.addActionListener(listener);
        num1.addActionListener(listener);
        num2.addActionListener(listener);
        num3.addActionListener(listener);
        num4.addActionListener(listener);
        num5.addActionListener(listener);
        num6.addActionListener(listener);
        num7.addActionListener(listener);
        num8.addActionListener(listener);
        num9.addActionListener(listener);
        restart.addActionListener(listener);
        confirm.addActionListener(listener);
        next.addActionListener(listener);
        exp.addActionListener(listener);
        btnlistener2 listener2=new btnlistener2();
        answer.addActionListener(listener2);
        clear.addActionListener(listener2);
        delete.addActionListener(listener2);
    }


    class btnlistener2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            //显示答案
            if (e.getSource()==answer){
                if (!(jtft.equals("Right")||jtft.equals("False"))){
                    String str=Delete.delete.mydelete(jtft);
                    jtjudge.setText("正确答案"+str+String.valueOf(Ans));
                }
            }
            //清除答案
            if (e.getSource()==clear){
                jtf.setText(jtft=Delete.delete.mydelete(jtft));
                ans="";
            }
            //回退一位
            if (e.getSource()==delete){
                jtf.setText(jtft=Delete.delete.mybackspace(jtft));
                ans=Delete.delete.mybackspace(ans);
            }
        }
    }


    class btnlistener implements ActionListener {
        boolean tem=false;
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()instanceof JButton){
                if (e.getSource()==num0||e.getSource()==num1||e.getSource()==num2||e.getSource()==num3||e.getSource()==num4||e.getSource()==num5||e.getSource()==num6||e.getSource()==num7||e.getSource()==num8||e.getSource()==num9){
                    if (tem){
                        jtft+=((JButton) e.getSource()).getText();
                        ans+=((JButton) e.getSource()).getText();
                        jtf.setText(jtft);//更新JTextField中的显示字符串
                    }
                }
                if (e.getSource()==confirm){
                    tem=false;
                    if(Integer.parseInt(ans)==Ans)
                    {
                        status.addItem(jtft+"   正确");
                        ans="";
                        jtft="Right";
                        jtf.setText(jtft);
                    }
                    else {
                        status.addItem(jtft+"   错误");
                        String str=Delete.delete.mydelete(jtft);
                        jtjudge.setText("正确答案"+str+String.valueOf(Ans));
                        ans="";
                        jtft="False";
                        jtf.setText(jtft);
                    }
                }
                if (e.getSource()==next||e.getSource()==restart){
                    if (e.getSource()==restart){
                        status.removeAllItems();
                        status.addItem("历史战绩");
                        choose=false;
                    }
                    tem=true;
                    jtjudge.setText("好好加油，不要偷懒哦！");
                    if (!choose){
                        ans="";
                        Randomcompute();
                    }
                    else {
                        ans="";
                        Expression ex=new Expression();
                        String s=ex.expression();
                        jtft= s+"=";
                        jtf.setText(jtft);
                        Ans=Expression.result(s);
                    }
                }
                if (e.getSource()==exp){
                    choose=true;
                    jtjudge.setText("下一题难度变高了，要仔细哦！");
                }
            }
        }
        public void Randomcompute(){
            int a=(int)(1+Math.random()*(100-1+1));
            int b=(int)(1+Math.random()*(100-1+1));
            int c=(int)(1+Math.random()*(3-1+1));
            Num1=String.valueOf(a);
            Num2=String.valueOf(b);
            if(c==1){
                Operater="+";
                Ans=a+b;
            }
            else if(c==2)
            {
                while (a<b){
                    a=(int)(1+Math.random()*(10-1+1));
                    b=(int)(1+Math.random()*(10-1+1));
                    Num1=String.valueOf(a);
                    Num2=String.valueOf(b);
                }
                Operater="-";
                Ans=a-b;
            }
            else if(c==3)
            {
                Operater="*";
                Ans=a*b;
            }


            jtft=Num1+Operater+Num2+"=";
            jtf.setText(jtft);
        }
    }
}
