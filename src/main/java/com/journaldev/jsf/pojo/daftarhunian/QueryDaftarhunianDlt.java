package com.journaldev.jsf.pojo.daftarhunian;

public class QueryDaftarhunianDlt {
//	private String noKamar;
	private int lantai;
	private int jmlTt;
	
	private DaftarHunianDtlKey daftarHunianDtlKey;		
	
	/*
	public String getNoKamar() {
		return noKamar;
	}
	public void setNoKamar(String noKamar) {
		this.noKamar = noKamar;
	} */
	
	public QueryDaftarhunianDlt(int lantai, int jmlTt, DaftarHunianDtlKey daftarHunianDtlKey) {
		super();
		this.lantai = lantai;
		this.jmlTt = jmlTt;
		this.daftarHunianDtlKey = daftarHunianDtlKey;
	}
	
	public QueryDaftarhunianDlt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLantai() {
		return lantai;
	}
	public void setLantai(int lantai) {
		this.lantai = lantai;
	}
	public int getJmlTt() {
		return jmlTt;
	}
	public void setJmlTt(int jmlTt) {
		this.jmlTt = jmlTt;
	}

	public DaftarHunianDtlKey getDaftarHunianDtlKey() {
		return daftarHunianDtlKey;
	}

	public void setDaftarHunianDtlKey(DaftarHunianDtlKey daftarHunianDtlKey) {
		this.daftarHunianDtlKey = daftarHunianDtlKey;
	}
}
