package sisMercadinhoV_Final;

public class Data {
	private int dia;
	private int mes;
	private int ano;

	public Data() {
		this.dia = 1;
		this.mes = 1;
		this.ano = 1900;
	}

	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public Data(String data) {
		String[] arrayData = data.split("\\/");
		
		this.dia = Integer.parseInt(arrayData[0]);
		this.mes = Integer.parseInt(arrayData[1]);
		this.ano = Integer.parseInt(arrayData[2]);
	}
	

	public String toString() {
		return this.dia + "/" + this.mes + "/" + this.ano;
	}

	public int getDia() {
		return this.dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
