package controller;

import exceptions.EstudianteNoInscritoCursoException;
import exceptions.EstudianteYaInscritoException;
import interfaces.ServiciosAcademicosI;
import model.Curso;
import model.Estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorAcademico implements ServiciosAcademicosI {

    private List<Estudiante> estudiantes;
    private List<Curso> cursos;
    private Map<Curso, List<Estudiante>> inscripciones;

    public GestorAcademico(){
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if(!estudiantes.contains(estudiante)) estudiantes.add(estudiante);
    }

    @Override
    public void agregarCurso(Curso curso) {
        if(!cursos.contains(curso)){
            cursos.add(curso);
            inscripciones.put(curso, new ArrayList<>());//el nuevo curso debe contener un array para inscribir a los estudiantes
        }

    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        Curso curso = buscarCursoPorId(idCurso);
        if(curso == null) throw new IllegalArgumentException("Curso no encontrado");
        if(inscripciones.get(curso).contains(estudiante)) throw new EstudianteYaInscritoException("Estudiante ya enrolado en el curso.");
        inscripciones.get(curso).add(estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoCursoException {
        Curso curso = buscarCursoPorId(idCurso);
        if(curso == null) throw new IllegalArgumentException("Curso no encontrado");
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        if(estudiante == null || !inscripciones.get(curso).contains(estudiante))
            throw new EstudianteNoInscritoCursoException("Estudiante no enrolado en el curso.");
        inscripciones.get(curso).remove(estudiante);
    }

    public Curso buscarCursoPorId(int idCurso){
        return cursos.stream()
                .filter(c -> c.getId() == idCurso)
                .findFirst()
                .orElse(null);
    }

    public Estudiante buscarEstudiantePorId(int id){
        return estudiantes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void mostrarEstudiantesInscritos() {
        int numeracion = 1;
        for (Map.Entry<Curso, List<Estudiante>> entry : inscripciones.entrySet()) {
            Curso curso = entry.getKey();
            List<Estudiante> inscritos = entry.getValue();

            System.out.println("Curso ID: " + curso.getId() + " - " + curso.getNombre());
            System.out.println("Estudiantes inscritos:");
            for (Estudiante estudiante : inscritos) {
                System.out.println(numeracion + ". " + estudiante.getNombre() + " " + estudiante.getApellido() + " | " + estudiante.getEstado() + " | " + estudiante.getFechaDeNacimiento());
                numeracion++;
            }
            numeracion = 1;
            System.out.println();
        }
    }
}
