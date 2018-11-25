package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<Employee> employeeList;
    private Connection conn;
    private static Repository instance;

    private Repository() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
    }

    public static Repository getInstance() throws SQLException {
        if (instance == null || instance.conn.isClosed()) {
            instance = new Repository();
        }
        return instance;
    }

    // ADD 
    public void addEmployee(Employee e) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("INSERT INTO employees VALUES (null, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.execute();

        ResultSet keys = st.getGeneratedKeys();
        keys.next();
        int key = keys.getInt(1);
        e.setId(key);
    }

    // SEARCH
    public List<Employee> searchAll() throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees");
        ResultSet rs = st.executeQuery();
        employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee res = new Employee();
            res.setId(rs.getInt("id"));
            res.setName(rs.getString("name"));
            res.setAge(rs.getInt("age"));
            res.setAddress(rs.getString("address"));
            res.setSalary(rs.getDouble("salary"));
            employeeList.add(res);
        }

        return employeeList;
    }

    public Employee searchById(int id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Employee res = new Employee();

        res.setId(rs.getInt("id"));
        res.setName(rs.getString("name"));
        res.setAge(rs.getInt("age"));
        res.setAddress(rs.getString("address"));
        res.setSalary(rs.getDouble("salary"));

        return res;
    }

    public List<Employee> searchByName(String name) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees WHERE name LIKE ?");
        st.setString(1, "%" + name + "%");
        ResultSet rs = st.executeQuery();
        employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee res = new Employee();
            res.setId(rs.getInt("id"));
            res.setName(rs.getString("name"));
            res.setAge(rs.getInt("age"));
            res.setAddress(rs.getString("address"));
            res.setSalary(rs.getDouble("salary"));
            employeeList.add(res);
        }

        return employeeList;
    }

    public List<Employee> searchByAge(int age) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees WHERE age = ?");
        st.setInt(1, age);
        ResultSet rs = st.executeQuery();
        employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee res = new Employee();
            res.setId(rs.getInt("id"));
            res.setName(rs.getString("name"));
            res.setAge(rs.getInt("age"));
            res.setAddress(rs.getString("address"));
            res.setSalary(rs.getDouble("salary"));
            employeeList.add(res);
        }

        return employeeList;
    }

    public List<Employee> searchByAddress(String address) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees WHERE address LIKE ?");
        st.setString(1, "%" + address + "%");
        ResultSet rs = st.executeQuery();
        employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee res = new Employee();
            res.setId(rs.getInt("id"));
            res.setName(rs.getString("name"));
            res.setAge(rs.getInt("age"));
            res.setAddress(rs.getString("address"));
            res.setSalary(rs.getDouble("salary"));
            employeeList.add(res);
        }

        return employeeList;
    }

    public List<Employee> searchBySalary(double salary) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM employees WHERE salary = ?");
        st.setDouble(1, salary);
        ResultSet rs = st.executeQuery();
        employeeList = new ArrayList<>();

        while (rs.next()) {
            Employee res = new Employee();
            res.setId(rs.getInt("id"));
            res.setName(rs.getString("name"));
            res.setAge(rs.getInt("age"));
            res.setAddress(rs.getString("address"));
            res.setSalary(rs.getDouble("salary"));
            employeeList.add(res);
        }

        return employeeList;
    }

    // UPDATE 
    public void updateEmployeeById(Employee e, int id) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE employees SET name = ?, age = ?, address = ?, salary = ? WHERE id = ?");
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.setInt(5, id);
        st.execute();
    }

    //These should not be used when there are more than one employee with the same data
    public void updateEmployeeByName(Employee e, String name) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE employees SET name = ?, age = ?, address = ?, salary = ? WHERE name = ?");
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.setString(5, name);
        st.execute();
    }

    public void updateEmployeeByAge(Employee e, int age) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE employees SET name = ?, age = ?, address = ?, salary = ? WHERE age = ?");
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.setInt(5, age);
        st.execute();
    }

    public void updateEmployeeByAddress(Employee e, String address) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE employees SET name = ?, age = ?, address = ?, salary = ? WHERE address = ?");
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.setString(5, address);
        st.execute();
    }

    public void updateEmployeeBySalary(Employee e, double salary) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE employees SET name = ?, age = ?, address = ?, salary = ? WHERE salary = ?");
        st.setString(1, e.getName());
        st.setInt(2, e.getAge());
        st.setString(3, e.getAddress());
        st.setDouble(4, e.getSalary());
        st.setDouble(5, salary);
        st.execute();
    }

    // DELETE
    public void deleteEmployeeById(int id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
        st.setInt(1, id);
        st.execute();
    }

    //These should not be used when there are more than one employee with the same data
    public void deleteEmployeeByName(String name) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE name = ?");
        st.setString(1, name);
        st.execute();
    }

    public void deleteEmployeeByAge(int age) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE age = ?");
        st.setInt(1, age);
        st.execute();
    }

    public void deleteEmployeeByAddress(String address) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE address = ?");
        st.setString(1, address);
        st.execute();
    }

    public void deleteEmployeeBySalary(double salary) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE salary = ?");
        st.setDouble(1, salary);
        st.execute();
    }
}
