package com.journaldev.jsf.pojo.daftarhunian;

public class DaftarHunianDtlKey {
	private int noTrx;
	private String noKamar;
	
	public DaftarHunianDtlKey() {
	}	
	
	public DaftarHunianDtlKey(int noTrx, String noKamar) {
		super();
		this.noTrx = noTrx;
		this.noKamar = noKamar;
	}
	public int getNoTrx() {
		return noTrx;
	}
	public void setNoTrx(int noTrx) {
		this.noTrx = noTrx;
	}
	public String getNoKamar() {
		return noKamar;
	}
	public void setNoKamar(String noKamar) {
		this.noKamar = noKamar;
	}
}
