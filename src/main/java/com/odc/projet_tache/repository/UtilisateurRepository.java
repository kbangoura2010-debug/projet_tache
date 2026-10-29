package com.odc.projet_tache.repository;
// Cette classe appartient au package "repository".
//
// Le Repository est la partie de l'application qui permet
// de communiquer avec la base de données.


import com.odc.projet_tache.model.Utilisateur;
// On importe la classe Utilisateur.
//
// Pourquoi ?
// Parce que ce Repository va travailler avec l'entité Utilisateur.
//
// Il doit donc savoir :
// "Je vais travailler avec la classe Utilisateur."


import org.springframework.data.jpa.repository.JpaRepository;
// On importe JpaRepository.
//
// JpaRepository fournit déjà beaucoup de méthodes permettant
// de travailler avec la base de données.
//
// Par exemple :
// findAll()
// findById()
// save()
// deleteById()
// etc.


public interface UtilisateurRepository
        extends JpaRepository<Utilisateur, Long> {

    }