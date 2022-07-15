import models.Etudiant;
import models.Evaluation;
import models.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Application {

    public static List<Etudiant> getData(){
        Etudiant etudiant1 = new Etudiant("bansept", "franck");
        etudiant1.getListeNote().add(new Note("JAVA", 15));
        etudiant1.getListeNote().add(new Note("PHP", 10));
        etudiant1.getListeNote().add(new Note("UML", 5));

        Etudiant etudiant2 = new Etudiant("doe", "simon");
        etudiant2.getListeNote().add(new Note("JAVA", 17));
        etudiant2.getListeNote().add(new Note("PHP", 18));

        Etudiant etudiant3 = new Etudiant("stark", "sansa");
        etudiant3.getListeNote().add(new Note("C#", 1));
        etudiant3.getListeNote().add(new Note("AVA", 19));


        List<Etudiant> listeEtudiant = new ArrayList<>();
        listeEtudiant.add(etudiant1);
        listeEtudiant.add(etudiant2);
        listeEtudiant.add(etudiant3);
        return listeEtudiant;
    }

    public static void main(String[] args) {
        
        exercice1();
        
        exercice2();
        
        exercice3();

       
    }

    public static void exercice1() {
        List<Etudiant> data = getData();

        String text = data.stream()
                .map( etudiant -> etudiant.getNom().toLowerCase(Locale.ROOT) + "." + etudiant.getPrenom().toLowerCase(Locale.ROOT) + "@cesi.com")
                .collect(Collectors.joining(" - "));
        System.out.println(text);
    }

    public static void exercice2(){
        List<Etudiant> data = getData();

        System.out.println(
                data.stream()
                        .filter(etudiant -> etudiant.getPrenom().toLowerCase(Locale.ROOT).charAt(0) == 's')
                        .sorted(Comparator.comparing(etudiant -> -etudiant.getListeNote().stream().mapToInt(note -> note.getNote()).max().getAsInt()))
                        .map(etudiant -> etudiant.getNom() + " " + etudiant.getPrenom())
                        .findFirst().get());
    }

    public static  void exercice3(){
        List<Etudiant> data = getData();

        List<Evaluation> listNote = data.stream()
                .map(etudiant -> etudiant.getListeNote().stream()
                        .map(note -> new Evaluation(etudiant,note.getNote())).collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        listNote.forEach(note -> System.out.println(note.getPersonne().getPrenom() + " " + note.getNote()));
    }

  


}
