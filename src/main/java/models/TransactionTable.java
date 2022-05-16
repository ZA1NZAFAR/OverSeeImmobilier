package models;


import interfaces.SQLable;

public class TransactionTable implements SQLable {

  private long numeroReference;
  private long idAgentImmobilier;
  private long idPropriétaire;
  private long idClient;
  private java.sql.Date datevente;
  private String commission;
  private long montantTotalTransaction;
  private String typeTransaction;
  private double prixVente;


  public long getNumeroReference() {
    return numeroReference;
  }

  public void setNumeroReference(long numeroReference) {
    this.numeroReference = numeroReference;
  }


  public long getIdAgentImmobilier() {
    return idAgentImmobilier;
  }

  public void setIdAgentImmobilier(long idAgentImmobilier) {
    this.idAgentImmobilier = idAgentImmobilier;
  }


  public long getIdPropriétaire() {
    return idPropriétaire;
  }

  public void setIdPropriétaire(long idPropriétaire) {
    this.idPropriétaire = idPropriétaire;
  }


  public long getIdClient() {
    return idClient;
  }

  public void setIdClient(long idClient) {
    this.idClient = idClient;
  }


  public java.sql.Date getDatevente() {
    return datevente;
  }

  public void setDatevente(java.sql.Date datevente) {
    this.datevente = datevente;
  }


  public String getCommission() {
    return commission;
  }

  public void setCommission(String commission) {
    this.commission = commission;
  }


  public long getMontantTotalTransaction() {
    return montantTotalTransaction;
  }

  public void setMontantTotalTransaction(long montantTotalTransaction) {
    this.montantTotalTransaction = montantTotalTransaction;
  }


  public String getTypeTransaction() {
    return typeTransaction;
  }

  public void setTypeTransaction(String typeTransaction) {
    this.typeTransaction = typeTransaction;
  }


  public double getPrixVente() {
    return prixVente;
  }

  public void setPrixVente(double prixVente) {
    this.prixVente = prixVente;
  }

  @Override
  public String getSQLInsert() {
    return "INSERT INTO transactiontable (numeroReference, idAgentImmobilier, idPropriétaire, idClient, datevente, commission, montantTotalTransaction, typeTransaction, prixVente) VALUES (" +
            numeroReference + ", " +
            idAgentImmobilier + ", " +
            idPropriétaire + ", " +
            idClient + ", " +
            "\"" + datevente + "\", " +
            "\"" + commission + "\", " +
            montantTotalTransaction + ", " +
            "\"" + typeTransaction + "\", " +
            prixVente + ");";
  }

  @Override
  public String getSQLUpdate() {
    return "UPDATE transactiontable SET numeroReference = " +
            numeroReference + ", idAgentImmobilier = " +
            idAgentImmobilier + ", idPropriétaire = " +
            idPropriétaire + ", idClient = " +
            idClient + ", datevente = " +
            "\"" + datevente + "\", " +
            "\"" + commission + "\", " +
            montantTotalTransaction + ", " +
            "\"" + typeTransaction + "\", " +
            prixVente + ";";
  }

  @Override
  public String getSQLDelete() {
    return "DELETE FROM transactiontable WHERE numeroReference = " + numeroReference + ";";
  }

  @Override
  public String getSQLSelect() {
    return "SELECT * FROM transactiontable WHERE numeroReference = " + numeroReference + ";";
  }
}
