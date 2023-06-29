/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class MovieTicket {
    private int nm;
    private int vp;
    private int vt;
    
    private String pcnm;
    private String pcvp;
    private String pcvt;

    public MovieTicket(int nm, int vp, int vt, String pcnm, String pcvp, String pcvt) {
        this.nm = nm;
        this.vp = vp;
        this.vt = vt;
        this.pcnm = pcnm;
        this.pcvp = pcvp;
        this.pcvt = pcvt;
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public int getVp() {
        return vp;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public String getPcnm() {
        return pcnm;
    }

    public void setPcnm(String pcnm) {
        this.pcnm = pcnm;
    }

    public String getPcvp() {
        return pcvp;
    }

    public void setPcvp(String pcvp) {
        this.pcvp = pcvp;
    }

    public String getPcvt() {
        return pcvt;
    }

    public void setPcvt(String pcvt) {
        this.pcvt = pcvt;
    }
    
    
}
