package model;


public class NumeriTelefonici {
;
	private String tag;
	private String prefisso;
    private String numero;
    private String tipoNumero;
    private NumeriTelefonici deputato;
    
    public NumeriTelefonici (String tag,String Prefisso, String Numero, String tipoNumero) {
    	this.tag=tag;
    	this.prefisso = Prefisso;
    	this.numero = Numero;
    	this.tipoNumero = tipoNumero;
    }
    
    public void setPrefisso (String Prefisso) {
    	this.prefisso = Prefisso;
    }
    
    public String getPrefisso () {
    	return prefisso;
    }
    
    public void setNumero (String Numero) {
    	this.numero = Numero;
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

	public String stampaNumero() {
		return prefisso+numero+" tipo: "+tipoNumero ;
	}

	public String  gettag() {
		return tag;
	}

	
    
}