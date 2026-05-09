package cm.enspm.studia.service;

import java.util.List;
import java.util.stream.Collectors;

import cm.enspm.studia.mapper.ClasseMapper;
import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.administration.Classe;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.repository.ClasseRepository;

public class ServicesClasse {
    private final ClasseRepository classeRepository;

    public ServicesClasse(ClasseRepository repository) {
        this.classeRepository = repository;
    }

    public void enregistrerClasse(Classe classe) {
        if (classeRepository.RechercherClasseParNom(
                classe.getLibelle(), 
                classe.getAnneeScolare().getIdAnneeScolaire()).isPresent()) {
            throw new IllegalArgumentException("Le nom de la classe existe déjà dans le système.");
        }
        classeRepository.enregistrerClasse(ClasseMapper.toDto(classe));
    }

    public List<Classe> listerClasses(Integer anneeScolaire) {
        return classeRepository.getAllClasses(anneeScolaire).stream()
                .map(ClasseMapper::toDomain)
                .collect(Collectors.toList());
    }
}
