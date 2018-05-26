package com.journaldev.jsf.pojo.daftarhunian;

import java.io.Serializable;

public class DaftarHunianDtl {
//no":"1","seqNo":1,"noKamar":314
//	private int no;
	private int seqNo;
//	private String noKamar;

        public static class MyCompositePK implements Serializable{
        private int noTrx;
        private String noKamar;
        
//        protected MyCompositePK(){
        public MyCompositePK(){	
        }
        
        public String getNoKamar() {
    		return noKamar;
    	}

    	public void setNoKamar(String noKamar) {
    		this.noKamar = noKamar;
    	}

        public int getNoTrx() {
                return noTrx;
        }

        public void setNoTrx(int noTrx) {
                this.noTrx = noTrx;
        }

    }
    
    private MyCompositePK id;
    
	public DaftarHunianDtl() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public int getNo() {
//		return no;
//	}

//	public void setNo(int no) {
//		this.no = no;
//	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

//	public String getNoKamar() {
//		return noKamar;
//	}

//	public void setNoKamar(String noKamar) {
//		this.noKamar = noKamar;
//	}

    public MyCompositePK getId() {
        return id;
    }

    public void setId(MyCompositePK id) {
        this.id = id;
    }
	
}
