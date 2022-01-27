package Model;


public class NumeriTelefonici {

	private String Prefisso;
    private String Numero;
    private String tipoNumero;
    private NumeriTelefonici deputato;
    
    NumeriTelefonici (String Prefisso, String Numero, String tipoNumero) {
    	this.Prefisso = Prefisso;
    	this.Numero = Numero;
    	this.tipoNumero = tipoNumero;
    }
    
    public void setPrefisso (String Prefisso) {
    	this.Prefisso = Prefisso;
    }
    
    public String getPrefisso () {
    	return Prefisso;
    }
    
    public void setNumero (String Numero) {
    	this.Numero = Numero;
    }
    
    public String getNumero () {
    	return Numero;
    }
    
    public void setTipoNumero (String tipoNumero) {
    	this.tipoNumero = tipoNumero;
    }
    
    public String getTipoNumero () {
    	return tipoNumero;
    }
    
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
    
}