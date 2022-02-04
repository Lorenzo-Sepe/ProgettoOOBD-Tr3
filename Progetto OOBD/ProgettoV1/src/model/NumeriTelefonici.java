package Model;


public class NumeriTelefonici {

	private String prefisso;
    private String numero;
    private String tipoNumero;
    private NumeriTelefonici deputato;
    
    NumeriTelefonici (String prefisso, String numero, String tipoNumero) {
    	this.prefisso = prefisso;
    	this.numero = numero;
    	this.tipoNumero = tipoNumero;
    }
    
    public void setPrefisso (String prefisso) {
    	this.prefisso = prefisso;
    }
    
    public String getPrefisso () {
    	return prefisso;
    }
    
    public void setNumero (String numero) {
    	this.numero = numero;
    }
    
    public String getNumero () {
    	return numero;
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
