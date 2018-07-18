package demo;

/**
 * Created by Administrator on 2018/7/14.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MyFrame extends JFrame{
    public MyFrame(){

        this.setVisible(true);
        this.setSize(300,200);
        this.setTitle("my frame");
//		FlowLayout flowLayout=new FlowLayout();
//		this.setLayout(flowLayout);
        GridLayout gridLayout=new GridLayout(3,2);
        this.setLayout(gridLayout);



        JButton btNorth= new JButton("东");
        JButton btSouth= new JButton("西");
        JButton btEast= new JButton("南");
        JButton btWest= new JButton("北");
        JButton btCenter= new JButton("中");

        this.add(btNorth);
        this.add(btSouth);
        this.add(btEast);
        this.add(btWest);
        this.add(btCenter);

//		this.add(btNorth,BorderLayout.NORTH);
//		this.add(btSouth,BorderLayout.SOUTH);
//		this.add(btEast,BorderLayout.EAST);
//		this.add(btWest,BorderLayout.WEST);
//		this.add(btCenter,BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void test(){

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MyFrame test = new MyFrame();
    }

}
