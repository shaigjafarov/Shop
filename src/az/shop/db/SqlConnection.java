package az.shop.db;

import az.shop.dto.eNum.Gender;
import az.shop.dto.eNum.Starred_Products;
import az.shop.dto.userSignUP.User;
import az.shop.model.Shoes;
import az.shop.model.Tshirts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SqlConnection {
    public static void main(String[] args) {
//        SqlConnection sqlConnection = new SqlConnection();
//        List<Shoes> shoeList = sqlConnection.getAllShoes();
//        shoeList.forEach(System.out::println);
//        boolean s = sqlConnection.deleteShoesById(shoeList.get(0).getId());
//        if (s) {
//            System.out.println("silindi");
//        } else System.out.println("silinmedi");
//        List<Shoes> shoeListAfterUpdate = sqlConnection.getAllShoes();
//        shoeListAfterUpdate.forEach(System.out::println);

        UserRepository uu =new UserRepositoryImpl();
//        uu.addUser(new User("Emin", "Huseyzada", "emin","pass1", 13));
        System.out.println(uu.checkUserNamePass("eminn","pass"));
//        List<Tshirts> tshirts = sqlConnection.getAllTshirts();
//        tshirts.forEach(System.out::println);
//        boolean t = sqlConnection.deleteTshirtsById(tshirts.get(0).getId());
//        if (t) {
//            System.out.println("silindi");
//        } else System.out.println("silinmedi");
//        List<Tshirts> tshirtsListAfterUpdate = sqlConnection.getAllTshirts();
//        tshirtsListAfterUpdate.forEach(System.out::println);

//        SqlConnection s= new SqlConnection();
//        s.addShoes(new Shoes(Gender.MALE,"brend","34","color", 34, Starred_Products.FIVE_STAR_PRODUCT));
    }

    boolean addShoes(Shoes shoe) {
        boolean result = false;
        try {
            Connection connection = DBConfig.getConnection();
            String sql = "INSERT INTO nese.shoes (id,gender, brand, size, colour, price) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "MALE");
            preparedStatement.setString(3, "puma");
            preparedStatement.setString(4, "42");
            preparedStatement.setString(5, "grey");
            preparedStatement.setInt(6, 30);
            result = preparedStatement.execute();
            connection.close();
            System.out.println("Connected to PostgreSQL server");
        } catch (SQLException e) {
            System.out.println("Error in connectiong to PostgreSQL server");
            e.printStackTrace();
        }
        return result;
    }

    public List<Shoes> getAllShoes() {
        Connection connection = null;
        try {
            List<Shoes> shoeList = new ArrayList<>();
            String sql = "SELECT id, gender, brand, size, colour, price  FROM nese.shoes";
            connection = DBConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Shoes shoe = new Shoes(rs.getInt(1), Gender.valueOf(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                shoeList.add(shoe);
            }
            DBConfig.closeConnection(connection);
            return shoeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean deleteShoesById(int shoeId) {
        Connection connection = null;
        try {
            String sql = "delete  from  nese.shoes where   id=?";
            connection = DBConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shoeId);
            int i = preparedStatement.executeUpdate();
            DBConfig.closeConnection(connection);
            if (i > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean addTshirts(Tshirts tshirts) {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/localhost", "postgres", "noreturns13");
            String sql = "INSERT INTO nese.shoes (id,gender, brand, body, colour, price) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "kisi");
            preparedStatement.setString(3, "madmext");
            preparedStatement.setString(4, "L");
            preparedStatement.setString(5, "black");
            preparedStatement.setInt(6, 110);
            result = preparedStatement.execute();
            connection.close();
            System.out.println("Connected to PostgreSQL server");
        } catch (SQLException e) {
            System.out.println("Error in connectiong to PostgreSQL server");
            e.printStackTrace();
        }
        return result;
    }

    public List<Tshirts> getAllTshirts() {
        Connection connection = null;
        try {
            List<Tshirts> tshirtsList = new ArrayList<>();
            String sql = "SELECT id, gender, brand, body, colour, price, FROM nese.shoes";
            connection = DBConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tshirts tshirts = new Tshirts(rs.getInt(1), Gender.valueOf(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                tshirtsList.add(tshirts);
            }
            DBConfig.closeConnection(connection);
            return tshirtsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean deleteTshirtsById(int tshirtsId) {
        Connection connection = null;
        try {
            String sql = "delete  from  nese.shoes where   id=?";
            connection = DBConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tshirtsId);
            int i = preparedStatement.executeUpdate();
            DBConfig.closeConnection(connection);
            if (i > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}