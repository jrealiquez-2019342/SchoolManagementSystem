package interfaces;

import exceptions.EstudianteNoInscritoCursoException;
import exceptions.EstudianteYaInscritoException;
import model.Curso;
import model.Estudiante;

public interface ServiciosAcademicosI {
    void matricularEstudiante(Estudiante estudiante);
    void agregarCurso(Curso curso);
    void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException;
    void desinscribirEstudianteCurso(int IdEstudiante, int idCurso) throws EstudianteNoInscritoCursoException;
}
