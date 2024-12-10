package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODepartamento {
    private Connection connection;
    private DAOEmpleado daoEmpleado;

    public DAODepartamento() throws SQLException {
        this.connection = DB.getConnection();
        this.daoEmpleado = new DAOEmpleado();
    }

    public List<DTODepartamento> getAllDepartamentos() throws SQLException {
        List<DTODepartamento> departamentos = new ArrayList<>();
        String query = "SELECT id, nombre FROM departamento";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DTODepartamento departamento = new DTODepartamento(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                );
                departamento.setEmpleados(daoEmpleado.getEmpleadosByDpto_Id(resultSet.getInt("id")));
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }

    public DTODepartamento getDepartamentoById(int id) throws SQLException {
        String query = "SELECT id, nombre FROM departamento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DTODepartamento departamento = new DTODepartamento(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre")
                    );
                    departamento.setEmpleados(daoEmpleado.getEmpleadosByDpto_Id(resultSet.getInt("id")));
                    return departamento;
                }
            }
        }
        return null;
    }

    public void addDepartamento(DTODepartamento departamento) throws SQLException {
        String query = "INSERT INTO departamento (nombre) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departamento.getNombre());
            statement.executeUpdate();
        }
    }

    public void updateDepartamento(DTODepartamento departamento) throws SQLException {
        String query = "UPDATE departamento SET nombre = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departamento.getNombre());
            statement.setInt(2, departamento.getId());
            statement.executeUpdate();
        }
    }

    public void deleteDepartamento(int id) throws SQLException {
        String query = "DELETE FROM departamento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
