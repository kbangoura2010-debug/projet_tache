package com.odc.projet_tache.model;
// Cette classe appartient au package "model".
// Le package model contient généralement les classes qui représentent
// les données de notre application.
// Ici, notre donnée principale est : Utilisateur.


import jakarta.persistence.*;
// On importe les annotations JPA.
// JPA permet de faire le lien entre nos classes Java
// et les tables de la base de données.


import lombok.AllArgsConstructor;
// Lombok va automatiquement créer un constructeur
// qui contient TOUS les attributs de la classe.


import lombok.Getter;
// Lombok va automatiquement créer les méthodes getXXX().


import lombok.NoArgsConstructor;
// Lombok va automatiquement créer un constructeur sans paramètre.


import lombok.Setter;
// Lombok va automatiquement créer les méthodes setXXX().



@Entity
// IMPORTANT.
//
// @Entity indique à JPA :
//
// "Cette classe Java doit être considérée comme une entité
// qui sera enregistrée dans une table de la base de données."
//
// Donc :
//
// Classe Java
//      ↓
// Utilisateur
//      ↓
// Table SQL
//      ↓
// utilisateurs


@Table(name = "utilisateurs")
// Indique le nom exact de la table MariaDB.
//
// Notre classe s'appelle :
// Utilisateur
//
// Mais notre table s'appelle :
// utilisateurs
//
// @Table permet de faire cette correspondance.


@Getter
// Lombok crée automatiquement les getters.
//
// Exemple :
// getNom()
// getPrenom()
// getEmail()
// getTelephone()
// etc.


@Setter
// Lombok crée automatiquement les setters.
//
// Exemple :
// setNom()
// setPrenom()
// setEmail()
// setTelephone()
// etc.


@NoArgsConstructor
// Lombok crée automatiquement un constructeur sans paramètre.
//
// Exemple :
//
// Utilisateur utilisateur = new Utilisateur();


@AllArgsConstructor
// Lombok crée automatiquement un constructeur
// contenant tous les attributs.
//
// Exemple conceptuel :
//
// new Utilisateur(
//     1L,
//     "Bangoura",
//     "Mohamed",
//     "mohamed@gmail.com",
//     "123456",
//     "620000000",
//     "ADMIN"
// );


public class Utilisateur {
    // Déclaration de la classe Utilisateur.



    @Id
    // Indique que cet attribut est la clé primaire (PRIMARY KEY)
    // de la table utilisateurs.


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Indique que l'ID est généré automatiquement par la base de données.
    //
    // C'est pour cela que dans MariaDB nous avons :
    //
    // id bigint(20) PRIMARY KEY AUTO_INCREMENT
    //
    // Exemple :
    //
    // Tu crées un utilisateur sans donner son ID.
    //
    // MariaDB crée :
    // 1
    //
    // Le suivant :
    // 2
    //
    // Le suivant :
    // 3
    // etc.


    private Long id;
    // Attribut représentant la colonne "id".
    //
    // Java :
    // Long id
    //
    // MariaDB :
    // id BIGINT



    @Column(name = "nom", nullable = false, length = 100)
    // Cette annotation indique comment l'attribut "nom"
    // correspond à la colonne SQL.
    //
    // name = "nom"
    // → nom de la colonne dans MariaDB.
    //
    // nullable = false
    // → la colonne ne peut pas être NULL.
    //
    // length = 100
    // → longueur maximale : 100 caractères.


    private String nom;
    // Contient le nom de l'utilisateur.



    @Column(name = "prenom", nullable = false, length = 100)
    // Correspond à la colonne "prenom".
    //
    // nullable = false
    // → obligatoire.
    //
    // length = 100
    // → maximum 100 caractères.


    private String prenom;
    // Contient le prénom de l'utilisateur.



    @Column(name = "email", nullable = false, unique = true, length = 150)
    // Correspond à la colonne "email".
    //
    // nullable = false
    // → l'email est obligatoire.
    //
    // unique = true
    // → deux utilisateurs ne peuvent pas avoir
    // le même email.
    //
    // length = 150
    // → maximum 150 caractères.


    private String email;
    // Contient l'adresse email.



    @Column(name = "mot_de_passe", nullable = false, length = 255)
    // Correspond à la colonne SQL :
    //
    // mot_de_passe
    //
    // nullable = false
    // → obligatoire.
    //
    // length = 255
    // → maximum 255 caractères.


    private String motDePasse;
    // Nom de l'attribut Java :
    //
    // motDePasse
    //
    // Mais le nom de la colonne MariaDB est :
    //
    // mot_de_passe
    //
    // C'est @Column(name = "mot_de_passe")
    // qui fait la correspondance.



    @Column(name = "telephone", length = 20)
    // Correspond à la colonne :
    //
    // telephone
    //
    // nullable n'est pas précisé.
    // Donc cette colonne peut être NULL.


    private String telephone;
    // Contient le numéro de téléphone.



    @Column(name = "role_application", nullable = false, length = 50)
    // Correspond à la colonne SQL :
    //
    // role_application
    //
    // nullable = false
    // → le rôle est obligatoire.
    //
    // length = 50
    // → maximum 50 caractères.


    private String roleApplication;
    // Attribut Java :
    //
    // roleApplication
    //
    // Colonne MariaDB :
    //
    // role_application

}