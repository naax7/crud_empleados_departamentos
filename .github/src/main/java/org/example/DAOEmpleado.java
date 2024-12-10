package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEmpleado {
    private Connection connection;

    public DAOEmpleado() throws SQLException {
        this.connection = DB.getConnection();
    }

    public List<DTOEmpleado> getAllEmpleados() throws SQLException {
        List<DTOEmpleado> empleados = new ArrayList<>();
        String query = "SELECT id, nombre, edad, dpto_id FROM empleados";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DTOEmpleado empleado = new DTOEmpleado(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("edad"),
                        resultSet.getInt("dpto_id")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    public DTOEmpleado getEmpleadoById(int id) throws SQLException {
        String query = "SELECT id, nombre, edad, dpto_id FROM empleados WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new DTOEmpleado(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("edad"),
                            resultSet.getInt("dpto_id")
                    );
                }
            }
        }
        return null;
    }

    public ArrayList<DTOEmpleado> getEmpleadosByDpto_Id(int id) throws SQLException {
        ArrayList<DTOEmpleado> empleados = new ArrayList<>();
        String query = "SELECT id, nombre, edad, dpto_id FROM empleados WHERE dpto_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            empleados.add(new DTOEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getInt(4)));
        }
        return empleados;
    }

    public void addEmpleado(DTOEmpleado empleado) throws SQLException {
        String query = "INSERT INTO empleados (nombre, edad, dpto_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado.getNombre());
            statement.setInt(2, empleado.getEdad());
            statement.setInt(3, empleado.getDpto_id());
            statement.executeUpdate();
        }
    }

    public void updateEmpleado(DTOEmpleado empleado) throws SQLException {
        String query = "UPDATE empleados SET nombre = ?, edad = ?, dpto_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado.getNombre());
            statement.setInt(2, empleado.getEdad());
            statement.setInt(3, empleado.getDpto_id());
            statement.setInt(4, empleado.getId());
            statement.executeUpdate();
        }
    }

    public void deleteEmpleado(int id) throws SQLException {
        String query = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
