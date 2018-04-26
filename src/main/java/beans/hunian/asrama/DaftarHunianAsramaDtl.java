/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.hunian.asrama;

import java.io.Serializable;

/**
 *
 * @author myssd
 */
public class DaftarHunianAsramaDtl implements Serializable{
    public static class MyCompositePK implements Serializable{
        private int noTrx;
        private String noKamar;
        
        protected MyCompositePK(){
        	
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
    
    private int seqNo;
    
    private DaftarHunianAsrama daftarHunianAsrama;
    
    private Room room;
    
    public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public MyCompositePK getId() {
		return id;
	}

	public void setId(MyCompositePK id) {
		this.id = id;
	}

	public DaftarHunianAsrama getDaftarHunianAsrama() {
		return daftarHunianAsrama;
	}

	public void setDaftarHunianAsrama(DaftarHunianAsrama daftarHunianAsrama) {
		this.daftarHunianAsrama = daftarHunianAsrama;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public DaftarHunianAsramaDtl(){
		
	}

    public DaftarHunianAsramaDtl(MyCompositePK id, int seqNo, DaftarHunianAsrama daftarHunianAsrama, Room room) {
        this.id = id;
        this.seqNo = seqNo;
        this.daftarHunianAsrama = daftarHunianAsrama;
        this.room = room;
    }
        
}
