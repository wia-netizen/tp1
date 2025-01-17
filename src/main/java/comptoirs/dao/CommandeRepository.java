package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;

import comptoirs.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    /**
     * Calcule le montant des articles commandés dans une commande,
     * en tenant compte de la remise appliquée.
     * @param numeroCommande le numéro de la commande à traiter
     * @return le montant total des articles commandés
     */
    @Query("""
            SELECT SUM(l.quantite * l.produit.prixUnitaire * (1 - c.remise)) 
            FROM Commande c 
            JOIN c.lignes l
            WHERE c.numero = :numeroCommande
            """)
    BigDecimal montantArticles(Integer numeroCommande);
}


