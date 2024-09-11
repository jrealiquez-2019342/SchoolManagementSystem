import controller.GestorAcademico;
import exceptions.EstudianteNoInscritoCursoException;
import exceptions.EstudianteYaInscritoException;
import model.Curso;
import model.Estudiante;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GestorAcademico gestor = new GestorAcademico();

        //creacion de estudiantes
        Estudiante estudiante1 = new Estudiante(
                1,
                "Joshua",
                "Realiquez",
                LocalDate.of(2005, 7, 19),
                Estudiante.Estado.MATRICULADO
        );
        Estudiante estudiante2 = new Estudiante(
                2,
                "Juan",
                "Pérez",
                LocalDate.of(2000, 5, 15),
                Estudiante.Estado.MATRICULADO
        );

        //creacion de cursos
        Curso curso1 = new Curso(
                101,
                "Matematicas",
                "Curso de matematicas algebraica",
                5,
                1
        );
        Curso curso2 = new Curso(
                201,
                "Programacion Orientada a Objetos",
                "Curso de programacion orientada a objetos con JAVA Spring Boot.",
                15,
                3
        );

        //inscribir estudiantes
        try{
            gestor.matricularEstudiante(estudiante1);
            gestor.matricularEstudiante(estudiante2);
        }catch (Exception e){
            System.out.println("Error al inscribir a los estudiantes: " + e.getMessage());
        }

        //agregar curso a pensum
        try {
            gestor.agregarCurso(curso1);
            gestor.agregarCurso(curso2);
        }catch (Exception e){
            System.out.println("Error al agregar los cursos: " + e.getMessage());
        }

        //enrolar estudiantes a sus cursos
        try {
            gestor.inscribirEstudianteCurso(estudiante1, 101);
            gestor.inscribirEstudianteCurso(estudiante1, 201);
            gestor.inscribirEstudianteCurso(estudiante2, 201);
            System.out.println("Estudiantes enrolados exitosamente.");
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Error al enrolar estudiantes: " + e.getMessage());
        }


        gestor.mostrarEstudiantesInscritos();
    }
}
