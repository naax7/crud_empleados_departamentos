package org.example;

import java.sql.SQLException;
import java.util.Scanner;
public class Consola {
    private DAOEmpleado daoEmpleado;
    private DAODepartamento daoDepartamento;
    public void menu() throws SQLException {
        daoDepartamento = new DAODepartamento();
        daoEmpleado = new DAOEmpleado();
        System.out.println("1. listar todos empleados\n" +
                "2. listar todos departamentos\n" +
                "3. listar todos los empleados de un departamento\n" +
                "4. actualizar un empleado\n" +
                "5. actualizar un departamento\n" +
                "6. eliminar un empleado por id\n" +
                "7. eliminar un departamento por id\n" +
                "8. insertar un empleado\n" +
                "9. insertar un departamento");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch(opcion){
            case 1:
                System.out.println(daoEmpleado.getAllEmpleados());
                break;
            case 2:
                System.out.println(daoDepartamento.getAllDepartamentos());
                break;
            case 3:
                System.out.println("Departamento: ");
                System.out.println(daoEmpleado.getEmpleadosByDpto_Id(sc.nextInt()));
                break;
            case 4:
                System.out.println("id empleado: ");
                DTOEmpleado empleado = daoEmpleado.getEmpleadoById(sc.nextInt());
                System.out.println(empleado);
                System.out.println("Nuevo nombre: ");
                empleado.setNombre(sc.next());
                System.out.println("Nueva edad: ");
                empleado.setEdad(sc.nextInt());
                System.out.println("Nuevo departamento: ");
                empleado.setDpto_id(sc.nextInt());
                daoEmpleado.updateEmpleado(empleado);
                break;
            case 5:
                System.out.println("id departamento: ");
                DTODepartamento departamento = daoDepartamento.getDepartamentoById(sc.nextInt());
                System.out.println(departamento);
                System.out.println("Nuevo nombre: ");
                departamento.setNombre(sc.nextLine());
                daoDepartamento.updateDepartamento(departamento);
                break;
            case 6:
                System.out.println("id empleado: ");
                daoEmpleado.deleteEmpleado(sc.nextInt());
                break;
            case 7:
                System.out.println("id departamento: ");
                daoDepartamento.deleteDepartamento(sc.nextInt());
                break;
            case 8:
                DTOEmpleado empleado2 = new DTOEmpleado(0, null, 0, 0);
                System.out.println("Nombre: ");
                empleado2.setNombre(sc.next());
                System.out.println("Edad: ");
                empleado2.setEdad(sc.nextInt());
                System.out.println("Departamento: ");
                empleado2.setDpto_id(sc.nextInt());
                daoEmpleado.addEmpleado(empleado2);
                break;
            case 9:
                DTODepartamento departamento2 = new DTODepartamento(0, null);
                System.out.println("Nombre: ");
                departamento2.setNombre(sc.next());
                daoDepartamento.addDepartamento(departamento2);
                break;
        }
    }
}
