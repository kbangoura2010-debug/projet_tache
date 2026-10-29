package com.odc.projet_tache.controller;
// Indique que cette classe appartient au package "controller".
// Les Controllers reçoivent les requêtes HTTP venant de Postman ou du frontend React.


import com.odc.projet_tache.model.Utilisateur;
// On importe la classe Utilisateur.
// C'est l'objet Java qui représente un utilisateur.


import com.odc.projet_tache.repository.UtilisateurRepository;
// On importe le Repository.
// Le Repository permet au Controller de communiquer avec la base de données.


import org.springframework.web.bind.annotation.*;
// Importe les annotations Spring utilisées pour créer notre API REST.
// Exemples : @RestController, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping...


import org.springframework.http.ResponseEntity;
// Permet de contrôler la réponse HTTP.
// Par exemple :
// 200 OK
// 404 Not Found


import java.util.List;
// Permet d'utiliser List<Utilisateur> pour retourner plusieurs utilisateurs.



@RestController
// Indique à Spring que cette classe est un Controller REST.
// Les méthodes de cette classe vont répondre aux requêtes HTTP.
// Exemple : GET, POST, PUT, DELETE.


@RequestMapping("/utilisateurs")
// Définit l'adresse de base de toutes les méthodes de ce Controller.
//
// Exemple :
// @GetMapping             → /utilisateurs
// @GetMapping("/{id}")    → /utilisateurs/1
// @PostMapping            → /utilisateurs
// @PutMapping("/{id}")    → /utilisateurs/1
// @DeleteMapping("/{id}") → /utilisateurs/1


public class UtilisateurController {
    // Déclaration de notre Controller.


    private final UtilisateurRepository utilisateurRepository;
    // On crée une variable qui contient notre Repository.
    //
    // C'est grâce à cette variable que le Controller pourra demander
    // au Repository de lire, enregistrer, modifier ou supprimer
    // des utilisateurs dans la base de données.


    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        // Constructeur du Controller.
        //
        // Spring va automatiquement fournir une instance de
        // UtilisateurRepository ici.

        this.utilisateurRepository = utilisateurRepository;
        // On stocke le Repository reçu dans notre variable.
    }



    // =========================================================
    // GET : RÉCUPÉRER TOUS LES UTILISATEURS
    // =========================================================

    @GetMapping
    // Répond à :
    //
    // GET http://localhost:8081/utilisateurs

    public List<Utilisateur> listerTousLesUtilisateurs() {
        // La méthode retourne une liste d'Utilisateurs.

        return utilisateurRepository.findAll();
        // findAll() demande au Repository :
        //
        // "Donne-moi tous les utilisateurs de la base de données."
        //
        // Repository → JPA/Hibernate → MariaDB
    }



    // =========================================================
    // GET : RÉCUPÉRER UN SEUL UTILISATEUR
    // =========================================================

    @GetMapping("/{id}")
    // Répond à une URL comme :
    //
    // GET http://localhost:8081/utilisateurs/2
    //
    // {id} est une variable qui peut changer.
    //
    // /utilisateurs/1 → id = 1
    // /utilisateurs/2 → id = 2
    // /utilisateurs/10 → id = 10


    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        // @PathVariable récupère le nombre qui se trouve dans l'URL.
        //
        // Exemple :
        //
        // /utilisateurs/2
        //              ↑
        //              2 devient id
        //
        // Donc :
        //
        // id = 2


        return utilisateurRepository.findById(id)
            // Demande au Repository :
            //
            // "Cherche l'utilisateur dont l'ID est 2."
            //
            // findById() retourne un Optional<Utilisateur>.


            .map(utilisateur -> ResponseEntity.ok(utilisateur))
            // Si l'utilisateur existe :
            //
            // ResponseEntity.ok(...)
            //
            // signifie :
            //
            // HTTP 200 OK
            //
            // avec l'utilisateur dans la réponse.


            .orElse(ResponseEntity.notFound().build());
            // Si l'utilisateur n'existe pas :
            //
            // HTTP 404 Not Found
            //
            // Exemple :
            //
            // GET /utilisateurs/99
            //
            // si l'utilisateur 99 n'existe pas → 404
    }



    // =========================================================
    // POST : CRÉER UN UTILISATEUR
    // =========================================================

    @PostMapping
    // Répond à :
    //
    // POST http://localhost:8081/utilisateurs


    public Utilisateur creerUtilisateur(
            @RequestBody Utilisateur utilisateur) {
        // @RequestBody récupère les données JSON envoyées par Postman.
        //
        // Exemple de JSON :
        //
        // {
        //     "nom": "Diallo",
        //     "prenom": "Alpha",
        //     "email": "alpha@gmail.com",
        //     "motDePasse": "123456",
        //     "telephone": "620000000",
        //     "roleApplication": "USER"
        // }
        //
        // Spring transforme automatiquement ce JSON
        // en objet Java Utilisateur.


        return utilisateurRepository.save(utilisateur);
        // save() demande au Repository d'enregistrer
        // l'utilisateur dans MariaDB.
        //
        // Comme l'ID est auto-généré, MariaDB crée automatiquement
        // l'identifiant.
    }



    // =========================================================
    // PUT : MODIFIER UN UTILISATEUR
    // =========================================================

    @PutMapping("/{id}")
    // Répond à :
    //
    // PUT http://localhost:8081/utilisateurs/2
    //
    // Ici, 2 représente l'utilisateur que nous voulons modifier.


    public ResponseEntity<Utilisateur> modifierUtilisateur(
            @PathVariable Long id,
            @RequestBody Utilisateur utilisateur) {
        // Nous avons deux sources de données :
        //
        // 1. @PathVariable Long id
        //    → récupère l'ID dans l'URL
        //
        // 2. @RequestBody Utilisateur utilisateur
        //    → récupère les nouvelles données dans le JSON.



        Utilisateur utilisateurExistant =
            utilisateurRepository.findById(id).orElse(null);
        // On cherche d'abord l'utilisateur dans la base.
        //
        // Exemple :
        //
        // PUT /utilisateurs/2
        //
        // findById(2)
        //
        // Si l'utilisateur existe :
        // utilisateurExistant contient l'utilisateur.
        //
        // S'il n'existe pas :
        // utilisateurExistant = null.



        if (utilisateurExistant == null) {
            // On vérifie si l'utilisateur n'existe pas.

            return ResponseEntity.notFound().build();
            // S'il n'existe pas :
            //
            // HTTP 404 Not Found
            //
            // On arrête immédiatement la méthode.
        }



        utilisateurExistant.setNom(utilisateur.getNom());
        // On remplace l'ancien nom par le nouveau nom.


        utilisateurExistant.setPrenom(utilisateur.getPrenom());
        // On remplace l'ancien prénom par le nouveau.


        utilisateurExistant.setEmail(utilisateur.getEmail());
        // On remplace l'ancien email par le nouveau.


        utilisateurExistant.setMotDePasse(utilisateur.getMotDePasse());
        // On remplace l'ancien mot de passe par le nouveau.


        utilisateurExistant.setTelephone(utilisateur.getTelephone());
        // On remplace l'ancien téléphone par le nouveau.


        utilisateurExistant.setRoleApplication(
            utilisateur.getRoleApplication());
        // On remplace l'ancien rôle par le nouveau.



        Utilisateur utilisateurModifie =
            utilisateurRepository.save(utilisateurExistant);
        // save() enregistre les modifications dans la base de données.
        //
        // MariaDB possède maintenant les nouvelles informations.



        return ResponseEntity.ok(utilisateurModifie);
        // On retourne :
        //
        // HTTP 200 OK
        //
        // avec l'utilisateur modifié dans le Body.
    }



    // =========================================================
    // DELETE : SUPPRIMER UN UTILISATEUR
    // =========================================================

    @DeleteMapping("/{id}")
    // Répond à :
    //
    // DELETE http://localhost:8081/utilisateurs/5
    //
    // Le 5 représente l'utilisateur à supprimer.


    public void supprimerUtilisateur(@PathVariable Long id) {
        // @PathVariable récupère l'ID dans l'URL.
        //
        // Exemple :
        //
        // DELETE /utilisateurs/5
        //
        // id = 5


        utilisateurRepository.deleteById(id);
        // Demande au Repository de supprimer
        // l'utilisateur correspondant à cet ID.
    }

}