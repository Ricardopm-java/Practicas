package Modelo;

public class Resena {
	
	private String lugar;
	private String calificacion;
	private String opinion;
	private String notas;
	private String usuario;
	
	public Resena(String lugar, String calificacion, String opinion, String notas, String usuario) {
		super();
		this.lugar = lugar;
		this.calificacion = calificacion;
		this.opinion = opinion;
		this.notas = notas;
		this.usuario = usuario;
	}
	
	
	public Resena(String usuario,String lugar, String calificacion, String opinion) {
		super();
		this.usuario = usuario;
		this.lugar = lugar;
		this.calificacion = calificacion;
		this.opinion = opinion;
	}

	public Resena(String lugar, String calificacion, String opinion) {
		super();
		this.lugar = lugar;
		this.calificacion = calificacion;
		this.opinion = opinion;
	}
	
	public Resena() {}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "[lugar=" + lugar + ", calificacion=" + calificacion + ", opinion=" + opinion + ", notas=" + notas
				+ "]";
	}
	
	
	public String imprimirOpinion() {
		return "[lugar=" + lugar +", calificacion=" + calificacion + ", opinion=" + opinion + "]";
	}
	
	public String imprimirLugar() {
		return "[usuario=" + usuario +", lugar=" + lugar +", calificacion=" + calificacion + ", opinion=" + opinion + "]";
	}
	

}
