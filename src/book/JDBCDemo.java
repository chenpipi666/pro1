package book;
import java.sql.*;
import java.util.*;
import java.util.Date;
/**
 * Created by Administrator on 2018/7/17.
 */
public class JDBCDemo {
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/books";
            try {
                Connection connection = DriverManager.getConnection(dbURL,
                        "root","root");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void InsertDate(String book_name, String book_publishers, String book_author, String create_time) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            String sql = "insert into book " +
                    "values('"+ book_name +"','"+ book_publishers +"','"+ book_author +"',"+create_time+")";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行数为：" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    private void DeleteDate(int id) {
        Connection connection = null;
        Statement statement = null;
        try {

            connection = getConnection();
            String sql = "delete from book where id=" + id +"";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    private void UpdateDate(String book_name, String book_pulishers, String book_author) {
        Connection connection = null;
        Statement statement = null;

        try {

            connection = getConnection();
            String sql = "update book set book_name='"+book_name+"',book_pulishers='"+ book_pulishers+"' where book_name= '"+ book_name +"'";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为：" + (rows > 0));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    private String [][] bestFindAllData() {
        String [][] datas = new String [100][3];
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from book";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int index = 0;
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String book_name = resultSet.getString("book_name");
                String book_publishers = resultSet.getString("book_publishers");
                String book_author = resultSet.getString("book_author");
                String create_time = resultSet.getString("create_time");
                datas[index][0] = id + "";
                datas[index][1] = book_name;
                datas[index][2] = book_publishers;
                datas[index][3] = book_author;
                datas[index][4] = create_time;
                index ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
        return datas;
    }
    private void findAllDate() {
        String [][] datas = bestFindAllData();
        StringBuffer buffer = new StringBuffer();
        buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
        buffer.append("id\t\t\tbook_name\t\t\tbook_pulishers\t\t\tbook_author\t\t\tcreate_time"+System.lineSeparator());
        buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
        for(int i = 0; i < datas.length; i ++) {
            String [] values = datas[i];
            if(values[0] != null && values[1] != null && values[2] != null) {
                buffer.append(
                        String.format(
                                "%s\t|%s\t|%s\t|%s\t", values[0], values[1], values[2],values[3],values[4]));
                buffer.append(System.lineSeparator());
            }
        }
        System.out.println(buffer.toString());
    }
    private void findAccountDataLikeKeyWord(String keyWord) {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select book_name,book_publishers,book_author from book " +
                "where book_name like '%" + keyWord + "%' or book_publishers like '%" + keyWord + "%'or book_author like '%" + keyWord + "%'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            StringBuffer buffer = new StringBuffer();
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            buffer.append("id\t\t\tbook_name\t\t\tbook_pulishers\t\t\tbook_author\t\t\tcreate_time" + System.lineSeparator());
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String book_name = resultSet.getString("book_name");
                String book_publishers = resultSet.getString("book_publishers");
                String book_author = resultSet.getString("book_author");
                String create_time = resultSet.getString("create_time");;
                buffer.append(id+"|\t"+book_name+"|\t"+book_publishers+"|\t"+book_author+"|\t"+create_time+"|"+System.lineSeparator());
            }

            System.out.println(buffer.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
    }

    private void close(Connection connection,
                       Statement statement,
                       ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        JDBCDemo jdbcDemo = new JDBCDemo();
            System.out.println("============================================================================");
            System.out.println("|                                  欢迎使用                                 |");
            System.out.println("1、添加数据   2、修改数据  3、删除数据  4、查询所有书籍  5、模糊搜索  6、退出系统");
            System.out.println("============================================================================");

            while (1==1){
                System.out.println("请选择你的操作：");
                int select = 0;
                select = scanner.nextInt();
                while (select < 1 || select > 6) {
                    System.out.println("选择的操作数不能识别，请重新选择：");
                    select = scanner.nextInt();
                }
                String value = null;
                JDBCDemo jdbcDemo1= new JDBCDemo();
                if (select == 1) {
                    System.out.println("请输入要添加的书籍名，出版社和书籍作者，时间中间用逗号分隔。举例：朝花夕拾,鲁迅,浙江出版社,18.11.1");
                    value = scanner.next();
                    String [] values = value.split(",");
                    jdbcDemo.InsertDate(values[0],values[1],values[2],values[3]);
                } else if (select == 2) {
                    System.out.println("请输入要修改的书籍名，出版社，书籍作者，逗号分隔，系统将根据书籍名修改");
                    value = scanner.next();
                    String [] values = value.split(",");
                    jdbcDemo.UpdateDate(values[0],values[1],values[2]);
                } else if (select == 3) {
                    System.out.println("请输入要删除的ID");
                    value = scanner.next();
                    jdbcDemo.DeleteDate(Integer.parseInt(value));
                } else if(select==4){
                    jdbcDemo.findAllDate();
                }else if(select==5){
                    System.out.println("请输入关键字");
                    value = scanner.next();
                    jdbcDemo.findAccountDataLikeKeyWord(value);
                }else  if(select==6){
                    System.exit(-1);
                }
            }
        }
}
