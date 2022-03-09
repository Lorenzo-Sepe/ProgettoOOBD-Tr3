package Model;


public class NumeriTelefonici {
		private String tag;
        private String prefisso;
        private String numero;
        private String tipoNumero;
        private NumeriTelefonici deputato;

        
    public NumeriTelefonici (String Prefisso, String Numero, String Tag, String tipoNumero) {
    	this.tag=Tag;
        this.prefisso = Prefisso;
        this.numero = Numero;
        this.tipoNumero = tipoNumero;
    }

    
    /**
     * restituisce il prefisso numerico
     * @return Stringa corrispondente al prefisso numerico
     */
    public String getPrefisso () {
        return prefisso;
    }

    /**
     * restituisce il numero di telefono
     * @return Stringa corrispondente al numero di telefono
     */
    public String getNumero () {
        return numero;
    }

    /**
     * restituisce il tipo del numero
     * @return Stringa corrispondente al tipo del numero
     */
    public String getTipoNumero () {
        return tipoNumero;
    }

    /**
     * imposta il deputato del numero di telefono
     * @param deputato oggetto della classe NumeriTelefonic corrispndente al deputato del numero
     * @return valore booleano il cui valore dipende alla riuscita o meno del metodo
     */
    public boolean setDeputato (NumeriTelefonici deputato) {
        boolean riuscita;
        String tipo1 = new String (this.getTipoNumero());
        String tipo2 = new String (deputato.getTipoNumero());
        if(tipo1.equals(tipo2)==false) {
            this.deputato = deputato;
            riuscita = true;
        }
        else {
            riuscita = false;
        }
        return riuscita;
    }

    /**
     * restituisce il deputato del numero
     * @return oggetto della classe NumeriTelefonici corrispondente al deputato del numero
     */
    public NumeriTelefonici getDeputato() {
        return deputato;
    }
    
    public String stampaNumero() {
        return prefisso+numero+" tipo: "+tipoNumero ;
    }

    /**
     * restituisce il tag del numero
     * @return Stringa corrispondente al tag del numero di telefono
     */
    public String  getTag() {
        return tag;
    }

}
