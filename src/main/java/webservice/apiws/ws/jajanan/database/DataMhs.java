/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webservice.apiws.ws.jajanan.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "datamahasiswa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataMhs.findAll", query = "SELECT d FROM DataMhs d"),
    @NamedQuery(name = "DataMhs.findByNim", query = "SELECT d FROM DataMhs d WHERE d.nim = :nim"),
    @NamedQuery(name = "DataMhs.findByNama", query = "SELECT d FROM DataMhs d WHERE d.nama = :nama"),
    @NamedQuery(name = "DataMhs.findByAlamat", query = "SELECT d FROM DataMhs d WHERE d.alamat = :alamat"),
    @NamedQuery(name = "DataMhs.findByProgramstudi", query = "SELECT d FROM DataMhs d WHERE d.programstudi = :programstudi"),
    @NamedQuery(name = "DataMhs.findByFakultas", query = "SELECT d FROM DataMhs d WHERE d.fakultas = :fakultas")})
public class DataMhs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nim")
    private String nim;
    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "programstudi")
    private String programstudi;
    @Column(name = "fakultas")
    private String fakultas;

    public DataMhs() {
    }

    public DataMhs(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProgramstudi() {
        return programstudi;
    }

    public void setProgramstudi(String programstudi) {
        this.programstudi = programstudi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nim != null ? nim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataMhs)) {
            return false;
        }
        DataMhs other = (DataMhs) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webservice.apiws.ws.jajanan.database.DataMhs[ nim=" + nim + " ]";
    }
    
}
