package org.example.Exception;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class Client {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try {
            int ans = calculator.divide(10, 0);
            System.out.println(ans);
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
