package demo;

/**
 * Created by Administrator on 2018/7/14.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class HomePage extends JFrame{
    private JPanel panelSearch= new JPanel();
    private JPanel panelFrocess = new JPanel();
    private JTextField txtSearch = new JTextField(60);
    private JPanel panelAdd = new JPanel();
    private JLabel labelAddAccount = new JLabel("账号：");
    private JTextField txtAddAccount = new JTextField();
    private JLabel labelView = new JLabel("aaa");
    private LayoutManager BoerderLayout;
    private JButton btQuery = new JButton("Query");
    private JButton btAdd = new JButton("Add");
    private JButton btInsert= new JButton("Insert");
    private JButton btDelete= new JButton("Delete");
    private JButton btReturn= new JButton("Return");
    JTextArea txName = new JTextArea("用户名：");
    JTextField flName = new JTextField("123");
    JTextArea txPassWord = new JTextArea("密码：");
    JTextField flPassWord = new JTextField("456");
    JButton btShow= new JButton("show");
    JPanel panelCenter = new JPanel();
    private JLabel lbView = new JLabel("保存成功！");


    protected Object Frame1;


    public HomePage(){

        this.add(panelSearch,BorderLayout.NORTH);
        this.add(panelFrocess,BorderLayout.SOUTH);
        this.add(panelAdd);
        this.add(panelCenter,BorderLayout.CENTER);

        panelCenter.add(panelAdd);




        panelSearch.setLayout(new FlowLayout());
        panelSearch.add(txtSearch);
        panelSearch.add(btQuery);
        panelSearch.setBorder(new LineBorder(new Color(0,0,0)));

        panelAdd.setLayout(new GridLayout(4,2));
        panelAdd.add(labelAddAccount);
        panelAdd.add(txtAddAccount);
        panelAdd.setVisible(true);
        panelAdd.add(lbView);
        lbView.setVisible(false);

        panelFrocess.setVisible(true);
        panelFrocess.add(btAdd);
        panelFrocess.add(btDelete);


        this.setSize(800,600);
        this.setTitle("首页");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        btQuery.setActionCommand("Query");
        btAdd.setActionCommand("Add");
        ActionListener actionListener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String command = e.getActionCommand();

                if(command.equals("Query")){
                    panelAdd.setVisible(true);
                    HomePage.this.panelAdd.remove(labelAddAccount);
                    HomePage.this.panelAdd.remove(txtAddAccount);
                    lbView.setVisible(true);
//					btQuery.setText("jjjj");设置显示的文字
                }else if(command.equals("Add")){

                    panelCenter.setLayout(new GridLayout(2,2));
                    panelCenter.setVisible(true);
                    btAdd.setVisible(false);
                    btDelete.setVisible(false);
                    HomePage.this.panelCenter.remove(panelAdd);

                    panelFrocess.add(btInsert);
                    panelFrocess.add(btReturn);
                    panelSearch.setVisible(false);
                    flName.setBorder(new LineBorder(new Color(0, 0, 0)));
                    flPassWord.setBorder(new LineBorder(new Color(0, 0, 0)));

                    panelCenter.add(txName);
                    panelCenter.add(flName);
                    panelCenter.add(txPassWord);
                    panelCenter.add(flPassWord);
                }

            }

        };
        btQuery.addActionListener(actionListener);
        btAdd.addActionListener(actionListener);

//		btAdd.setActionCommand("Add");
//		ActionListener actionListener1 = new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String command = e.getActionCommand();
//
//				if(command.equals("Add")){
//					//!应该跳转到添加界
//
//					panelCenter.setLayout(new GridLayout(2,2));
//					panelCenter.setVisible(true);
//					btAdd.setVisible(false);
//					panelAdd.setVisible(false);
//					HomePage.this.remove(panelAdd);
//
//					panelFrocess.add(btInsert);
//					panelFrocess.add(btDelete);
//					panelFrocess.add(btReturn);
//					panelSearch.setVisible(false);
//					flName.setBorder(new LineBorder(new Color(0, 0, 0)));
//					flPassWord.setBorder(new LineBorder(new Color(0, 0, 0)));
//
//					 panelCenter.add(txName);
//					 panelCenter.add(flName);
//					 panelCenter.add(txPassWord);
//					 panelCenter.add(flPassWord);
//
//
//
//				}
//			}
//
//
//
//
//		};
//		btAdd.addActionListener(actionListener1);
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub

        HomePage test = new HomePage();
    }


}
