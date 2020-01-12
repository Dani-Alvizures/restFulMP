/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dannyalvizures
 */
@Entity
@Table(name = "FISCALIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fiscalia.findAll", query = "SELECT f FROM Fiscalia f")
    , @NamedQuery(name = "Fiscalia.findByIdFiscalia", query = "SELECT f FROM Fiscalia f WHERE f.idFiscalia = :idFiscalia")
    , @NamedQuery(name = "Fiscalia.findByNombre", query = "SELECT f FROM Fiscalia f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Fiscalia.findByDireccion", query = "SELECT f FROM Fiscalia f WHERE f.direccion = :direccion")
    , @NamedQuery(name = "Fiscalia.findByTelefono", query = "SELECT f FROM Fiscalia f WHERE f.telefono = :telefono")})
public class Fiscalia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFiscalia")
    private Integer idFiscalia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "telefono")
    private String telefono;

    public Fiscalia() {
    }

    public Fiscalia(Integer idFiscalia) {
        this.idFiscalia = idFiscalia;
    }

    public Fiscalia(Integer idFiscalia, String nombre, String direccion, String telefono) {
        this.idFiscalia = idFiscalia;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdFiscalia() {
        return idFiscalia;
    }

    public void setIdFiscalia(Integer idFiscalia) {
        this.idFiscalia = idFiscalia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getJson(){
        String json = "{" + 
                "\"id\":"+this.idFiscalia +","+
                "\"nombre\":\""+this.nombre +"\","+
                "\"direccion\":\""+this.direccion +"\","+ 
                "\"telefono\":\""+this.telefono +"\""+
                "}";
        return json;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFiscalia != null ? idFiscalia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fiscalia)) {
            return false;
        }
        Fiscalia other = (Fiscalia) object;
        if ((this.idFiscalia == null && other.idFiscalia != null) || (this.idFiscalia != null && !this.idFiscalia.equals(other.idFiscalia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Fiscalia[ idFiscalia=" + idFiscalia + " ]";
    }
    
}
