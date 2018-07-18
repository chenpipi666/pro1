package demo;

/**
 * Created by Administrator on 2018/7/14.
 */
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;

        import com.sun.xml.internal.bind.v2.schemagen.xmlschema.LocalAttribute;

        import java.sql.*;
        import java.util.Scanner;


/**
 *create by zn on
 * 演示通过jdbc连接数据库和进行增，删，改，查的操作
 */
public class Try {
    /**
     *演示通过jdbc连接数据库
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://127.0.0.1:3306/test";
            try {
                Connection connection = DriverManager.getConnection(dbURL,"root","root");
                return  connection;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

//    private void testInsertDate(String name, String number, int id) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/test",
//                    "root", "root");
////             String sql = "insert into acount (id,name,number) values (9,6,9)";
//            String sql = "insert into account values('"+ name +"','"+ number +"',"+ id +")";
//            Statement statement = connection.createStatement();
//            int rows = statement.executeUpdate(sql);
//            System.out.println("所影响的条数为："+(rows>0));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void Close(Connection connection,Statement statement,ResultSet resultSet){
        try {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void testInsertDate(String name, String number, int id) throws ClassNotFoundException {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root", "root");
//             String sql = "insert into acount (id,name,number) values (9,6,9)";
            String sql = "insert into account values('"+ name +"','"+ number +"',"+ id +")";
            statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的条数为："+(rows>0));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(connection,statement,resultSet=null);
        }
    }

    private  void testDeleteDate(int id){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root", "root");
//            String sql = "delete from account where id=1";
            String sql = "delete from account where id=" +id +"";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + "行被删除！");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Close(connection,statement,resultSet=null);
        }

    }

    private  void testUpdateDate(String name, String number, int id){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root", "root");
//            String sql = "UPDATE account set number=23 where id=6";
            String sql = "update account set name='"+name+"',number='"+ number+"' where id= "+id+"";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + "行被修改！");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Close(connection,statement,resultSet=null);
        }

    }
//    private void findAllDate(){
//        Connection connection =getConnection();
//        String sql = "select * from account";
//        try{
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try{
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("=========================================="+System.lineSeparator());
//        buffer.append("id\t\t\taccount\t\t\tnumber\t\t\t"+System.lineSeparator());
//        buffer.append("=========================================="+System.lineSeparator());
//            while (resultSet.next()){
//
//                String name = resultSet.getString(1);
//                String number = resultSet.getString(2);
//                int id = resultSet.getInt(3);
//                buffer.append(id+"|\t"+name+"|\t"+number+"|"+System.lineSeparator());
//            }
//            System.out.println(buffer.toString());
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
    private String[][] bestFindAllDate(){
        return  null;
    }//有问题

    private void findAllDateFormatOutput(){
        String [][] dates = bestFindAllDate();
            StringBuffer buffer = new StringBuffer();
            buffer.append("=========================================="+System.lineSeparator());
            buffer.append("id\t\t\taccount\t\t\tnumber\t\t\t"+System.lineSeparator());
            buffer.append("=========================================="+System.lineSeparator());
        for(int i = 0;i<dates.length;i++){
            String [] values = dates[i];
            if(values[0] != null && values[i] != null && values[2] != null){
                buffer.append(
                        String.format(
                                "%s\t|%s\t|%s",values[0],values[1],values[2]));
                buffer.append(System.lineSeparator());
            }
        }
    }

private  void  findAccountDataById(int id){
    Connection connection = getConnection();
    String sql="select * from account where id="+id+"";
    try{
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql) ;
        StringBuffer buffer=new StringBuffer();
        buffer.append("----------------------------------------------------------------------------------------"+System.lineSeparator());
        buffer.append("id\tname\tnumber"+System.lineSeparator());
        buffer.append("----------------------------------------------------------------------------------------"+System.lineSeparator());
        while (resultSet.next()){
            id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            String number=resultSet.getString("number");
            buffer.append(id+"\t\t\t"+name+"\t\t\t"+number+System.lineSeparator());
        }
        System.out.println(buffer.toString());
    }catch (SQLException e){
        e.printStackTrace();
    }
}

    //根据模糊搜索数据，根据用户输入的关键词来模糊搜索

//    private void findAccountDateLikeKey(String keyWord){
//        Connection connection = getConnection();
//        String sql="select * from account where name like '%"&request.form("keyWord")&"%' or number like '%"&request.form("keyWord")&"%'";
//        try{
//            Statement statement=connection.createStatement();
//            ResultSet resultSet=statement.executeQuery(sql) ;
//            StringBuffer buffer=new StringBuffer();
//            buffer.append("----------------------------------------------------------------------------------------"+System.lineSeparator());
//            buffer.append("id\tname\tnumber"+System.lineSeparator());
//            buffer.append("----------------------------------------------------------------------------------------"+System.lineSeparator());
//            while (resultSet.next()){
//                int id=resultSet.getInt("id");
//                String name=resultSet.getString("name");
//                String number=resultSet.getString("number");
//                buffer.append(id+"\t\t\t"+name+"\t\t\t"+number+System.lineSeparator());
//            }
//            System.out.println(buffer.toString());
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
    public static void main(String[] args) throws ClassNotFoundException {
        Try demo = new Try();





        Scanner scanner = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("|         欢迎使用HNB 11 人工智能系统           |");
        System.out.println("1、添加数据   2、修改数据  3、删除数据  4、退出系统");
        System.out.println("================================================");

        while (1==1){
        System.out.println("请选择你的操作：");
        int select = 0;
        select = scanner.nextInt();
        while (select < 1 || select > 4) {
            System.out.println("选择的操作数不能识别，请重新选择：");
            select = scanner.nextInt();
        }
        String value = null;
        Try test= new Try();
        if (select == 1) {
            System.out.println("请输入要添加的账号和密码，中间用逗号分隔。举例：133,简介");
            value = scanner.next();
            String [] values = value.split(",");
            test.testInsertDate(values[0],values[1],(int) System.currentTimeMillis());
        } else if (select == 2) {
            System.out.println("请输入要修改的账号，密码，ID，逗号分隔，系统将根据ID修改");
            value = scanner.next();
            String [] values = value.split(",");
            test.testUpdateDate(values[0],values[1],Integer.parseInt(values[2]));
        } else if (select == 3) {
            System.out.println("请输入要删除的ID");
            value = scanner.next();
            test.testDeleteDate(Integer.parseInt(value));
        } else if(select==4){
            System.exit(-1);
        }
        }
    }
}
//    select * from account;